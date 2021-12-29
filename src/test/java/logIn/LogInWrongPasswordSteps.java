package logIn;

import account.LogIn;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;

public class LogInWrongPasswordSteps {

    private LogIn logIn;
    private String validUsername;
    private String wrongPassword;

    @Given("valid username $username and wrong password $password")
    public void validUsernameAndWrongPassword(String username, String password) {
        logIn = new LogIn();
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
