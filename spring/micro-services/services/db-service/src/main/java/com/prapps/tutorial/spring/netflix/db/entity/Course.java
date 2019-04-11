package com.prapps.tutorial.spring.netflix.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String duration;

    public Course() { }

    public Course(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int hashCode() {
        return Objects.hash(id, name, duration);
    }

    public boolean equals(Object other) {
        if (other instanceof Course) {
            Course otherCourse = (Course) other;
            return Objects.equals(id, otherCourse.getId())
                    && Objects.equals(duration, otherCourse.getDuration())
                    && Objects.equals(name, otherCourse.getName());
        }

        return false;
    }
}
