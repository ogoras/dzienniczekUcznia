package model;

public class User {
    private final String login;
    private final String password;
    private final Student studentsData;

    public User(String login, String password, Student studentsData) {
        this.login = login;
        this.password = password;
        this.studentsData = studentsData;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Student getStudentsData() {
        return studentsData;
    }
}
