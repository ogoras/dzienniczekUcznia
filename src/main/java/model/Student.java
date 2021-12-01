package model;

import java.util.*;

public class Student extends Person {

    private Map<Subject, Integer> attendances = new HashMap<>();
    private Map<Subject, List<Grade>> grades = new HashMap<>();
    private Map<String, Subject> subjects;

    public Student(String firstName, String surname, int id) {
        super(firstName, surname, id);
        subjects = new HashMap<>();
    }

    public void addSubject(Subject subject) {
        subjects.put(subject.getName(), subject);
    }

    public double calculateWeightedAverageGradeForSubject(Subject subject) {
        double sum = 0.0;
        int sumOfWeights = 0;
        if(grades.get(subject) == null)
            return 0.0;
        for(Grade grade : grades.get(subject)){
            sum += grade.getValue() * grade.getWeight();
            sumOfWeights += grade.getWeight();
        }
        return sumOfWeights != 0 ? (sum / sumOfWeights) : 0.0;
    }

    public double calculateGeneralArithmeticAverageGrade() {
        double sum = 0.0;
        int count = 0;
        for(Subject subject : grades.keySet()) {
            sum += calculateWeightedAverageGradeForSubject(subject);
            count++;
        }
        return count != 0 ? (sum / count) : 0.0;
    }

    public double calculateSubjectAttendance(Subject subject) {
        if(attendances.get(subject) > subject.getAmountOfClasses())
            throw new IllegalArgumentException("Number of Attendances greater than number of classes");
        return subject.getAmountOfClasses() != 0 ?
                ((double) attendances.get(subject) / (double) subject.getAmountOfClasses()) : 0.0;
    }

    public double calculateGeneralAttendance() {
        double sum = 0.0;
        int countOfClasses = 0;
        for(Subject subject : attendances.keySet()){
            if(subject.getAmountOfClasses() != 0) {
                sum += (double) attendances.get(subject);
                countOfClasses += subject.getAmountOfClasses();
            }
        }
        return countOfClasses != 0 ? (sum / (double) countOfClasses) : 0.0;
    }

    public void setAttendance(Subject subject, int attendance) {
        attendances.put(subject, attendance);
    }

    public Map<Subject, Integer> getAttendances() {
        return attendances;
    }

    public List<Subject> getAttendedSubjects() {
        List<Subject> subjects;
        subjects = attendances.keySet().stream().toList();
        return subjects;
    }

    public List<Subject> getSubjectsWithGrades() {
        List<Subject> subjects;
        subjects = grades.keySet().stream().toList();
        return subjects;
    }

    public List<Grade> getGrades(Subject subject) {
        List<Grade> lgrades;
        lgrades = grades.get(subject);
        return lgrades;
    }

    public void addGrade(Grade grade) {
        if(grades.get(grade.getSubject()) != null){
            grades.get(grade.getSubject()).add(grade);
        } else {
            List<Grade> gradesList = new ArrayList<>();
            gradesList.add(grade);
            grades.put(grade.getSubject(), gradesList);
        }
    }

    public Collection<Subject> getSubjects() {
        return subjects.values();
    }

    public Subject getSubject(String s) {
        return subjects.get(s);
    }
}
