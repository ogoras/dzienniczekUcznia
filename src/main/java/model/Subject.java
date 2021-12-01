package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Subject {

    private String subjectName;
    private int amountOfClasses = 0;
    private List<SchoolTest> tests;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
        tests = new ArrayList<>();
    }

    public int getAmountOfClasses() {
        return amountOfClasses;
    }

    public void setAmountOfClasses(int amount) {
        amountOfClasses = amount;
    }

    public String getName() {
        return subjectName;
    }

    public void addTest(SchoolTest test) {
        tests.add(test);
        test.setSubject(this);
        tests.sort(SchoolTest::compareTo);
    }

    public List<SchoolTest> getTests(){
        return tests;
    }

    public List<SchoolTest> getUpcomingTests() {
        List<SchoolTest> upcoming = new ArrayList<>();
        for(SchoolTest t : tests)
            if (!t.isPast())
                upcoming.add(t);

        return upcoming;
    }
}
