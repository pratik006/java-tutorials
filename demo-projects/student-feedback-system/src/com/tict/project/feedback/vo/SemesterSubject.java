package com.tict.project.feedback.vo;

public class SemesterSubject {

	private long semesterId;
	private long subjectId;
	private String semesterName;
	private String subjectName;
	
	public long getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(long semesterId) {
		this.semesterId = semesterId;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
