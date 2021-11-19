package model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {
    private final int id;
    private List<Subject> subjects;

    public Teacher(int id) {
        super();
        this.id = id;
        subjects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubjects(Subject... subjectsToAdd) {
        for(Subject subject : subjectsToAdd){
            subjects.add(subject);
        }
    }

    public boolean hasSubject(Subject subject){
        return subjects.contains(subjects);
    }
}
