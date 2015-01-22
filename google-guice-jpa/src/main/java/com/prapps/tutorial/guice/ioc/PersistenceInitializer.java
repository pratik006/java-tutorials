package com.prapps.tutorial.guice.ioc;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;

@Singleton
public class PersistenceInitializer {

	@Inject
	PersistenceInitializer(PersistService service) {
		service.start();
		// At this point JPA is started and ready.
	}
}
