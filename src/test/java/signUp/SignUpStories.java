package signUp;

import logIn.LogInSuccessSteps;
import logIn.LogInWrongUsernameFailureSteps;
import logIn.LogInWrongPasswordSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class SignUpStories extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(this.getClass()))
                        .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new SignUpSuccessSteps(), new LogInSuccessSteps(), new LogInWrongPasswordSteps(), new LogInWrongUsernameFailureSteps());
    }

    @Override
    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        return finder.findPaths(codeLocationFromClass(this.getClass()).getFile(), Arrays.asList("**/*.story"), Arrays.asList(""));
    }
}
