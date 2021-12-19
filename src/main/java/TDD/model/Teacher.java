package TDD.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {
    private List<Subject> subjects;

    public Teacher(String firstName, String lastName, int id) {
        super(firstName, lastName, id);
        subjects = new ArrayList<>();
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
