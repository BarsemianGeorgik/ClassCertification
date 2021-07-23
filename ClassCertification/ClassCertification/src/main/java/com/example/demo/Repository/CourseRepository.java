package com.example.demo.Repository;

import com.example.demo.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer>{

    @Query(value = "UPDATE course u SET startDate =?, endDate = ?, teacherName = ?, description =? WHERE u.name = ?", nativeQuery = true)
    static void updateCourse(EntityManager em, String startDate, String endDate, String teacherName, String description, String name){
        em.createNativeQuery("UPDATE course u SET startDate =?, endDate = ?, teacherName = ?, description =? WHERE u.name = ?")
                .setParameter(1, startDate)
                .setParameter(2, endDate)
                .setParameter(3, teacherName)
                .setParameter(4, description)
                .setParameter(5, name)
                .executeUpdate();
    }

    void deleteByName(String name);

    List<Course> findByName(String course);

}
