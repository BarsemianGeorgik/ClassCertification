package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "applicant")
public class Applicant {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @Column(name = "course")
    private String course;

 /*   @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course", referencedColumnName = "name")*/
/*    @Column(name = "course")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name")*/

   // private Course course;

    public Applicant() {
    }

 /*   public Applicant(String name, String email, String phone, String address, String status, Course course) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.course = course;
    }*/

    public Applicant(String name, String email, String phone, String address, String status, String course) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

/*    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }*/
    public String getCourse() {
    return course;
}

    public void setCourse(String course) {
        this.course = course;
    }

}
