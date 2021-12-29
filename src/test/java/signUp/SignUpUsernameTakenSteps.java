package signUp;

import account.SignUp;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import repository.Repository;

public class SignUpUsernameTakenSteps {
    private SignUp signUp;
    private String username;
    private String password;
    private boolean accountCreationSuccess;
    private Repository repository;
    private String firstName, surname;

    @Given("an existing user with username $takenUsername")
    public void givenAnExistingUserWithUsernameTaken(String takenUsername) {
        repository = new Repository();
        signUp = new SignUp(repository);
        username = takenUsername;
        password = "totallySafePassword";
        firstName = "Jan";
        surname = "Kowalski";
        signUp.createAccount(username, password, firstName, surname);
    }

    @When("registering a new account with username $takenUsername")
    public void whenRegisteringANewAccountWithUsernameTaken(String takenUsername) {
        accountCreationSuccess = signUp.createAccount(username, password, firstName, surname);
    }

    @Then("registration fails")
    public void thenRegistrationFails() {
        Assertions.assertFalse(accountCreationSuccess);
    }
}
