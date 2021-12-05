package benchmarks;

import model.Student;
import model.Subject;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class GeneralAttendanceBenchmark {

    Student student;

    @Param({"5", "20"})
    int numberOfSubjects;

    @Setup
    public void setup() {
        student = new Student("Pawel", "Jumper", 15);

        for( int j = 0; j < numberOfSubjects; j++) {
            Subject subject = new Subject("Subject" + j);
            subject.setAmountOfClasses((int)(100 + (Math.random() * 20)));
            student.setAttendance(subject, (int)(60 + (Math.random() * 40)));
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 1)
    @Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 1)
    public double calculateGeneralAttendance() {
        return student.calculateAttendance();
    }
}
