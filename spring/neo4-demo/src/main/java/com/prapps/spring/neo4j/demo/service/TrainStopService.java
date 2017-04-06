package com.prapps.spring.neo4j.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prapps.spring.neo4j.demo.persistence.core.TrainStop;
import com.prapps.spring.neo4j.demo.persistence.repo.TrainStopRepo;

@Service
public class TrainStopService extends GenericService<TrainStop> {

	TrainStopRepo trainStopRepo;
	
	@Autowired
	public TrainStopService(TrainStopRepo trainStopRepo) {
		super(trainStopRepo);
		this.trainStopRepo = trainStopRepo;
	}

}
