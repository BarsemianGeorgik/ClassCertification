package com.example.demo.Controller;

import com.example.demo.Service.CertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/generateCertificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;


    @GetMapping("")
    public void generateCertificate() throws IOException, DocumentException {
        certificateService.generateCertificate();
    }
}
