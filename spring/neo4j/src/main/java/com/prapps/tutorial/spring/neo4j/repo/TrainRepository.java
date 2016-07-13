package com.prapps.tutorial.spring.neo4j.repo;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.prapps.tutorial.spring.neo4j.persistence.Train;

@Repository
public interface TrainRepository extends GraphRepository<Train> {
    //List<Train> findById(@Param("name") Long id);
}