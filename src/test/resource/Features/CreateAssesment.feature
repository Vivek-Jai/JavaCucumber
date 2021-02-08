Feature: Create the new assesment to the user 

Background: Login the application 

	Given Sign to the application 
	
		|Url|Username| Password|
		|https://zotuuxt.cyberprism.ie|cyberprism@cri.ie|t1bg*UgJ^~#p|
		
Scenario: Create the new assesment to the user 

	Given click the create new assesment button 
	
	When the user fill the form details and submit 
	
		|OrganisationName|OrganisationLogo|BusinessFactor|CountryIndex|Address|Scope_of_Audit|Category|SecurityClassification|AssessorName|AssessorTitle|AssessorEmail|AssignUser|
		
		|Bank of America|//DataUtil//AssesmentLogo.jpeg|Financial|Ireland|Ardbraccan The Lane Co. Meath|Testing Purpose|Banking|Testing|DSRC|Assessor|vivek.j@dsrc.co.in|AutoTest1|
		
	Then verify the new assesment created succesfully 
	
		|AssesmentName|
		|Bank of America|
		