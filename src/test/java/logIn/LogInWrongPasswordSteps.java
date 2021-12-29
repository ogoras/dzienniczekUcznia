package logIn;

import account.LogIn;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import repository.Repository;

public class LogInWrongPasswordSteps {

    private LogIn logIn;
    private String validUsername;
    private String wrongPassword;
    private Repository repository;

    @Given("valid username $username and wrong password $password")
    public void validUsernameAndWrongPassword(String username, String password) {
        repository = new Repository();
        logIn = new LogIn(repository);
        validUsername = username;
        wrongPassword = password;
    }

    @When("the user log in with given data")
    public void theUserLogInWithGivenData() {
        logIn.performLogIn(validUsername, wrongPassword);
    }

    @Then("logging in fails")
    public void userIsLoggedInStatus() {
        Assertions.assertFalse(logIn.isLoggedIn());
    }
}
