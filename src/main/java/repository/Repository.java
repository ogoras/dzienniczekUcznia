package repository;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Repository {

    private Map<String, User> allUsers;
    private int id = 2;

    public Repository() {
        allUsers = getMockedData();
    }

    public boolean doesUserExits(String username) {
        return allUsers.containsKey(username);
    }

    public boolean checkUserPassword(String username, String password) {
        return allUsers.containsKey(username) && allUsers.get(username).getPassword().equals(password);
    }

    public Student getStudent(String username) {
        return allUsers.get(username).getStudentsData();
    }

    private Map<String, User> getMockedData() {
        Map<String, User> mockedData = new HashMap<>();

        String login = "Andrzej Andrzejewski 3";
        String password = "29lis";
        mockedData.put(login, new User(login, password, getMockedStudentsData()));

        login = "Julixd";
        password = "hihihi";
        mockedData.put(login, new User(login, password, getAnotherMockedStudentsData()));

        return mockedData;
    }

    private Subject setStudentsSubject(Student student, String subjectName, int numberOfAttendedClasses, int numberOfAllClasses) {
        Subject subject = new Subject(subjectName);
        subject.setAmountOfClasses(numberOfAllClasses);
        student.setAttendance(subject, numberOfAttendedClasses);
        student.addSubject(subject);
        return subject;
    }

    private void addGrades(Student student, Grade... grades) {
        for (Grade g : grades) {
            student.addGrade(g);
        }
    }

    private Student getMockedStudentsData() {
        Student student = new Student("Andrzej", "Andrzejewski", 0);

        Subject math = setStudentsSubject(student, "Mathematics", 3, 4);
        Subject biology = setStudentsSubject(student, "Biology", 4, 6);
        Subject chemistry = setStudentsSubject(student, "Chemistry", 1, 1);
        Subject physicalEducation = setStudentsSubject(student, "PE", 1, 4);
        Subject physics = setStudentsSubject(student, "Physics", 3, 8);

        addGrades(
                student,
                new Grade(6, 2, math),
                new Grade(3, 3, math),
                new Grade(2, 1, math),
                new Grade(3, 2, LocalDate.of(2021, 10, 25), biology),
                new Grade(5, 4, biology),
                new Grade(4, 2, LocalDate.of(2022, 10, 20), chemistry),
                new Grade(1, 1, LocalDate.of(2021, 11, 28), chemistry),
                new Grade(6, 4, LocalDate.of(2022, 1, 18), chemistry),
                new Grade(2, 4, LocalDate.of(2022, 1, 1), chemistry),
                new Grade(1, 5, LocalDate.of(2021, 12, 2), physics),
                new Grade(2, 3, LocalDate.of(2021, 12, 1), physics),
                new Grade(1, 2, LocalDate.of(2021, 11, 15), physics),
                new Grade(4, 2, LocalDate.of(2021, 11, 21), physicalEducation),
                new Grade(6, 4, physicalEducation)
        );

        SchoolTest test1 = new SchoolTest(
                "DNA Exam",
                LocalDateTime.of(2022, 1, 20, 10, 5),
                "test about DNA"
        );

        SchoolTest test2 = new SchoolTest(
                "Carp anatomy",
                LocalDateTime.of(2021, 12, 24, 19, 0),
                "Carp anatomy for your Christmas Eve"
        );

        biology.addTest(test1);
        biology.addTest(test2);
        return student;
    }

    private Student getAnotherMockedStudentsData() {
        Student human = new Student("Julia", "Julewska", 1);

        Subject english = setStudentsSubject(human, "English", 8, 10);
        Subject history = setStudentsSubject(human, "History", 0, 5);
        Subject french = setStudentsSubject(human, "French", 7, 7);

        addGrades(
                human,
                new Grade(5.5, 2, LocalDate.of(2021, 1, 29), english),
                new Grade(5, 1, LocalDate.of(2020, 11, 12), english),
                new Grade(4.5, 2, LocalDate.of(2020, 12, 12), english),
                new Grade(6, 3, LocalDate.of(2020, 10, 25), english),
                new Grade(1, 3, LocalDate.of(2020, 11, 16), history),
                new Grade(5, 2, LocalDate.of(2021, 11, 3), french),
                new Grade(6, 1, LocalDate.of(2020, 11, 30), french)
        );

        SchoolTest test = new SchoolTest(
                "2nd World War",
                LocalDateTime.of(2021, 1, 5, 12, 0),
                "test about 2nd WW"
        );

        history.addTest(test);
        return human;
    }

    public void createUser(String username, String password, String firstName, String surname) {
        allUsers.put(username, new User(username, password, new Student(firstName, surname, id)));
        id++;
    }
}
