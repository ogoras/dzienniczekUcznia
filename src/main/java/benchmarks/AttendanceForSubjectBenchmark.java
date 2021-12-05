package benchmarks;

import model.Student;
import model.Subject;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class AttendanceForSubjectBenchmark {

    Student student;
    Subject math;

    @Setup
    public void setup() {
        student = new Student("Marcin", "Najman", 10);
        math = new Subject("Mathematics");
        math.setAmountOfClasses(100);
        student.setAttendance(math, 80);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 1)
    @Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 1)
    public double calculateAttendanceForOneSubject() {
        return student.calculateSubjectAttendance(math);
    }
}
