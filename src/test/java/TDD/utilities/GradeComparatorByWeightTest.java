package TDD.utilities;

import TDD.model.Grade;
import TDD.model.Student;
import TDD.model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GradeComparatorByWeightTest {

    private static Subject subject;
    private static Student student;

    @BeforeAll
    public static void setUp() {
        subject = new Subject("English");
        student = new Student("Janusz", "Jabłko", 7);
    }

    @Test
    public void TestComparingGradesByWeightWithFirstWeightBigger() {
        //given
        int weightOfFirstGrade = 6;
        int weightOfSecondGrade = 4;

        Grade grade1 = new Grade(2, weightOfFirstGrade, "dwa", LocalDate.of(2021, 5, 5), subject, student, null);
        Grade grade2 = new Grade(4, weightOfSecondGrade, "", LocalDate.of(2021, 8, 5), subject, student, null);

        GradeComparatorByWeight comparator = new GradeComparatorByWeight();

        //when
        int result = comparator.compare(grade1, grade2);
        int expected = 1;

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestComparingGradesByWeightWithEqualWeights() {
        //given
        int weightOfFirstGrade = 2;
        int weightOfSecondGrade = 2;

        Grade grade1 = new Grade(6, weightOfFirstGrade, "dwa", LocalDate.of(2021, 9, 15), subject, student, null);
        Grade grade2 = new Grade(1, weightOfSecondGrade, "", LocalDate.of(2020, 8, 22), subject, student, null);

        GradeComparatorByWeight comparator = new GradeComparatorByWeight();

        //when
        int result = comparator.compare(grade1, grade2);
        int expected = 0;

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestComparingGradesByWeightWithFirstWeightSmaller() {
        //given
        int weightOfFirstGrade = 1;
        int weightOfSecondGrade = 4;

        Grade grade1 = new Grade(5, weightOfFirstGrade, "", LocalDate.of(2019, 5, 5), subject, student, null);
        Grade grade2 = new Grade(4, weightOfSecondGrade, "", LocalDate.of(2021, 1, 24), subject, student, null);

        GradeComparatorByWeight comparator = new GradeComparatorByWeight();

        //when
        int result = comparator.compare(grade1, grade2);
        int expected = -1;

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestSortingGradesByWeightWithComparator() {
        //given
        int biggestWeight = 7;
        int middleWeight = 5;
        int smallestWeight = 4;

        List<Grade> list = new ArrayList<>(4);
        list.add(new Grade(1, middleWeight, "", LocalDate.of(2021, 4, 4), subject, student, null));
        list.add(new Grade(2, smallestWeight, "", LocalDate.of(2021, 4, 4), subject, student, null));
        list.add(new Grade(3, biggestWeight, "e", LocalDate.of(2021, 2, 19), subject, student, null));
        list.add(new Grade(4, middleWeight, "e", LocalDate.of(2020, 2, 2), subject, student, null));

        GradeComparatorByWeight comparator = new GradeComparatorByWeight();

        //when
        list.sort(comparator);

        //then
        Assertions.assertTrue(list.get(0).getWeight() == smallestWeight && list.get(1).getWeight() == middleWeight
                && list.get(2).getWeight() == middleWeight && list.get(3).getWeight() == biggestWeight
        );
    }

    @Test
    public void testWithNullAsFirstGrade() {
        //given
        Grade firstGrade = null;
        Grade secondGrade = new Grade(5, 5, "five", LocalDate.of(2015, 5, 5), subject, student, null);

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GradeComparatorByWeight().compare(firstGrade, secondGrade));
    }

    @Test
    public void testWithNullAsSecondGrade() {
        //given
        Grade firstGrade = new Grade(2, 3, "", LocalDate.of(2020, 2, 14), subject, student, null);
        Grade secondGrade = null;

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GradeComparatorByWeight().compare(firstGrade, secondGrade));
    }

    @Test
    public void testWithNullsAsBothGrades() {
        //given
        Grade firstGrade = null;
        Grade secondGrade = null;

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GradeComparatorByWeight().compare(firstGrade, secondGrade));
    }
}
