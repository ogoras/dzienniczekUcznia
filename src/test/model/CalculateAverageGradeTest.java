package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class CalculateAverageGradeTest {

    private Student student;
    private List<Grade> grades;

    @BeforeEach
    public void setUp(){
        student = new Student("X", "Y", 10);
        grades = new ArrayList<>();
    }

    @Test
    public void calculateWeightedAverageForOneSubject_whenOnlyOneSubjectInList() {
        //given
        Subject math = new Subject("Mathematics");
        Grade grade1 = new Grade(4, 2, math);
        Grade grade2 = new Grade(5, 3, math);
        Grade grade3 = new Grade(2, 1, math);
        Grade grade4 = new Grade(5, 2, math);
        grades.add(grade1);
        grades.add(grade2);
        grades.add(grade3);
        grades.add(grade4);

        //when
        double calculatedAverage = student.calculateWeightedAverageGradeForSubject(math);

        //then
        double expected = (double) (grade1.getValue() * grade1.getWeight() +
                                    grade2.getValue() * grade2.getWeight() +
                                    grade3.getValue() * grade3.getWeight() +
                                    grade4.getValue() * grade4.getWeight()) /
                (grade1.getWeight() + grade2.getWeight() + grade3.getWeight() + grade4.getWeight());

        Assertions.assertEquals(expected, calculatedAverage);
    }

    @Test
    public void calculateWeightedAverageForOneSubject_whenManySubjectsInList() {
        //given
        Subject math = new Subject("Mathematics");
        Grade mathGrade1 = new Grade(6, 2, math);
        Grade mathGrade2 = new Grade(5, 3, math);
        Grade mathGrade3 = new Grade(2, 1, math);
        grades.add(mathGrade1);
        grades.add(mathGrade2);
        grades.add(mathGrade3);

        Subject biology = new Subject("Biology");
        Grade biologyGrade1 = new Grade(3, 2, biology);
        Grade biologyGrade2 = new Grade(5, 1, biology);
        grades.add(biologyGrade1);
        grades.add(biologyGrade2);

        Subject chemistry = new Subject("Chemistry");
        Grade chemistryGrade1 = new Grade(4, 2, chemistry);
        Grade chemistryGrade2 = new Grade(1, 1, chemistry);
        Grade chemistryGrade3 = new Grade(5, 4, chemistry);
        Grade chemistryGrade4 = new Grade(2, 4, chemistry);
        grades.add(chemistryGrade1);
        grades.add(chemistryGrade2);
        grades.add(chemistryGrade3);
        grades.add(chemistryGrade4);

        //when
        double calculatedMathAverage = student.calculateWeightedAverageGradeForSubject(math);

        //then
        double expected = (double) (mathGrade1.getValue() * mathGrade1.getWeight() +
                mathGrade2.getValue() * mathGrade2.getWeight() +
                mathGrade3.getValue() * mathGrade3.getWeight()) /
                (mathGrade1.getWeight() + mathGrade2.getWeight() + mathGrade3.getWeight());

        Assertions.assertEquals(expected, calculatedMathAverage);
    }

    @Test
    public void calculateWeightedAverageForOneSubject_whenNoGradesForSubject() {
        //given
        Subject math = new Subject("Mathematics");

        //when
        double calculatedAverage = student.calculateWeightedAverageGradeForSubject(math);

        //then
        double expected = 0;

        Assertions.assertEquals(expected, calculatedAverage);
    }

    @Test
    public void calculateGeneralArithmeticAverageGrade() {
        //given
        Subject math = new Subject("Mathematics");
        Grade mathGrade1 = new Grade(6, 2, math);
        Grade mathGrade2 = new Grade(3, 3, math);
        Grade mathGrade3 = new Grade(2, 1, math);
        grades.add(mathGrade1);
        grades.add(mathGrade2);
        grades.add(mathGrade3);

        Subject biology = new Subject("Biology");
        Grade biologyGrade1 = new Grade(3, 2, biology);
        Grade biologyGrade2 = new Grade(5, 4, biology);
        grades.add(biologyGrade1);
        grades.add(biologyGrade2);

        Subject chemistry = new Subject("Chemistry");
        Grade chemistryGrade1 = new Grade(4, 2, chemistry);
        Grade chemistryGrade2 = new Grade(1, 1, chemistry);
        Grade chemistryGrade3 = new Grade(6, 4, chemistry);
        Grade chemistryGrade4 = new Grade(2, 4, chemistry);
        grades.add(chemistryGrade1);
        grades.add(chemistryGrade2);
        grades.add(chemistryGrade3);
        grades.add(chemistryGrade4);

        //when
        double calculatedAverage = student.calculateGeneralArithmeticAverageGrade();

        //then
        double mathAverageGrade = (double) (mathGrade1.getValue() * mathGrade1.getWeight() +
                mathGrade2.getValue() * mathGrade2.getWeight() +
                mathGrade3.getValue() * mathGrade3.getWeight()) /
                (mathGrade1.getWeight() + mathGrade2.getWeight() + mathGrade3.getWeight());

        double biologyAverageGrade = (double) (biologyGrade1.getValue() * biologyGrade1.getWeight() +
                biologyGrade2.getValue() * biologyGrade2.getWeight()) /
                (biologyGrade1.getWeight() + biologyGrade2.getWeight());

        double chemistryAverageGrade = (double) (chemistryGrade1.getValue() * chemistryGrade1.getWeight() +
                chemistryGrade2.getValue() * chemistryGrade2.getWeight() +
                chemistryGrade3.getValue() * chemistryGrade3.getWeight() +
                chemistryGrade4.getValue() * chemistryGrade4.getWeight()) /
                (chemistryGrade1.getWeight() + chemistryGrade2.getWeight() + chemistryGrade3.getWeight() + chemistryGrade4.getWeight());

        double expected = (mathAverageGrade + biologyAverageGrade + chemistryAverageGrade) / 3;

        Assertions.assertEquals(expected, calculatedAverage);
    }

    @Test
    public void calculateGeneralArithmeticAverageGrade_whenNoGrades() {
        //when
        double calculatedAverageGrade = student.calculateGeneralArithmeticAverageGrade();

        //then
        double expected = 0;

        Assertions.assertEquals(expected, calculatedAverageGrade);
    }

}
