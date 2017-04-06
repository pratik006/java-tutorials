package com.prapps.spring.neo4j.demo.persistence.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Station")
public class Station extends BaseEntity {
	private String code;
	@Relationship(type="ADJACENT_TO", direction = Relationship.UNDIRECTED)
	private Collection<Station> nextStation;
	
	@Relationship(type="STOPS", direction = Relationship.INCOMING)
	private Set<TrainStop> trainStops;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Collection<Station> getNextStation() {
		return nextStation;
	}
	public void setNextStation(Collection<Station> nextStation) {
		this.nextStation = nextStation;
	}
	public Set<TrainStop> getTrainStops() {
		if (trainStops == null) {
			trainStops = new HashSet<>();
		}
		return trainStops;
	}
	public void setTrainStops(Set<TrainStop> trainStops) {
		this.trainStops = trainStops;
	}
	@Override
	public String toString() {
		return "Station [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
