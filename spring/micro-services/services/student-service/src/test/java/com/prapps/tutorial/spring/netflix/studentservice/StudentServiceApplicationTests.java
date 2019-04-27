package com.prapps.tutorial.spring.netflix.studentservice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceApplicationTests {

	@BeforeClass
	public static void setUp() {
		System.setProperty("logging.baseloc", "/tmp");
		System.setProperty("CORE_API_HOST", "localhost");

	}

	@Test
	public void contextLoads() {
		RestTemplate template = new RestTemplate();
		Set<String> hashes = new HashSet<>();
		Set<String> uniqueHashes = Collections.synchronizedSet(hashes);
		Callable<String> runnable = () -> {
			StudentSearchResponse resp = template.getForObject("http://192.168.99.101:32001/search?name=Pratik", StudentSearchResponse.class);
			/*System.out.println("Msg: "+resp.getMessages().get(0));
			if (uniqueHashes.contains(resp.getMessages().get(0))) {
				uniqueHashes.add(resp.getMessages().get(0));
			}*/
			return resp.getMessages().get(0).split("StudentService: ")[1];
		};
		ExecutorService service = Executors.newFixedThreadPool(100);

		List<Future<String>> callables = new ArrayList<>(2);
		for (int i=0;i<20000;i++) {
			callables.add(service.submit(runnable));
		}
		service.shutdown();

		System.out.println("uniqueHashes: "+callables.stream().map(f -> {
			try {
				return f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			return "";
		}).collect(Collectors.toSet()));
	}

}
