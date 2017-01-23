package com.prapps.spring.neo4j.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prapps.spring.neo4j.demo.persistence.core.Train;
import com.prapps.spring.neo4j.demo.persistence.repo.TrainRepo;

@Service
public class TrainService extends GenericService<Train> {

	TrainRepo trainRepo;
	
	@Autowired
	public TrainService(TrainRepo trainRepo) {
		super(trainRepo);
		this.trainRepo = trainRepo;
	}
	
	public List<Train> findTrains(String from, String to) {
		return trainRepo.findTrains(from, to);
	}

}
