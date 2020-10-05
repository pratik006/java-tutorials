package com.prapps.tutorial.streamservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableBinding(StreamServiceApplication.AnalyticsBinding.class)
public class StreamServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(StreamServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(StreamServiceApplication.class, args);
	}

	@Component
	public static class PageViewEventSource implements ApplicationRunner {
		private final MessageChannel pageViewsOut;

		public PageViewEventSource(AnalyticsBinding analyticsBinding) {
			this.pageViewsOut = analyticsBinding.pageViewsOut();
		}

		@Override
		public void run(ApplicationArguments args) throws Exception {
			List<String> names = Arrays.asList("mfisher", "dyser", "schako", "abilan", "ozhurakousky", "grussel");
			List<String> pages = Arrays.asList("blog", "sitemap", "initializr", "news", "colophon", "about");
Thread.sleep(10000);
			Runnable runnable = () -> {
				String rPage = pages.get(new Random().nextInt(pages.size()));
				String rName = pages.get(new Random().nextInt(pages.size()));
				PageViewEvent pageEvent = new PageViewEvent(rName, rPage, Math.random() > .5 ? 10 : 1000);
				Message message = MessageBuilder
						.withPayload(pageEvent)
						.setHeader(KafkaHeaders.MESSAGE_KEY, pageEvent.getUserId().getBytes())
						.build();
				try {
					this.pageViewsOut.send(message);
					LOG.info("Message sent - {}", message);
				} catch (Exception ex) {
					LOG.error("Exception occured.", ex);
				}
			};

			Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
		}
	}

	@Component
	public static class PageViewEventSink {
		private static final Logger LOG = LoggerFactory.getLogger(PageViewEventSink.class);

		@StreamListener
		@SendTo(AnalyticsBinding.PAGE_COUNT_OUT)
		public KStream<String, Long> processPageViews(@Input(AnalyticsBinding.PAGE_VIEWS_IN) KStream<String, PageViewEvent> events) {
			return events
				.filter((key, value) -> value.getDuration() > 10)
				.map((key, value) -> new KeyValue<>(value.getPage(), "0"))
				.groupByKey()
					//.windowedBy(TimeWindows.of(Duration.ofMinutes(1))
				.count(Materialized.as(AnalyticsBinding.PAGE_COUNT_MV))
					//.count()
					.toStream();
		}
	}

	@Component
	public static class PageCountSink {
		private static final Logger LOG = LoggerFactory.getLogger(PageCountSink.class);
		@StreamListener
		public void processPageCount(@Input(AnalyticsBinding.PAGE_COUNT_IN) KTable<String, Long> counts) {
			counts.toStream().foreach((key, value) -> LOG.info("PageCount: "+key +"=" + value));
		}
	}

	@RestController
	public static class CountRestcontroller {

		private InteractiveQueryService interactiveQueryService;

		public CountRestcontroller(InteractiveQueryService interactiveQueryService) {
			this.interactiveQueryService = interactiveQueryService;
		}

		@GetMapping("/counts")
		Map<String, Long> counts() {
			Map<String, Long> countsMap = new HashMap<>();
			KeyValueIterator<String, Long> itr = this.interactiveQueryService.getQueryableStore(AnalyticsBinding.PAGE_COUNT_MV, QueryableStoreTypes.<String, Long>keyValueStore()).all();
			while (itr.hasNext()) {
				KeyValue<String, Long> value = itr.next();
				countsMap.put(value.key, value.value);
			}
			return countsMap;
		}
	}

	interface AnalyticsBinding {
		String PAGE_VIEWS_OUT = "pvout";
		String PAGE_VIEWS_IN = "pvin";
		//String PAGE_VIEWS_MV = "pvmv";
		String PAGE_COUNT_OUT = "pcout";
		String PAGE_COUNT_IN = "pcin";
		String PAGE_COUNT_MV = "pcmv";

		@Output(PAGE_VIEWS_OUT)
		MessageChannel pageViewsOut();

		@Input(PAGE_VIEWS_IN)
		KStream<String, PageViewEvent> pageViewsIn();

		@Output(PAGE_COUNT_OUT)
		KStream<String, Long> pageCountOut();

		@Input(PAGE_COUNT_IN)
		KTable<String, Long> pageCountIn();
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	static class PageViewEvent {
		private String userId, page;
		private long duration;
	}
}
