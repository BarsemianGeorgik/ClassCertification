package com.example.demo.Repository;

import com.example.demo.Entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer>{
    List<Applicant> findByName(String name);
    List<Applicant> findByEmail(String email);
    List<Applicant> findByStatus(String status);

    @Query(value = "SELECT * FROM applicant WHERE course=?1", nativeQuery = true)
    List<Applicant> findByCourse(String course);

    @Query(value = "INSERT INTO applicant (name, email, phone, address, status, course) VALUES (?,?,?,?,?,?)", nativeQuery = true)
    static void saveParticipant(EntityManager em, String name, String email, String phone, String address, String status, String course){
         em.createNativeQuery("INSERT INTO applicant (name, email, phone, address, status, course) VALUES (?,?,?,?,?,?)")
                 .setParameter(1, name)
                 .setParameter(2, email)
                 .setParameter(3, phone)
                 .setParameter(4, address)
                 .setParameter(5, status)
                 .setParameter(6, course)
                 .executeUpdate();
    }

    @Query(value = "UPDATE applicant u SET email =?, phone = ?, address = ?, status =? WHERE u.name = ?", nativeQuery = true)
    static void updateApplicant(EntityManager em, String email, String phone, String address, String status, String name){
        em.createNativeQuery("UPDATE applicant u SET email =?, phone = ?, address = ?, status =? WHERE u.name = ?")
                .setParameter(1, email)
                .setParameter(2, phone)
                .setParameter(3, address)
                .setParameter(4, status)
                .setParameter(5, name)
                .executeUpdate();
    }

    void deleteByName(String name);


}
