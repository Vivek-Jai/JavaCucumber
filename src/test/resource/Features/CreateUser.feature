Feature: Create the new user to the application 

Background: Login the application 

	Given Then Sign to the application 
	
		|Url|Username| Password|
		|https://zotuuxt.cyberprism.ie|cyberprism@cri.ie|t1bg*UgJ^~#p|

Scenario: Create the new user 		
		
	When click the user link in the menu 
	
	And click the add new user button 
	
	Then fill the user form and click submit 
	
	|Name|Email|UserType|UserSecurity|
	|kumar|kumar@gmail.com|Standard Account|Two Factor Authentication|
	
	Then verify the user is created successfully or not
	|Name|
	|kumar|