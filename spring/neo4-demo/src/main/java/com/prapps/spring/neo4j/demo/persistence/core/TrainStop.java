package com.prapps.spring.neo4j.demo.persistence.core;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@RelationshipEntity(type = "STOPS")
public class TrainStop extends BaseEntity {
	@Transient
	private Long trainId;
	@Transient
	private Long stationId;
	@Transient
	private String stationCode;
	
	//@JsonIgnore
	@StartNode private Train train;
	@JsonIgnore
	@EndNode private Station station;
	
	@JsonProperty("arr")
	private String arrival;
	@JsonProperty("dep")
	private String departure;
	private String halt;
	@JsonProperty("dist")
	private String distance;
	private String day;
	@JsonProperty("seq")
	private String sequence;
	
	public Long getTrainId() {
		return trainId;
	}
	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getHalt() {
		return halt;
	}
	public void setHalt(String halt) {
		this.halt = halt;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "TrainStop [trainId=" + trainId + ", stationId=" + stationId + ", stationCode=" + stationCode
				+ ", train=" + train + ", station=" + station + ", arrival=" + arrival + ", departure=" + departure
				+ ", halt=" + halt + ", distance=" + distance + ", day=" + day + ", sequence=" + sequence + "]";
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
