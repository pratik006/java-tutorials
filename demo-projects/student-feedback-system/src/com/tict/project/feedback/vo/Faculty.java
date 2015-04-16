package com.tict.project.feedback.vo;

import java.util.List;

public class Faculty extends User {

	private List<SemesterSubject> semesterSubjects;

	public List<SemesterSubject> getSemesterSubjects() {
		return semesterSubjects;
	}

	public void setSemesterSubjects(List<SemesterSubject> semesterSubjects) {
		this.semesterSubjects = semesterSubjects;
	}
	
}
