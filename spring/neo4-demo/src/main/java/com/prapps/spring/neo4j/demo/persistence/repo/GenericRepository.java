package com.prapps.spring.neo4j.demo.persistence.repo;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T> extends GraphRepository<T> {
}
