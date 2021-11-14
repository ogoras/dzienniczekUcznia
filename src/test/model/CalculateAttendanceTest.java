package model;

import model.Student;
import model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CalculateAttendanceTest {

    private Map<Subject, Integer> attendances;
    private Student student;

    @BeforeEach
    public void setUp(){
        student = new Student("X", "Y", 10);
        attendances = student.getAttendances();
    }

    @Test
    public void calculateAttendanceForOneSubject() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 2;
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        attendances.put(math, mathAttendance);

        //when
        double calculatedAttendance = student.calculateSubjectAttendance(math);

        //then
        double expected = 0.4;

        Assertions.assertEquals(expected, calculatedAttendance);
    }

    @Test
    public void calculateAttendanceForOneSubject_whenAttendanceIsZero() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 0;
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        attendances.put(math, mathAttendance);

        //when
        double calculatedAttendance = student.calculateSubjectAttendance(math);

        //then
        double expected = 0;

        Assertions.assertEquals(expected, calculatedAttendance);
    }

    @Test
    public void calculateAttendanceForOneSubject_whenAmountOfClassesIsZero() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 0;
        attendances.put(math, mathAttendance);

        //when
        double calculatedAttendance = student.calculateSubjectAttendance(math);

        //then
        double expected = 0;

        Assertions.assertEquals(expected, calculatedAttendance);
    }

    @Test
    public void calculateAttendanceForOneSubject_whenAttendanceEqualsAmountOfClasses() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 3;
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        attendances.put(math, mathAttendance);

        //when
        double calculatedAttendance = student.calculateSubjectAttendance(math);

        //then
        double expected = 1;

        Assertions.assertEquals(expected, calculatedAttendance);
    }

    @Test()
    public void calculateAttendanceForOneSubject_whenAttendanceIsGreaterThanAmountOfClasses() {
        //given
        Subject math = new Subject("Mathematics");
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        int mathAttendance = math.getAmountOfClasses() + 1;
        attendances.put(math, mathAttendance);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.calculateSubjectAttendance(math));
    }

    @Test
    public void calculateGeneralAttendance() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 3;
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        math.classTookPlace();
        attendances.put(math, mathAttendance);

        Subject biology = new Subject("Biology");
        int biologyAttendance = 5;
        biology.classTookPlace();
        biology.classTookPlace();
        biology.classTookPlace();
        biology.classTookPlace();
        biology.classTookPlace();
        attendances.put(biology, biologyAttendance);

        Subject chemistry = new Subject("Chemistry");
        int chemistryAttendance = 1;
        chemistry.classTookPlace();
        attendances.put(chemistry, chemistryAttendance);

        //when
        double calculatedAttendance = student.calculateGeneralAttendance();

        //then
        double expected = 0.9;

        Assertions.assertEquals(expected, calculatedAttendance);
    }
}
