package model;

public class Subject {

    private String subjectName;
    private int amountOfClasses = 0;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
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
}
