package model;

import java.time.LocalDateTime;

public class SchoolTest implements Comparable<SchoolTest> {

    private String name;
    private LocalDateTime date;
    private String description;

    public SchoolTest() {
        this(null, null, null);
    }

    public SchoolTest(String name, LocalDateTime date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPast(){
        return LocalDateTime.now().isAfter(date);
    }

    @Override
    public int compareTo(SchoolTest otherTest) {
        if(otherTest == null) {
            throw new IllegalArgumentException("Cannot compare to null argument.");
        }
        if(this.date.isBefore(otherTest.getDate())) {
            return -1;
        } else if(this.date.isAfter(otherTest.getDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}
