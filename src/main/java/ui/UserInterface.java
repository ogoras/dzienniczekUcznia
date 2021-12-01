package ui;

import model.Grade;
import model.SchoolTest;
import model.Student;
import model.Subject;
import repository.Repository;
import utilities.GradeComparatorByDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class UserInterface {

    Student student;
    BufferedReader reader;
    String command;
    Comparator<Grade> gradeComparator;

    UserInterface(){
        Repository repository = new Repository();
        gradeComparator = new GradeComparatorByDate();
        gradeComparator = gradeComparator.reversed();
        student = repository.getStudent(0);
    }

    public void start(){
        reader = new BufferedReader(
                new InputStreamReader(System.in));

        displayMain();
        command = "";
        while (!command.equals("exit")) {
            try {
                command = reader.readLine();
                String[] args = command.split(" ");
                switch(args[0]) {
                    case "subject":
                    case "sub":
                        String name;
                        if(args.length > 1) {
                            name = args[1];
                        } else {
                            System.out.println("Enter subject name: ");
                            name = reader.readLine();
                        }
                        Subject subject = student.getSubject(name);
                        if(subject != null) {
                            displaySubject(subject);
                        } else {
                            System.out.println("You're not enrolled in subject " + name + " or subject does not exist.");
                        }
                        break;
                    case "sort":
                        //TODO
                        break;
                    case "home":
                    case "main":
                        displayMain();
                    case "exit":
                        break;
                    default:
                        System.out.println("Wrong command");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayMain(){
        System.out.println("Hi " + student.getFirstname() + "!");
        System.out.println("Your general average grade is: " +
                round(student.calculateArithmeticAverage(),2));
        System.out.println("Your general attendance is: " + round(student.calculateAttendance(),2));
        System.out.println();
        displaySubjectList();
        System.out.println();
        displayTestList();
    }

    private void displayTestList() {
        System.out.println("Your upcoming tests:");
        System.out.println("Test:\t\t\tSubject:\t\tDate:\t\t\t\t\tDescription:");
        for(SchoolTest test : student.getUpcomingTests()) {
            printTest(test);
        }
    }

    private void printTest(SchoolTest test) {
        System.out.println(test.getName() +
                "\t" + test.getSubject().getName() +
                "\t\t\t" + test.getDate() +
                "\t\t" + test.getDescription() );
    }

    private void displaySubjectList() {
        System.out.println("Subject:\t\tAverage Grade:\t\tAttendance:");
        for(Subject sub: student.getSubjects()) {
            System.out.println(sub.getName() +
                    "\t\t" + round(student.calculateWeightedAverage(sub),2) +
                    "\t\t\t\t" + round(student.calculateSubjectAttendance(sub)*100,2) + "%");
        }
    }

    private void displaySubject(Subject s){
        System.out.println(s.getName());
        System.out.println();
        System.out.println("Grades: ");
        System.out.println("Value:\tWeight:\tDate:\t\t\tTest:\t\t\tComment:");
        List<Grade> grades = student.getGrades(s);
        grades.sort(gradeComparator);
        for(Grade grade: grades) {
            System.out.println(grade.getValue() + "\t\t" +
                                grade.getWeight() + "\t\t" +
                                grade.getDate() + "\t\t" +
                                (grade.getTest()==null?"":grade.getTest()) + "\t\t" +
                                grade.getComment());
        }

        System.out.println();
        System.out.println("Attendance: " + student.getAttendances().get(s) + "/" + s.getAmountOfClasses() + " = "
                + round(student.calculateSubjectAttendance(s)*100, 2) + "%");

        System.out.println();
        if (!s.getTests().isEmpty())
            System.out.println("Test:\t\t\tSubject:\t\tDate:\t\t\t\t\tDescription:");
        for (SchoolTest test : s.getTests()) {
            printTest(test);
        }
    }

    private double round(double value, int decimalPlaces) {
        int factor = 1;
        for (int i=0; i<decimalPlaces;  i++) {
            factor *= 10;
        }
        return Math.round(value*factor)/(double)factor;
    }
}
