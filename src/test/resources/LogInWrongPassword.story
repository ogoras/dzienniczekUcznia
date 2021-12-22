Scenario: User tries to log in with wrong password

Given valid username "Valid User" and wrong password "wrong"
When the user log in with given data
Then logging in fails