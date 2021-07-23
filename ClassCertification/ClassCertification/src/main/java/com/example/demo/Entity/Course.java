package com.example.demo.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "teachername")
    private String teacherName;

    @Column(name = "description")
    private String description;

    public Course (){

    }

    public Course(String name, Date startDate, Date endDate, String teacherName, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacherName = teacherName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
