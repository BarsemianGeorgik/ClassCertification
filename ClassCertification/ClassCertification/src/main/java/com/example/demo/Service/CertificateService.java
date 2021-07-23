package com.example.demo.Service;

import com.example.demo.Entity.Applicant;
import com.example.demo.Entity.Course;
import com.example.demo.Repository.ApplicantRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Transactional
public class CertificateService {

    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private CourseService courseService;

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallnormal = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);

    public void generateCertificate() throws IOException, DocumentException {

        List<Applicant> completedApplicants = applicantService.findByStatus("COMPLETED");

        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("ListOfCertifiedParticipants.zip"));
        for (int i = 0; i < completedApplicants.size(); i++) {
            ZipEntry entry = new ZipEntry(completedApplicants.get(i).getName().replaceAll("\\s+","") + "Certification"+ ".pdf");
            zip.putNextEntry(entry);
            Document document = new Document();
            PdfWriter writer =
                    PdfWriter.getInstance(document, zip);
            writer.setCloseStream(false);

            document.open();

            Paragraph paragraph = new Paragraph("Certificate",subFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            addEmptyLine(paragraph,2);
            document.add(paragraph);

            Course course = courseService.processSearch(completedApplicants.get(i).getCourse()).get(0);

            //course title
            paragraph = new Paragraph(course.getName(),subFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            addEmptyLine(paragraph,1);
            document.add(paragraph);

            //description
            paragraph = new Paragraph(course.getDescription(),smallnormal);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            addEmptyLine(paragraph,2);
            document.add(paragraph);

            paragraph = new Paragraph("Applicant Name: "+completedApplicants.get(i).getName());
            paragraph.setAlignment(Element.ALIGN_LEFT );
            addEmptyLine(paragraph,1);
            document.add(paragraph);

            paragraph = new Paragraph("Teacher Name: "+course.getTeacherName());
            paragraph.setAlignment(Element.ALIGN_LEFT );
            addEmptyLine(paragraph,1);
            document.add(paragraph);

            paragraph = new Paragraph("Start Date: "+course.getStartDate());
            paragraph.setAlignment(Element.ALIGN_LEFT );
            addEmptyLine(paragraph,1);
            document.add(paragraph);

            paragraph = new Paragraph("End Date: "+course.getEndDate());
            paragraph.setAlignment(Element.ALIGN_LEFT );
            document.add(paragraph);

            document.close();
            zip.closeEntry();
        }
        zip.close();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
