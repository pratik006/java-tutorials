package com.prapps.spring.neo4j.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prapps.spring.neo4j.demo.persistence.core.Station;
import com.prapps.spring.neo4j.demo.service.StationService;

@RestController
@RequestMapping(value = "/station")
public class StationController {
	@Autowired StationService stationService;
	
	@RequestMapping("/code/{code}")
	public Station get(@PathVariable(value = "code") String code) {
		return stationService.findByCode(code);
	}
	
	@RequestMapping(value = "/all", method = {RequestMethod.GET})
	public Iterable<Station> getAll() {
		return stationService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET})
	public Station get(@PathVariable("id") Long id) {
		return stationService.findById(id);
	}

	@RequestMapping(method = {RequestMethod.POST})
	public Iterable<Station> add(@RequestBody Iterable<Station> stations) {
		return stationService.save(stations);
	}
	
	@RequestMapping(method = {RequestMethod.DELETE})
	public void delete(@RequestBody Collection<Long> ids) {
		stationService.delete(ids);
	}
}
