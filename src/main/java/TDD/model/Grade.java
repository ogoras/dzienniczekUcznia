package TDD.model;

import java.time.LocalDate;

public class Grade {

    private double value;
    private int weight;
    private String comment;
    private LocalDate date;
    private Subject subject;
    private Student student;
    private SchoolTest test;

    public Grade(double v, int w, String c, LocalDate d, Subject sub, Student s, SchoolTest t) {
        value = v;
        weight = w;
        comment = c;
        date = d;
        subject = sub;
        student = s;
        test = t;
    }

    public Grade(double v, int w, Subject sub) {
        this(v, w, "", java.time.LocalDate.now(), sub, null, null);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setValue(double v) {
        value = v;
    }

    public double getValue() {
        return value;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Subject getSubject() {
        return subject;
    }

    public SchoolTest getTest() {
        return test;
    }

    public Student getStudent() {
        return student;
    }
}
