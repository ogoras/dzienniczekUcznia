package signUp;

import account.SignUp;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;

public class SignUpSuccessSteps {
    private SignUp signUp;
    private String username;
    private String password;
    private boolean accountCreationSuccess;

    @Given("available username $availableUsername")
    public void availableUsername(String availableUsername){
        signUp = new SignUp();
        username = availableUsername;
        password = "totallySafePassword";
    }

    @When("account with username $availableUsername is created")
    public void accountIsCreated(){
        accountCreationSuccess = signUp.createAccount(username, password);
    }

    @Then("creating account is successful")
    public void accountCreationStatus(){
        Assertions.assertTrue(accountCreationSuccess);
    }
}
