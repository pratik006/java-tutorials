package com.prapps.tutorial.spring.neo4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.tutorial.spring.neo4j.persistence.Train;
import com.prapps.tutorial.spring.neo4j.repo.TrainRepository;

@Controller
@RequestMapping("/rail")
public class RailwayController {

	@Autowired TrainRepository trainRepository;
	
	@RequestMapping("/{id}")
	public @ResponseBody Train findTrain(@PathVariable(value = "id") Long id) {
		Train train = new Train();
		train.setId(id);
		train.setName("abc");
		train = trainRepository.save(train);
		System.out.println(train);
		return trainRepository.findOne(id);
	}
	
	@RequestMapping("/hello")
	public @ResponseBody String test() {
		return "hello";
	}
}
