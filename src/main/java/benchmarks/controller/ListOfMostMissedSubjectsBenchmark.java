package benchmarks.controller;

import controller.Controller;
import model.Grade;
import model.Student;
import model.Subject;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ListOfMostMissedSubjectsBenchmark {

    private Student student;

    @Param({"10", "100"})
    int numberOfAttendancesInSubject;

    @Param({"5", "20"})
    int numberOfMostMissedSubjects;

    @Setup
    public void setup(){
        student  = new Student("Jan", "Kowalski", 1234);
        int amountOfClasses = numberOfAttendancesInSubject * 3;

        for(int i = 0; i < numberOfMostMissedSubjects; i++){
            Subject subject = new Subject("Subject" + i);

            subject.setAmountOfClasses(amountOfClasses);
            student.setAttendance(subject, numberOfAttendancesInSubject);
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 1)
    @Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 1)
    public List<Subject> getStudentsListOfMostMissedSubjects() {
        return Controller.getListOfMostMissedSubjects(student);
    }
}
