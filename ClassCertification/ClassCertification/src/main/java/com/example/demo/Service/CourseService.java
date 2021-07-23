package com.example.demo.Service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.demo.Entity.Course;
import com.example.demo.Repository.ApplicantRepository;
import com.example.demo.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository repo;

    @PersistenceContext
    private EntityManager em;

    public List<Course> listAll() {
        return repo.findAll();
    }

    public void save(Course course) {
        repo.save(course);
    }

    public void delete(String name) {
        repo.deleteByName(name);
    }

    public List<Course> processSearch(String name) {
        return repo.findByName(name);
    }

    public void updateCourse(Map<String, String> courseMap, String name){
        Course applicant = repo.findByName(name).get(0);
        CourseRepository.updateCourse(em, courseMap.get("startDate"), courseMap.get("endDate"),
                courseMap.get("teacherName"), courseMap.get("description"),name);
    }

}

