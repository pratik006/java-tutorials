package com.prapps.tutorial.spring.neo4j.persistence;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "train", path = "train")
public interface TrainRepository extends PagingAndSortingRepository<Train, Long> {
    //List<Train> findById(@Param("name") Long id);

}