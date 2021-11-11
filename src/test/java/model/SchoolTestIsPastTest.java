package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class SchoolTestIsPastTest {

    private SchoolTest test;
    private LocalDate date;
    private String name = "test name";

    @BeforeEach
    public void setUp(){
        test = new SchoolTest();
    }

    @Test
    public void isPast_should_returnTrue_when_testDateIsInPast(){
        LocalDate now = LocalDate.now();

        date = now.minus(100, ChronoUnit.YEARS);
        test.setDate(date);
        Assertions.assertTrue(test.isPast());

        date = now.minus(1, ChronoUnit.WEEKS);
        test.setDate(date);
        Assertions.assertTrue(test.isPast());

        date = now.minus(1, ChronoUnit.MILLIS);
        test.setDate(date);
        Assertions.assertTrue(test.isPast());
    }

    @Test
    public void isPast_should_returnFalse_when_testDateIsNotInPast(){
        LocalDate now = LocalDate.now();

        date = now.plus(100, ChronoUnit.YEARS);
        test.setDate(date);
        Assertions.assertTrue(test.isPast());

        date = now.plus(1, ChronoUnit.WEEKS);
        test.setDate(date);
        Assertions.assertTrue(test.isPast());

        date = now.plus(1, ChronoUnit.MILLIS);
        test.setDate(date);
        Assertions.assertTrue(test.isPast());
    }

}