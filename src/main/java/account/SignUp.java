package account;

import repository.Repository;

public class SignUp {

    private final Repository repository;

    public SignUp(Repository repository) {
        this.repository = repository;
    }

    public boolean createAccount(String username, String password, String firstName, String surname){
        if(repository.doesUserExits(username)) {
            return false;
        } else {
            repository.createUser(username, password, firstName, surname);
            return true;
        }
    }
}
