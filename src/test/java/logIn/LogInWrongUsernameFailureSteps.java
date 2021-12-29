package logIn;

import account.LogIn;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import repository.Repository;

public class LogInWrongUsernameFailureSteps {

    private LogIn logIn;
    private String incorrectUsername;
    private String relevantPassword;
    private Repository repository;

    @Given("incorrect username $username and relevant password $password")
    public void loggingInDialogHasBeenDisplayed(String username, String password) {
        repository = new Repository();
        logIn = new LogIn(repository);
        incorrectUsername = username;
        relevantPassword = password;
    }

    @When("I log in with given data")
    public void userLogsInWithValidData() {
        logIn.performLogIn(incorrectUsername, relevantPassword);
    }

    @Then("logging in is unsuccessful")
    public void userIsLoggedIn() {
        Assertions.assertFalse(logIn.isLoggedIn());
    }
}
