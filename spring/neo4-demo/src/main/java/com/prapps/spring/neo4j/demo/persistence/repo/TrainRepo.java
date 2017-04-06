package com.prapps.spring.neo4j.demo.persistence.repo;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prapps.spring.neo4j.demo.persistence.core.Train;

@Repository
public interface TrainRepo extends GenericRepository<Train> {
	Train findById(Long id);
	@Query("match (s1:Station {code: {from}})-[stop:STOPS]-(t1:Train)-[:STOPS]-(s2:Station {code: {to}}) "
			+ "return t1, stop")
	List<Train> findTrains(@Param("from") String from, @Param("to") String to);
}
