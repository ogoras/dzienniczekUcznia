package utilities;

import model.Grade;
import model.SchoolTest;
import model.Student;
import model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GradeComparatorByDateTest {

    private static Subject subject;
    private static Student student;

    @BeforeAll
    public static void setUp() {
        subject = new Subject("Math");
        student = new Student("Anna", "Arbuz", 7);
    }

    @Test
    public void TestComparingGradesByDateWithFirstDateOlder() {
        //given
        LocalDate dateOfFirstGrade = LocalDate.of(2021, 11, 10);
        LocalDate dateOfSecondGrade = LocalDate.of(2021, 11, 11);

        Grade grade1 = new Grade(2, 3, "dwa", dateOfFirstGrade, subject, student, null);
        Grade grade2 = new Grade(4, 6, "", dateOfSecondGrade, subject, student, new SchoolTest());

        GradeComparatorByDate comparator = new GradeComparatorByDate();

        //when
        int result = comparator.compare(grade1, grade2);
        int expected = -1;

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestComparingGradesByDateWithEqualDates() {
        //given
        LocalDate dateOfFirstGrade = LocalDate.of(2021, 10, 5);
        LocalDate dateOfSecondGrade = LocalDate.of(2021, 10, 5);

        Grade grade1 = new Grade(3, 6, "", dateOfFirstGrade, subject, student, null);
        Grade grade2 = new Grade(3, 1, "", dateOfSecondGrade, subject, student, null);

        GradeComparatorByDate comparator = new GradeComparatorByDate();

        //when
        int result = comparator.compare(grade1, grade2);
        int expected = 0;

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestComparingGradesByDateWithFirstDateYounger() {
        //given
        LocalDate dateOfFirstGrade = LocalDate.of(2021, 12, 1);
        LocalDate dateOfSecondGrade = LocalDate.of(2021, 11, 22);

        Grade grade1 = new Grade(2, 5, "", dateOfFirstGrade, subject, student, null);
        Grade grade2 = new Grade(5, 4, "", dateOfSecondGrade, subject, student, null);

        GradeComparatorByDate comparator = new GradeComparatorByDate();

        //when
        int result = comparator.compare(grade1, grade2);
        int expected = 1;

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestSortingGradesByDateWithComparator() {
        //given
        LocalDate youngestDate = LocalDate.of(2021, 12, 1);
        LocalDate middleDate = LocalDate.of(2021, 11, 7);
        LocalDate oldestDate = LocalDate.of(2021, 11, 2);

        List<Grade> list = new ArrayList<>(4);
        list.add(new Grade(1, 5, "", middleDate, subject, student, null));
        list.add(new Grade(2, 6, "", oldestDate, subject, student, null));
        list.add(new Grade(3, 7, "nie", youngestDate, subject, student, null));
        list.add(new Grade(4, 8, "wiem", middleDate, subject, student, null));

        GradeComparatorByDate comparator = new GradeComparatorByDate();

        //when
        list.sort(comparator);

        //then
        Assertions.assertTrue(
                list.get(0).getDate().equals(oldestDate) &&
                        list.get(1).getDate().equals(middleDate) &&
                        list.get(2).getDate().equals(middleDate) &&
                        list.get(3).getDate().equals(youngestDate)
        );
    }

    @Test
    public void testWithNullAsFirstGrade() {
        //given
        Grade firstGrade = null;
        Grade secondGrade = new Grade(2, 3, "Excellent!", LocalDate.of(2021, 4, 5), subject, student, null);

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GradeComparatorByDate().compare(firstGrade, secondGrade));
    }

    @Test
    public void testWithNullAsSecondGrade() {
        //given
        Grade firstGrade = new Grade(1, 1, "", LocalDate.of(2020, 3, 30), subject, student, null);
        Grade secondGrade = null;

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GradeComparatorByDate().compare(firstGrade, secondGrade));
    }

    @Test
    public void testWithNullAsBothGrades() {
        //given
        Grade firstGrade = null;
        Grade secondGrade = null;

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GradeComparatorByDate().compare(firstGrade, secondGrade));
    }
}
