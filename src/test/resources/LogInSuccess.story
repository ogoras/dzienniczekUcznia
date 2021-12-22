Scenario: User logs in with valid data

Given valid username "Andrzej Andrzejewski 3" and valid password "29lis"
When I log in with given data
Then logging in is successful