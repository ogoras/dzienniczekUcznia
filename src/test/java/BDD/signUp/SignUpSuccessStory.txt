Scenario: User creates account with available username

Given available username "available"
When account with username "available" is created
Then creating account is successful