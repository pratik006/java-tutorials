package com.prapps.spring.neo4j.demo.persistence.repo;

import org.springframework.stereotype.Repository;

import com.prapps.spring.neo4j.demo.persistence.core.TrainStop;

@Repository
public interface TrainStopRepo extends GenericRepository<TrainStop> {
}
