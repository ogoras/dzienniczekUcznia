package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class CalculateAverageGradeTest {

    private Student student;
    private List<Grade> grades;

    @BeforeEach
    public void setUp(){
        student = new Student("X", "Y", 10);
        grades = student.getGrades();
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
        double expected = 4.375;

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
        double expected = 4.8333333333;

        Assertions.assertEquals(expected, calculatedMathAverage, 1e-10);
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
        double expected = 3.964646465;

        Assertions.assertEquals(expected, calculatedAverage, 1e-5);
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
