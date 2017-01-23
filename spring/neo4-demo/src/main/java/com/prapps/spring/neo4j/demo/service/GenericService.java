package com.prapps.spring.neo4j.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prapps.spring.neo4j.demo.persistence.core.BaseEntity;
import com.prapps.spring.neo4j.demo.persistence.core.Train;
import com.prapps.spring.neo4j.demo.persistence.repo.GenericRepository;
import com.prapps.spring.neo4j.demo.persistence.repo.StationRepo;
import com.prapps.spring.neo4j.demo.persistence.repo.TrainRepo;

@Transactional(readOnly = true)
public abstract class GenericService<T extends BaseEntity> {
	
	private GenericRepository<T> genericRepo;
	
	@Autowired
	public GenericService(GenericRepository<T> genericRepo) {
		this.genericRepo = genericRepo;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Iterable<T> save(Iterable<T> entities) {
		return genericRepo.save(entities);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T save(T entity, int depth) {
		return genericRepo.save(entity, depth);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T save(T entity) {
		return genericRepo.save(entity);
	}
	
	public Iterable<T> findAll() {
		return genericRepo.findAll();
	}
	
	public T findOne(Long graphId) {
		return genericRepo.findOne(graphId);
	}
	
	public T findById(Long id) {
		if (genericRepo instanceof TrainRepo) {
			Train t	= ((TrainRepo)genericRepo).findById(id);
			if (t != null) {
				return genericRepo.findOne(t.getGraphId());
			}
			return (T) t;
		}
		if (genericRepo instanceof StationRepo) {
			return genericRepo.findOne(((StationRepo)genericRepo).findById(id).getGraphId(), 2);
		}
		return null;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		genericRepo.delete(id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Iterable<Long> ids) {
		for (Long id : ids) {
			genericRepo.delete(id);
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(T t) {
		genericRepo.delete(t);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Collection<T> t) {
		genericRepo.delete(t);
	}
}
