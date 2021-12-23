package signUp;

import account.SignUp;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;

public class SignUpUsernameTakenSteps {
    private SignUp signUp;
    private String username;
    private String password;
    private boolean accountCreationSuccess;

    @Given("an existing user with username $takenUsername")
    public void givenAnExistingUserWithUsernameTaken(String takenUsername) {
        signUp = new SignUp();
        username = takenUsername;
        password = "totallySafePassword";
        signUp.createAccount(username, password);
    }

    @When("registering a new account with username $takenUsername")
    public void whenRegisteringANewAccountWithUsernameTaken(String takenUsername) {
        accountCreationSuccess = signUp.createAccount(username, password);
    }

    @Then("registration fails")
    public void thenRegistrationFails() {
        Assertions.assertFalse(accountCreationSuccess);
    }
}
