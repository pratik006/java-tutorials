package com.barsha;

import java.util.List;

public class Testcase {
	private String name;
	private List<CsvRec> records;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CsvRec> getRecords() {
		return records;
	}
	public void setRecords(List<CsvRec> records) {
		this.records = records;
	}
}
