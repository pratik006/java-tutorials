package com.prapps.tutorial.spring.netflix.db;

import com.prapps.tutorial.spring.netflix.db.entity.Course;
import com.prapps.tutorial.spring.netflix.db.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db/course")
public class CourseResource {

    private CourseRepo courseRepo;

    @Autowired
    public CourseResource(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @GetMapping("/all-names")
    public List<String> getAllCourseNames() {
        return courseRepo.findAll()
            .stream()
                .map(course -> course.getName())
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
}
