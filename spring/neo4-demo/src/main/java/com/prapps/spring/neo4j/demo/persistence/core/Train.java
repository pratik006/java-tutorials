package com.prapps.spring.neo4j.demo.persistence.core;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Train")
public class Train extends BaseEntity {
	private String from;
	private String to;
	private String classes;
	private String rundays;
	private String type;
	
	@Relationship(type="STOPS", direction = Relationship.OUTGOING)
	private Set<TrainStop> trainStops;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getRundays() {
		return rundays;
	}
	public void setRundays(String rundays) {
		this.rundays = rundays;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<TrainStop> getTrainStops() {
		if (null == trainStops) {
			trainStops = new HashSet<>();
		}
		
		return trainStops;
	}
	public void setTrainStops(Set<TrainStop> trainStops) {
		this.trainStops = trainStops;
	}
	@Override
	public String toString() {
		return "Train [from=" + from + ", to=" + to + ", classes=" + classes + ", rundays=" + rundays + ", type=" + type
				+ ", trainStops=" + trainStops + ", id=" + id + ", name=" + name + "]";
	}
}
