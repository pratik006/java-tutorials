package com.prapps.tutorial.spring.netflix.db.repo;

import com.prapps.tutorial.spring.netflix.db.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

}
