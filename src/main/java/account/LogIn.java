package account;

import repository.Repository;

public class LogIn {

    private final Repository repository;
    private boolean isLoggedIn = false;

    public LogIn(Repository repository) {
        this.repository = repository;
    }

    public void performLogIn(String username, String password) {
        if(!repository.doesUserExits(username)){
            isLoggedIn = false;
        } else {
            isLoggedIn = repository.checkUserPassword(username, password);
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
