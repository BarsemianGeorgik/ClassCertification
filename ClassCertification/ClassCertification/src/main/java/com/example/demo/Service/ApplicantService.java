package com.example.demo.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.demo.Entity.Applicant;
import com.example.demo.Repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ApplicantService {

    @Autowired
    private ApplicantRepository repo;

    @Autowired
    private CourseService courseService;

    @PersistenceContext
    private EntityManager em;

    public List<Applicant> listAll() {
        return repo.findAll();
    }

    public List<Applicant> processSearch(Map param) {
        if (!param.isEmpty()){
            String key = (String) param.keySet().toArray()[0];
            switch(key)
            {
                case "name":
                    return repo.findByName((String) param.get(key));
                case "email":
                    return repo.findByEmail((String) param.get(key));
                case "course":
                    return repo.findByCourse((String) param.get(key));
                default:
                    return null;
            }
        }
        else{
            return null;
        }
    }

    public void save(Map<String,String> applicant) throws Exception {
        if(!applicant.isEmpty() && applicant.size()==6){
            if(courseService.processSearch(applicant.get("course")).isEmpty()){
                throw new Exception("Course doesn't exist");
            }
            ApplicantRepository.saveParticipant(em, applicant.get("name"),
                    applicant.get("email"),
                    applicant.get("phone"),
                    applicant.get("address"),
                    applicant.get("status"),
                    applicant.get("course"));
        }
    }
    public void updateApplicant(Map<String, String> applicantMap, String name){
        Applicant applicant = repo.findByName(name).get(0);
        ApplicantRepository.updateApplicant(em, applicantMap.get("email"), applicantMap.get("phone"),
                applicantMap.get("address"), applicantMap.get("status"),name);
    }

    public Applicant get(Integer id) {
        return repo.findById(id).get();
    }

    public List<Applicant> findByStatus(String status) {
        return repo.findByStatus(status);
    }
    public void delete(String name) {
        repo.deleteByName(name);
    }
}
