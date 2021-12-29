package logIn;

import account.LogIn;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import repository.Repository;

public class LogInSuccessSteps {

    private LogIn logIn;
    private String validUsername;
    private String validPassword;
    private Repository repository;

    @Given("valid username $username and valid password $password")
    public void loggingInDialogHasBeenDisplayed(String username, String password) {
        repository = new Repository();
        logIn = new LogIn(repository);
        validUsername = username;
        validPassword = password;
    }

    @When("I log in with given data")
    public void userLogsInWithValidData() {
        logIn.performLogIn(validUsername, validPassword);
    }

    @Then("logging in is successful")
    public void userIsLoggedIn() {
        Assertions.assertTrue(logIn.isLoggedIn());
    }
}
