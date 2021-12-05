package benchmarks;

import model.Grade;
import model.Student;
import model.Subject;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class AverageGradeForSubjectBenchmark {

    Student student;
    Subject math;

    @Param({"10", "100", "1000"})
    int numberOfGrades;

    @Setup
    public void setup() {
        student = new Student("Jacek", "Muranski", 10);
        math = new Subject("Mathematics");

        for(int i = 0; i < numberOfGrades; i++){
            double value = getRandomGradeValue();
            int weight = getRandomWeight();
            student.addGrade(new Grade(value, weight, math));
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 1)
    @Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 1)
    public double calculateWeightedAverageForOneSubject() {
        return student.calculateWeightedAverage(math);
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
