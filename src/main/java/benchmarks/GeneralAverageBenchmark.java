package benchmarks;

import model.Grade;
import model.Student;
import model.Subject;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class GeneralAverageBenchmark {

    Student student;

    @Param({"10", "100"})
    int numberOfGradesInSubject;
    @Param({"5", "20"})
    int numberOfSubjects;

    @Setup
    public void setup() {
        student = new Student("Mlody", "Muranski", 10);

        for( int j = 0; j < numberOfSubjects; j++) {
            Subject subject = new Subject("Subject" + j);

            for (int i = 0; i < numberOfGradesInSubject; i++) {
                double value = getRandomGradeValue();
                int weight = getRandomWeight();
                student.addGrade(new Grade(value, weight, subject));
            }
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 1)
    @Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 1)
    public double calculateGeneralAverage() {
        return student.calculateArithmeticAverage();
    }

    private double getRandomGradeValue(){
        double value = (double) (2 + (int)(Math.random() * 11 ))/2;
        return value;
    }

    private int getRandomWeight(){
        int weight = 1 + (int)(Math.random() * 4 );
        return weight;
    }
}
