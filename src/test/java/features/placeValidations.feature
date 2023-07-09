Feature: Validating Place API

@SimpleTest
  Scenario: Verify if Add Place Functionality is being Successfully added using AddPlaceAPI
    Given AddPlace Payload
    When User Calls "AddPlaceAPI" with "POST" HTTP Request
    Then the API call got Success with Status Code 200
    And "status" in Response Body is "OK"
    And "scope" in Response Body is "APP"

@AddPlace
  Scenario Outline: Verify if Add Place Functionality is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<Name>" "<Language>" "<Address>"
    When User Calls "AddPlaceAPI" with "POST" HTTP Request
    Then the API call got Success with Status Code 200
    And "status" in Response Body is "OK"
    And "scope" in Response Body is "APP"
    And Verify place_Id Created maps to "<Name>" using "GetPlaceAPI"

    Examples: 
      | Name        | Language | Address            |
      | AAutomation | English  | World Cross Centre |
      | BAutomation | Spanish  | Sea Cross Centre   |

@DeletePlace
  Scenario: Verify if Delete Place Functioanlity is Working
    Given DeletePlace Payload
    When User Calls "DeletePlaceAPI" with "POST" HTTP Request
    Then the API call got Success with Status Code 200
    And "status" in Response Body is "OK"
