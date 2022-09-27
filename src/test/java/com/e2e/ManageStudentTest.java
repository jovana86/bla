package com.e2e;

import com.e2e.config.model.Student;
import com.e2e.config.pages.student.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManageStudentTest extends TestBase {

    StudentPage studentPage;
    StudentCreatePage studentCreatePage;
    StudentDetailsPage studentReadPage;
    StudentEditPage studentEditPage;
    StudentDeletePage studentDeletePage;


    DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String firstNameStudent = "Demo003";
    private String lastNameStudent = "QA103";
    private String newNameStudent = "QA103-EDIT";
    private String enrollmentDateStudent = "01/01/2022";

    String studentUrl = "http://wagner.wang/ContosoUniversity/Students";

    @Test(priority = 1, enabled = true, description = "Successful student creation")
    public void testStudentCreate() {
        TestBase.driver.get(studentUrl);
        Student student = new Student(lastNameStudent, firstNameStudent, enrollmentDateStudent);
        studentPage = new StudentPage(TestBase.driver, waitTime);
        studentCreatePage = studentPage.createNewStudent();
        studentCreatePage.enterNewStudentData(student);

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        studentCreatePage.createNewStudent();

        Assert.assertTrue(studentPage.isStudentCreated(student.getFirstName()));
    }

    @Test(priority = 2,
            description = "Successful student record reading")
    public void testStudentRead() {
        TestBase.driver.get(studentUrl);
        Student student = new Student(lastNameStudent, firstNameStudent, enrollmentDateStudent);
        studentPage = new StudentPage(TestBase.driver, waitTime);
        studentReadPage = studentPage.readExistingStudent(student.getFirstName(), student.getLastName());

        Assert.assertTrue(studentReadPage.checkFirstName(student.getFirstName()));
        Assert.assertTrue(studentReadPage.checkLastName(student.getLastName()));

        LocalDate eDate = LocalDate.parse(student.getEnrollmentDate(), oldPattern);
        student.setEnrollmentDate(eDate.format(newPattern));

        Assert.assertTrue(studentReadPage.checkEnrollmentDate(student.getEnrollmentDate()));
    }

    @Test(priority = 3,
            enabled = true,
            description = "Successful student record editing")
    public void testStudentEdit() {
        TestBase.driver.get(studentUrl);
        Student student = new Student(lastNameStudent, firstNameStudent, enrollmentDateStudent);

        studentPage = new StudentPage(TestBase.driver, waitTime);
        studentEditPage = studentPage.editExistingStudent(student.getFirstName(), student.getLastName());
        student.setFirstName(newNameStudent);
        studentEditPage.editStudentData(student);
        studentEditPage.saveEditedStudentData();

        Assert.assertTrue(studentPage.isStudentCreated(student.getFirstName()));

    }

    @Test(priority = 4,
            enabled = true,
            description = "Successful student record deletion")
    public void testStudentDelete() {
        TestBase.driver.get(studentUrl);
        Student student = new Student(lastNameStudent, firstNameStudent, enrollmentDateStudent);

        studentPage = new StudentPage(TestBase.driver, waitTime);
        studentDeletePage = studentPage.deleteExistingStudent(student.getFirstName(), student.getLastName());
        studentDeletePage.deleteExistingStudent(student);

        Assert.assertTrue(studentPage.isStudentDeleted(student.getFirstName()));
    }

}
