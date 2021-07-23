package com.example.demo.Controller;

import com.example.demo.Entity.Applicant;
import com.example.demo.Entity.Course;
import com.example.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("")
    public List<Course> courseList() {
        return courseService.listAll();
    }


    @GetMapping("/search")
    public List<Course> searchCourse(@RequestParam(name = "course") String name ) {
        return courseService.processSearch(name);
    }

    @PostMapping("/")
    public void addCourse(@RequestBody Course course) {
        courseService.save(course);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateCourse(@RequestBody Map course, @PathVariable String name) {
        try {
            courseService.updateCourse(course, name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{name}")
    public void deleteCourse(@PathVariable String name) {
        courseService.delete(name);
    }
}
