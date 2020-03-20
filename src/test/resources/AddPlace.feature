Feature: validating place APi 
@AddPlace
Scenario Outline: verify is place being successfully added using AddPlaceApi

	Given Add place payload "<name>" "<language>" "<address>"
	When user calls "AddPlaceApi" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceApi"
	
Examples:
	|name    |language |address  |
	|AAHouse |English  |mathikere|
#	|BBHouse |Spanish  |yeshwanthpur|
@DeletePlace
Scenario: verify if Deleteplace functionality is working
	Given DeletePlace payload
	When user calls "DeletePlaceApi" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	
	
