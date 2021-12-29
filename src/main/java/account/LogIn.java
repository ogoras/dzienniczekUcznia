package account;

import repository.Repository;

public class LogIn {

    private final Repository repository = new Repository();
    private boolean isLoggedIn = false;

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
