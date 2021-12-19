package model;

public class Person {
    private final String firstname;
    private final String lastName;
    private final int id;

    public Person(String firstname, String lastName, int id) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return firstname + " " + lastName;
    }
}
