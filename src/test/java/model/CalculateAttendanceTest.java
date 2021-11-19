package model;

import model.Student;
import model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateAttendanceTest {

    private Student student;

    @BeforeEach
    public void setUp(){
        student = new Student("X", "Y", 10);
    }

    @Test
    public void calculateAttendanceForOneSubject() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 2;
        math.setAmountOfClasses(5);
        student.setAttendance(math, mathAttendance);

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
        math.setAmountOfClasses(4);
        student.setAttendance(math, mathAttendance);

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
        math.setAmountOfClasses(0);
        student.setAttendance(math, mathAttendance);

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
        math.setAmountOfClasses(3);
        student.setAttendance(math, mathAttendance);

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
        math.setAmountOfClasses(4);
        int mathAttendance = math.getAmountOfClasses() + 1;
        student.setAttendance(math, mathAttendance);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.calculateSubjectAttendance(math));
    }

    @Test
    public void calculateGeneralAttendance() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 3;
        math.setAmountOfClasses(4);
        student.setAttendance(math, mathAttendance);

        Subject biology = new Subject("Biology");
        int biologyAttendance = 5;
        biology.setAmountOfClasses(5);
        student.setAttendance(biology, biologyAttendance);

        Subject chemistry = new Subject("Chemistry");
        int chemistryAttendance = 1;
        chemistry.setAmountOfClasses(1);
        student.setAttendance(chemistry, chemistryAttendance);

        //when
        double calculatedAttendance = student.calculateGeneralAttendance();

        //then
        double expected = 0.9;

        Assertions.assertEquals(expected, calculatedAttendance);
    }
}
