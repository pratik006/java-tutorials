package com.prapps.spring.neo4j.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prapps.spring.neo4j.demo.persistence.core.Station;
import com.prapps.spring.neo4j.demo.persistence.repo.StationRepo;

@Service
public class StationService extends GenericService<Station> {

	private StationRepo stationRepo;
	
	@Autowired
	public StationService(StationRepo stationRepo) {
		super(stationRepo);
		this.stationRepo = stationRepo;
	}
	
	public Station findByCode(String code) {
		return stationRepo.findByCode(code);
	}
}
