package benchmarks.controller;

import controller.Controller;
import model.Grade;
import model.Student;
import model.Subject;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ListOfTroublesomeSubjectsBenchmark {

    private Student student;

    @Param({"10", "100"})
    int numberOfGrades;

    @Param({"5", "20"})
    int numberOfSubjects;

    @Setup
    public void setup(){
        student  = new Student("Jan", "Kowalski", 1234);

        for(int i = 0; i < numberOfSubjects; i++){
            Subject subject = new Subject("Subject" + i);

            for(int j = 0; j < numberOfGrades; j++){
                student.addGrade(new Grade(1, 5, subject));
            }
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 1)
    @Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 1)
    public List<Subject> getStudentsListOfTroublesomeSubjects() {
        return Controller.getListOfTroublesomeSubjects(student);
    }
}
