package com.prapps.tutorial.spring.netflix.studentservice;

import java.util.ArrayList;
import java.util.List;

public class StudentSearchResponse {
    private List<Student> students;
    private List<String> messages;

    public StudentSearchResponse() { }
    public StudentSearchResponse(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<String> getMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
        }

        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
