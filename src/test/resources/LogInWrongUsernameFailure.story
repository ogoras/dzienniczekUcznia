Scenario: User logs in with uncorrect username

Given incorrect username "niepoprawnyLogin" and relevant password "hasloBezZnaczenia"
When I log in with given data
Then logging in is unsuccessful