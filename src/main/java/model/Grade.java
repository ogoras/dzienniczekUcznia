package model;

import java.time.LocalDate;

public class Grade {

    private int value;
    private int weight;
    private String comment;
    private LocalDate date;
    private Subject subject;
    private Student student;
    private SchoolTest test;

    public Grade(int value, int weight, String comment, LocalDate date, Subject subject, Student student, SchoolTest test) {
    }

    public Grade(int value, int weight, Subject subject) {
    }

    public void setDate(LocalDate date) {
        //todo
    }

    public LocalDate getDate() {
        //todo
        return null;
    }

    public void setComment(String comment) {
        //todo
    }

    public String getComment() {
        //todo
        return null;
    }

    public void setValue() {
        //todo
    }

    public int getValue() {
        //todo
        return 0;
    }

    public void setWeight(int weight) {
        //todo
    }

    public int getWeight() {
        //todo
        return 0;
    }

    public Subject getSubject() {
        //todo
        return null;
    }
}
