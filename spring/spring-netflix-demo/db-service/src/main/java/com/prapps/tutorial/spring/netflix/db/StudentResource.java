package com.prapps.tutorial.spring.netflix.db;


import com.prapps.tutorial.spring.netflix.db.entity.Student;
import com.prapps.tutorial.spring.netflix.db.repo.CourseRepo;
import com.prapps.tutorial.spring.netflix.db.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/db/student")
public class StudentResource {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    @Autowired
    public StudentResource(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @GetMapping("/{name}")
    public List<Student> findStudent(@PathVariable String name) {
        System.out.println("hashCode: "+this.hashCode());
        return studentRepo.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name);
    }

    @PostMapping("/register")
    public Student register(@RequestParam("studentId") Long studentId, @RequestParam("courseId") Long courseId) {
        Student student = studentRepo.findById(studentId).get();
        student.getRegisteredCourses().add(courseRepo.findById(courseId).get());
        return studentRepo.save(student);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }
}
