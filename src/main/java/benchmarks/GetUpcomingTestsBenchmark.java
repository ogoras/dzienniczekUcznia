package benchmarks;

import TDD.model.SchoolTest;
import TDD.model.Student;
import TDD.model.Subject;
import org.openjdk.jmh.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class GetUpcomingTestsBenchmark {
    private Student student;

    @Setup
    public void setup() {
        student = new Student("Bartłomiej", "Teraz", 17);

        Subject math = new Subject("Matematyka");
        math.addTest(new SchoolTest(
                "Kartkóweczka z zadań",
                LocalDateTime.of(2021, 12, 15, 12, 0),
                "Żeby uczniom nie było za lekko."
        ));

        math.addTest(new SchoolTest(
                "Sprawdzianik z wielomianków",
                LocalDateTime.of(2021, 12, 19, 8, 30),
                "Wielomiany."
        ));

        Subject physics = new Subject("Fizyka");
        physics.addTest(new SchoolTest(
                "Kartkówka z fali",
                LocalDateTime.of(2021, 12, 19, 10, 0),
                ""
        ));

        physics.addTest(new SchoolTest(
                "Duży sprawdzian",
                LocalDateTime.of(2022, 1, 10, 9, 0),
                "Obejmuje materiał z tematów: pola, fale, drgania."
        ));

        physics.addTest(new SchoolTest(
                "Próbna matura z fizyki 1",
                LocalDateTime.of(2021, 12, 19, 8, 30),
                ""
        ));

        Subject english = new Subject("Język angielski");
        english.addTest(new SchoolTest(
                "Sprawdzian unit",
                LocalDateTime.of(2021, 12, 20, 13, 0),
                "Unit 3"
        ));

        english.addTest(new SchoolTest(
                "Kartkówka słówka",
                LocalDateTime.of(2021, 12, 16, 9, 0),
                "Słówka z tematu Travelling"
        ));

        Subject polish = new Subject("Język polski");
        polish.addTest(new SchoolTest(
                "Sprawdzian epoka",
                LocalDateTime.of(2021, 12, 14, 8, 0),
                "Barok"
        ));

        polish.addTest(new SchoolTest(
                "Sprawdzian lektura",
                LocalDateTime.of(2021, 12, 15, 8, 44),
                "Dziady cz. 3"
        ));

        polish.addTest(new SchoolTest(
                "Kartkówka interpretacja wiersza",
                LocalDateTime.of(2021, 12, 16, 11, 30),
                ""
        ));

        polish.addTest(new SchoolTest(
                "Kartkówka analiza wiersza",
                LocalDateTime.of(2021, 12, 17, 10, 45),
                "Opis"
        ));

        Subject physicalEducation = new Subject("WF");
        physicalEducation.addTest(new SchoolTest(
                "Zaliczenie",
                LocalDateTime.of(2022, 1, 20, 8, 40),
                "Pompeczki"
        ));

        Subject chemistry = new Subject("Chemia");
        chemistry.addTest(new SchoolTest(
                "Sprawdzian 15",
                LocalDateTime.of(2022, 1, 8, 14, 0),
                "Pirozliza węgla kamiennego"
        ));

        chemistry.addTest(new SchoolTest(
                "Sprawdzian 16",
                LocalDateTime.of(2022, 1, 16, 14, 0),
                "Rafinacja ropy naftowej"
        ));

        student.addSubject(math);
        student.addSubject(physics);
        student.addSubject(english);
        student.addSubject(polish);
        student.addSubject(chemistry);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 1)
    @Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 1)
    public List<SchoolTest> getUpcomingTests() {
        return student.getUpcomingTests();
    }
}
