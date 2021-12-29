package ui;

import account.LogIn;
import controller.Controller;
import model.Grade;
import model.SchoolTest;
import model.Student;
import model.Subject;
import repository.Repository;
import utilities.GradeComparatorByDate;
import utilities.GradeComparatorByWeight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserInterface {

    private Student student;
    private BufferedReader reader;
    private String command;
    private Comparator<Grade> gradeComparator;
    private boolean sortDescending = true;
    private boolean isSubjectDisplayed = false;
    private Subject subject;
    private List<String> commandsWithDescription;
    private final Repository repository;

    UserInterface() {
        repository = new Repository();
        gradeComparator = new GradeComparatorByDate();
        gradeComparator = gradeComparator.reversed();
        commandsWithDescription = new ArrayList<>();
        initCommandsList();
    }

    public void start() {
        reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            logInDisplay(reader);

            displayMain();
            command = "";
            while (!command.equals("exit")) {
                command = reader.readLine();
                String[] args = command.split(" ");
                switch (args[0]) {
                    case "subject":
                    case "sub":
                        String name;
                        if (args.length > 1) {
                            name = args[1];
                        } else {
                            System.out.println("Enter subject name: ");
                            name = reader.readLine();
                        }
                        subject = student.getSubject(name);
                        if (subject != null) {
                            isSubjectDisplayed = true;
                            displaySubject(subject);
                        } else {
                            System.out.println("You're not enrolled in subject " + name + " or subject does not exist.");
                        }
                        break;
                    case "sort":
                        if (args.length < 2) {
                            System.out.println("Missing argument");
                            break;
                        }

                        switch (args[1]) {
                            case "ascending", "asc" -> {
                                gradeComparator = sortDescending ? gradeComparator.reversed() : gradeComparator;
                                sortDescending = false;
                            }
                            case "descending", "desc" -> {
                                gradeComparator = sortDescending ? gradeComparator : gradeComparator.reversed();
                                sortDescending = true;
                            }
                            case "date" -> {
                                gradeComparator = new GradeComparatorByDate();
                                gradeComparator = sortDescending ? gradeComparator.reversed() : gradeComparator;
                            }
                            case "weight" -> {
                                gradeComparator = new GradeComparatorByWeight();
                                gradeComparator = sortDescending ? gradeComparator.reversed() : gradeComparator;
                            }
                        }
                        if (isSubjectDisplayed) {
                            displaySubject(subject);
                        } else {
                            System.out.println("You have to choose subject");
                        }

                        break;
                    case "help":
                        System.out.println("Available command: ");

                        for (String s : commandsWithDescription) {
                            System.out.println(s);
                        }

                        isSubjectDisplayed = false;
                        break;
                    case "home":
                    case "main":
                        displayMain();
                        isSubjectDisplayed = false;
                    case "exit":
                        break;
                    default:
                        System.out.println("Wrong command");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logInDisplay(BufferedReader reader) throws IOException {
        LogIn login = new LogIn();
        String username = "";
        while (!login.isLoggedIn()) {
            System.out.println("Hi! Enter username:\n");
            username = reader.readLine();
            System.out.println("Enter password:\n");
            String password = reader.readLine();
            login.performLogIn(username, password);
        }
        student = repository.getStudent(username);
    }

    private void initCommandsList() {
        commandsWithDescription.add("subject/sub - gives a prompt to insert name of subject to display");
        commandsWithDescription.add("sort ascending/asc - sorts subject grades ascending*");
        commandsWithDescription.add("sort descending/desc - sorts subject grades descending*");
        commandsWithDescription.add("sort date - sorts subject grades date*");
        commandsWithDescription.add("sort weight - sorts subject grades date*");
        commandsWithDescription.add("home/main - display homepage");
        commandsWithDescription.add("help - display available command (current view)");
        commandsWithDescription.add("exit - close program");
        commandsWithDescription.add("*works only in subject view");
    }

    private void displayMain() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        double average = round(student.calculateArithmeticAverage(), 2);
        double attendance = round(student.calculateAttendance(), 2);
        List<Subject> lowAverageSubjects = Controller.getListOfTroublesomeSubjects(student);
        List<Subject> lowAttendanceSubjects = Controller.getListOfMostMissedSubjects(student);


        System.out.println("Hi " + student.getFirstname() + "!\n");
        System.out.println("Your general average grade is: " + average);

        if (!lowAverageSubjects.isEmpty()) {
            for (Subject s : lowAverageSubjects) {
                System.out.println(ANSI_RED + "Your " + s.getName() + "'s grades are too low" + ANSI_RESET);
            }
        }

        System.out.println("\nYour general attendance is: " + attendance);

        if (!lowAttendanceSubjects.isEmpty()) {
            for (Subject s : lowAttendanceSubjects) {
                System.out.println(ANSI_RED + "Your " + s.getName() + "'s attendance is too low" + ANSI_RESET);
            }
        }

        System.out.println();
        displaySubjectList();
        System.out.println();
        displayTestList();
    }

    private void displayTestList() {
        System.out.println("Your upcoming tests:");
        System.out.println(String.format("%-20s %-20s %-20s %s", "Test", "Subject", "Date", "Description"));
        for (SchoolTest test : student.getUpcomingTests()) {
            printTest(test);
        }
    }

    private void printTest(SchoolTest test) {
        System.out.println(String.format(
                "%-20s %-20s %-20s %s",
                test.getName(),
                test.getSubject().getName(),
                test.getDate(),
                test.getDescription()
        ));
    }

    private void displaySubjectList() {
        System.out.println(String.format("%-15s %-15s %-15s", "Subject", "Average grade", "Attendance"));
        for (Subject sub : student.getSubjects()) {
            System.out.println(String.format(
                    "%-15s %-15s %-15s",
                    sub.getName(),
                    round(student.calculateWeightedAverage(sub), 2),
                    round(student.calculateSubjectAttendance(sub) * 100, 2) + "%"
            ));
        }
    }

    private void displaySubject(Subject s) {
        System.out.println(s.getName());
        System.out.println();
        System.out.println("Grades: ");
        System.out.println(String.format(
                "%-15s %-15s %-15s %-15s %s",
                "Value:",
                "Weight:",
                "Date:",
                "Test:",
                "Comment:"
        ));

        List<Grade> grades = student.getGrades(s);
        grades.sort(gradeComparator);
        for (Grade grade : grades) {
            System.out.println(String.format(
                    "%-15s %-15s %-15s %-15s %s",
                    grade.getValue(),
                    grade.getWeight(),
                    grade.getDate(),
                    grade.getTest() == null ? "" : grade.getTest(),
                    grade.getComment()
            ));
        }

        System.out.println();
        System.out.println("Attendance: " + student.getAttendances().get(s) + "/" + s.getAmountOfClasses() + " = "
                + round(student.calculateSubjectAttendance(s) * 100, 2) + "%");

        System.out.println();
        if (!s.getTests().isEmpty()) {
            System.out.println(String.format("%-20s %-20s %-20s %s", "Test", "Subject", "Date", "Description"));
        }

        for (SchoolTest test : s.getTests()) {
            printTest(test);
        }
    }

    private double round(double value, int decimalPlaces) {
        int factor = 1;
        for (int i = 0; i < decimalPlaces; i++) {
            factor *= 10;
        }
        return Math.round(value * factor) / (double) factor;
    }
}
