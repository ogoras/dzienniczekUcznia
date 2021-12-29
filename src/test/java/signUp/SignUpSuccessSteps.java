package signUp;

import account.SignUp;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import repository.Repository;

public class SignUpSuccessSteps {
    private SignUp signUp;
    private String username;
    private String password;
    private boolean accountCreationSuccess;
    private Repository repository;
    private String firstName, surname;

    @Given("available username $availableUsername")
    public void availableUsername(String availableUsername){
        repository = new Repository();
        signUp = new SignUp(repository);
        username = availableUsername;
        password = "totallySafePassword";
        firstName = "Jan";
        surname = "Kowalski";
    }

    @When("account with username $availableUsername is created")
    public void accountIsCreated(){
        accountCreationSuccess = signUp.createAccount(username, password, firstName, surname);
    }

    @Then("creating account is successful")
    public void accountCreationStatus(){
        Assertions.assertTrue(accountCreationSuccess);
    }
}
