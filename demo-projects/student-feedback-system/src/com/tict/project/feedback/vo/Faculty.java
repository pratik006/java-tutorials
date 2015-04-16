package com.tict.project.feedback.vo;

import java.util.List;

public class Faculty extends User {

	private List<SemesterSubject> semesterSubjects;

	public Faculty() {
		// TODO Auto-generated constructor stub
	}
	
	public Faculty(long id, String fname, String lname) {
		super(id, fname, lname);
	}
	
	public List<SemesterSubject> getSemesterSubjects() {
		return semesterSubjects;
	}

	public void setSemesterSubjects(List<SemesterSubject> semesterSubjects) {
		this.semesterSubjects = semesterSubjects;
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public String toString() {
		return super.toString();
	}
	
	public boolean equals(Object o) {
		if(o != null && o instanceof Faculty) {
			return super.equals(o);
		}
		return false;
	}
}
