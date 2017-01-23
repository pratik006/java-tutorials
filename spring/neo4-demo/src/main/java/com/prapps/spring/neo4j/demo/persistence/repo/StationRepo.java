package com.prapps.spring.neo4j.demo.persistence.repo;

import org.springframework.stereotype.Repository;

import com.prapps.spring.neo4j.demo.persistence.core.Station;

@Repository
public interface StationRepo extends GenericRepository<Station> {
	Station findByCode(String code);
	Station findById(Long cid);
}
