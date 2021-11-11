package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SchoolTestCompareToTest {

    private SchoolTest test;
    private LocalDate date;
    private String name = "test name";
    private SchoolTest testToCompare;
    private LocalDate dateToCompare;
    private String nameToCompare = "test name to compare";

    @BeforeEach
    public void setUp(){
        test = new SchoolTest();
        testToCompare = new SchoolTest();
    }

    @Test
    public void compareTo_should_returnNegativeValue_when_testIsEarlierThanTheOtherTest(){
        date = LocalDate.now();

        dateToCompare = date.plus(100, ChronoUnit.YEARS);
        test.setDate(date);
        testToCompare.setDate(dateToCompare);
        Assertions.assertTrue(test.compareTo(testToCompare) < 0);

        dateToCompare = date.plus(1, ChronoUnit.WEEKS);
        test.setDate(date);
        testToCompare.setDate(dateToCompare);
        Assertions.assertTrue(test.compareTo(testToCompare) < 0);

        dateToCompare = date.plus(1, ChronoUnit.MILLIS);
        test.setDate(date);
        testToCompare.setDate(dateToCompare);
        Assertions.assertTrue(test.compareTo(testToCompare) < 0);
    }

    @Test
    public void compareTo_should_return0_when_testIsAtTheSameTimeAsOtherTest(){
        date = LocalDate.now();
        test.setDate(date);
        testToCompare.setDate(date);
        Assertions.assertEquals(test.compareTo(testToCompare), 0);
    }

    @Test
    public void compareTo_should_returnPositiveValue_when_testIsLaterThanTheOtherTest(){
        date = LocalDate.now();

        dateToCompare = date.minus(100, ChronoUnit.YEARS);
        test.setDate(date);
        testToCompare.setDate(dateToCompare);
        Assertions.assertTrue(test.compareTo(testToCompare) > 0);

        dateToCompare = date.minus(1, ChronoUnit.WEEKS);
        test.setDate(date);
        testToCompare.setDate(dateToCompare);
        Assertions.assertTrue(test.compareTo(testToCompare) > 0);

        dateToCompare = date.minus(1, ChronoUnit.MILLIS);
        test.setDate(date);
        testToCompare.setDate(dateToCompare);
        Assertions.assertTrue(test.compareTo(testToCompare) > 0);
    }
}
