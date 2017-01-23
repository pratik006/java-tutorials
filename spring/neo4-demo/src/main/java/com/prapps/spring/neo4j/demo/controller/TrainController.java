package com.prapps.spring.neo4j.demo.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prapps.spring.neo4j.demo.persistence.core.Station;
import com.prapps.spring.neo4j.demo.persistence.core.Train;
import com.prapps.spring.neo4j.demo.persistence.core.TrainStop;
import com.prapps.spring.neo4j.demo.service.StationService;
import com.prapps.spring.neo4j.demo.service.TrainService;
import com.prapps.spring.neo4j.demo.service.TrainStopService;

@RestController
@RequestMapping(value = "/train")
public class TrainController {
	@Autowired TrainService trainService;
	@Autowired StationService stationService;
	@Autowired TrainStopService trainStopService;
	
	@RequestMapping("/find")
	public Collection<Train> findTrains(@RequestParam("from") String from, @RequestParam("to") String to) {
		Collection<Train> trains = trainService.findTrains(from, to);
		return trains;
	}
	
	@RequestMapping(value = "/all", method = {RequestMethod.GET})
	public Iterable<Train> getAll() {
		return trainService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET})
	public Train get(@PathVariable("id") Long id) {
		return trainService.findById(id);
	}

	@RequestMapping(method = {RequestMethod.POST})
	public Iterable<Train> add(@RequestBody Iterable<Train> stations) {
		return trainService.save(stations);
	}
	
	@RequestMapping(method = {RequestMethod.DELETE})
	public void delete(@RequestBody Collection<Long> ids) {
		trainService.delete(ids);
	}
	
	@RequestMapping(value = "/updatestops", method = RequestMethod.POST)
	public void updateTrainStop(@RequestBody Iterable<TrainStop> trainStops) {
		Collection<Train> trains = new ArrayList<>();
		Collection<Station> stations = new ArrayList<>();
		Collection<TrainStop> stops = new ArrayList<>();
		
		for (TrainStop trainStop : trainStops) {
			System.out.println("trainStop: "+trainStop);
			Train train = trainService.findById(trainStop.getTrainId());
			Station station = stationService.findByCode(trainStop.getStationCode());
			trainStop.setTrain(train);
			trainStop.setStation(station);
			stops.add(trainStopService.save(trainStop));
			train.getTrainStops().add(trainStop);
			trains.add(train);
			
			
			station.getTrainStops().add(trainStop);
			stations.add(station);
		}
		//trainService.save(trains);
		//stationService.save(stations);
	}
}
