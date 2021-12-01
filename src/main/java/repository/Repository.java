package repository;

import model.Grade;
import model.SchoolTest;
import model.Student;
import model.Subject;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Repository {
    public Student getStudent(int i) {
        Student student = new Student("Jan", "Kowalski", 0);
        Subject math = new Subject("Mathematics");
        int mathAttendance = 3;
        math.setAmountOfClasses(4);
        student.setAttendance(math, mathAttendance);
        student.addSubject(math);

        Subject biology = new Subject("Biology");
        int biologyAttendance = 4;
        biology.setAmountOfClasses(6);
        student.setAttendance(biology, biologyAttendance);
        student.addSubject(biology);

        Subject chemistry = new Subject("Chemistry");
        int chemistryAttendance = 1;
        chemistry.setAmountOfClasses(1);
        student.setAttendance(chemistry, chemistryAttendance);
        student.addSubject(chemistry);

        Grade mathGrade1 = new Grade(6, 2, math);
        Grade mathGrade2 = new Grade(3, 3, math);
        Grade mathGrade3 = new Grade(2, 1, math);
        student.addGrade(mathGrade1);
        student.addGrade(mathGrade2);
        student.addGrade(mathGrade3);

        Grade biologyGrade1 = new Grade(3, 2, biology);
        Grade biologyGrade2 = new Grade(5, 4, biology);
        biologyGrade1.setDate(LocalDate.of(2020, 10, 25));
        student.addGrade(biologyGrade1);
        student.addGrade(biologyGrade2);

        Grade chemistryGrade1 = new Grade(4, 2, chemistry);
        Grade chemistryGrade2 = new Grade(1, 1, chemistry);
        Grade chemistryGrade3 = new Grade(6, 4, chemistry);
        Grade chemistryGrade4 = new Grade(2, 4, chemistry);
        chemistryGrade1.setDate(LocalDate.of(2021,10,20));
        chemistryGrade2.setDate(LocalDate.of(2020,11,28));
        chemistryGrade3.setDate(LocalDate.of(2021,1,18));
        chemistryGrade4.setDate(LocalDate.of(2021,1,1));
        student.addGrade(chemistryGrade1);
        student.addGrade(chemistryGrade2);
        student.addGrade(chemistryGrade3);
        student.addGrade(chemistryGrade4);

        SchoolTest test1 = new SchoolTest("DNA Exam",
                LocalDateTime.of(2010, 8, 17, 10, 5),
                "test about DNA");
        SchoolTest test2 = new SchoolTest("Carp anatomy",
                LocalDateTime.of(2021, 12, 24, 19, 0),
                "Carp anatomy for your Christmas Eve");
        biology.addTest(test1);
        biology.addTest(test2);

        return student; //TODO
    }
}
