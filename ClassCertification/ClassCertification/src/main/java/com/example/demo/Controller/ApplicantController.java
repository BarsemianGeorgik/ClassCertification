package com.example.demo.Controller;


import com.example.demo.Entity.Applicant;
import com.example.demo.Service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/applicants")
public class ApplicantController {
    @Autowired
    ApplicantService applicantService;

    @GetMapping("")
    public List<Applicant> list() {
        return applicantService.listAll();
    }

    @GetMapping("/search")
    public List<Applicant> searchApplicant(@RequestParam Map<String,String> allParams ) {
        return applicantService.processSearch(allParams);
    }

    @PostMapping("/")
    public void addApplicant(@RequestBody Map applicant) throws Exception {
        applicantService.save(applicant);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateApplicant(@RequestBody Map applicant, @PathVariable String name) {
        try {
            applicantService.updateApplicant(applicant, name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{name}")
    public void deleteParticipant(@PathVariable String name) {
        applicantService.delete(name);
    }
}

