package controller;

import model.Student;
import model.Subject;
import model.Grade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ControllerTest {

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student("Young", "Leosia", 20);
    }

    @Test
    public void checkHighAverageTest() {
        //given
        Subject math = new Subject("Mathematics");
        Grade grade1 = new Grade(6, 3, math);
        Grade grade2 = new Grade(6, 3, math);
        Grade grade3 = new Grade(3, 2, math);
        Grade grade4 = new Grade(4, 1, math);
        Grade grade5 = new Grade(6, 1, math);
        student.addGrade(grade1);
        student.addGrade(grade2);
        student.addGrade(grade3);
        student.addGrade(grade4);
        student.addGrade(grade5);

        //when
        double average = student.calculateWeightedAverage(math);

        //then

        Assertions.assertTrue(Controller.checkAverage(average));
    }

    @Test
    public void checkLowAverageTest() {
        //given
        Subject math = new Subject("Mathematics");
        Grade grade1 = new Grade(1, 3, math);
        Grade grade2 = new Grade(1, 3, math);
        Grade grade3 = new Grade(2, 2, math);
        Grade grade4 = new Grade(1, 1, math);
        Grade grade5 = new Grade(2, 1, math);
        student.addGrade(grade1);
        student.addGrade(grade2);
        student.addGrade(grade3);
        student.addGrade(grade4);
        student.addGrade(grade5);

        //when
        double average = student.calculateWeightedAverage(math);

        //then
        Assertions.assertFalse(Controller.checkAverage(average));
    }

    @Test
    public void checkHighAttendanceTest() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 55;
        math.setAmountOfClasses(60);
        student.setAttendance(math, mathAttendance);

        //when
        double attendance = student.calculateSubjectAttendance(math);

        //then
        Assertions.assertTrue(Controller.checkAttendance(attendance));
    }

    @Test
    public void checkLowAttendanceTest() {
        //given
        Subject math = new Subject("Mathematics");
        int mathAttendance = 15;
        math.setAmountOfClasses(60);
        student.setAttendance(math, mathAttendance);

        //when
        double attendance = student.calculateSubjectAttendance(math);

        //then
        Assertions.assertFalse(Controller.checkAttendance(attendance));
    }

    @Test
    public void getListOfTroublesomeSubjectsTest() {
        //given
        Subject math = new Subject("Mathematics");
        Subject music = new Subject("Music");
        Subject pe = new Subject("Physical Education");
        Grade grade1 = new Grade(1, 3, math);
        Grade grade2 = new Grade(1, 3, math);
        Grade grade3 = new Grade(2, 2, math);
        Grade grade4 = new Grade(1, 1, math);
        Grade grade5 = new Grade(2, 1, math);
        Grade grade6 = new Grade(2, 2, music);
        Grade grade7 = new Grade(3, 1, music);
        Grade grade8 = new Grade(2, 3, music);
        Grade grade9 = new Grade(5, 3, pe);
        Grade grade10 = new Grade(5, 3, pe);
        Grade grade11 = new Grade(5, 1, pe);
        Grade grade12 = new Grade(4, 2, pe);
        student.addGrade(grade1);
        student.addGrade(grade2);
        student.addGrade(grade3);
        student.addGrade(grade4);
        student.addGrade(grade5);
        student.addGrade(grade6);
        student.addGrade(grade7);
        student.addGrade(grade8);
        student.addGrade(grade9);
        student.addGrade(grade10);
        student.addGrade(grade11);
        student.addGrade(grade12);

        //when
        List<Subject> subjects = Controller.getListOfTroublesomeSubjects(student);
        List<Subject> expected = new ArrayList<>();
        expected.add(math);

        //then
        Assertions.assertIterableEquals(expected, subjects);
    }

    @Test
    public void getListOfMostMissedSubjectsTest() {
        //given
        Subject math = new Subject("Mathematics");
        Subject music = new Subject("Music");
        Subject pe = new Subject("Physical Education");
        int mathAttendance = 15;
        int musicAttendance = 55;
        int peAttendance = 31;
        math.setAmountOfClasses(60);
        music.setAmountOfClasses(60);
        pe.setAmountOfClasses(60);
        student.setAttendance(math, mathAttendance);
        student.setAttendance(music, musicAttendance);
        student.setAttendance(pe, peAttendance);

        //when
        List<Subject> subjects = Controller.getListOfMostMissedSubjects(student);
        List<Subject> expected = new ArrayList<>();
        expected.add(math);

        //then
        Assertions.assertIterableEquals(expected, subjects);
    }
}
