package controller;

import model.Student;
import model.Subject;

import java.util.List;

public class Controller {

    private static final double MINIMAL_ATTENDANCE_THRESHOLD = 0.5;

    static public boolean checkAverage(double average) {
        return average >= 2;
    }

    static public boolean checkAttendance(double attendance) {
        return attendance >= MINIMAL_ATTENDANCE_THRESHOLD;
    }

    static public List<Subject> getListOfTroublesomeSubjects(Student student) {
        verifyStudent(student);
        return student.getSubjectsWithGrades().stream()
                .filter(subject -> student.calculateWeightedAverageGradeForSubject(subject) < 2).toList();
    }

    static public List<Subject> getListOfMostMissedSubjects(Student student) {
        verifyStudent(student);
        return student.getAttendedSubjects().stream()
                .filter(subject -> student.calculateSubjectAttendance(subject) < MINIMAL_ATTENDANCE_THRESHOLD).toList();
    }

    private static void verifyStudent(Student student) {
        if (student == null || student.getAttendedSubjects() == null) {
            throw new IllegalArgumentException("Student and his subjects cannot be null.");
        }
    }
}
