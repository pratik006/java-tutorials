package com.sensor;

public class Task implements Comparable<Task>{
	
	private String id;
	private String command;
	private String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int compareTo(Task other) {
		return Integer.parseInt(id) - Integer.parseInt(other.getId());
	}
}
