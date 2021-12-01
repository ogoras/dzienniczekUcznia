package ui;

import model.Grade;
import model.Student;
import model.Subject;
import repository.Repository;
import utilities.GradeComparatorByDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
                        displayMain();
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
        System.out.println("Your general average grade is: " + student.calculateGeneralArithmeticAverageGrade());
        System.out.println("Your general attendance is: " + student.calculateGeneralAttendance());
        displaySubjectList();

    }

    private void displaySubjectList() {
        System.out.println("Subject:\t\tAverage Grade:\t\tAttendance:");
        for(Subject sub: student.getSubjects()) {
            System.out.println(sub.getName() +
                    "\t\t" + Math.round(student.calculateWeightedAverageGradeForSubject(sub)*100)/100.0 +
                    "\t\t\t\t" + Math.round(student.calculateSubjectAttendance(sub)*100)/100.0);
        }
    }

    private void displaySubject(Subject s){
        System.out.println(s.getName());
        System.out.println("Grades: ");
        System.out.println("Value:\tWeight:\tDate:\t\t\tTest:\t\t\tComment:");
        List<Grade> grades = student.getGrades(s);
        grades.sort(gradeComparator);
        for(Grade grade: grades) {
            System.out.println(grade.getValue() + "\t\t" +
                                grade.getWeight() + "\t\t" +
                                grade.getDate() + "\t\t" +
                                grade.getTest() + "\t\t" +
                                grade.getComment());
        }

        System.out.println("Attendance: " + student.getAttendances().get(s) + "/" + s.getAmountOfClasses() + " = " + Math.round(student.calculateSubjectAttendance(s)*10000)/100.0 + "%");
    }
}
