package com.prapps.tutorial.spring.neo4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prapps.tutorial.spring.neo4j.persistence.Train;
import com.prapps.tutorial.spring.neo4j.persistence.TrainRepository;

@RestController("/rail")
public class RailwayController {

	@Autowired TrainRepository trainRepository;
	
	@RequestMapping("/{id}")
	public @ResponseBody Train findTrain(@PathVariable(value = "id") Long id) {
		return trainRepository.findOne(id);
	}
	
	@RequestMapping("/hello")
	public String test() {
		return "hello";
	}
}
