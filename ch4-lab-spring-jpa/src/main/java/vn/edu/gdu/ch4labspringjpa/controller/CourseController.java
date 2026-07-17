package vn.edu.gdu.ch4labspringjpa.controller;

import org.springframework.web.bind.annotation.*;
import vn.edu.gdu.ch4labspringjpa.entity.Course;
import vn.edu.gdu.ch4labspringjpa.repository.CourseRepository;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }
}