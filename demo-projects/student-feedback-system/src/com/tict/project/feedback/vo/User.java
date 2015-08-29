package com.tict.project.feedback.vo;

public class User {
	

	private long id;
	private String username;
	private String firstName;
	private String lastName;
	private String type;
	private String email;
	private String dob;
	private String caste;
	private String gender;
	private String nationality;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(long id, String fname, String lname) {
		this.id = id;
		this.firstName = fname;
		this.lastName = lname;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int hashCode() {
		return (int)id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	public boolean equals(Object o) {
		if(o != null && o instanceof User) {
			User temp = (User) o;
			if(id == temp.getId()) {
				return true;
			}
		}
		return false;
	}
}
