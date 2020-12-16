Feature: Test the City Bik API
  Description: Test GET method

  Scenario: Successful status code
    Given I send a GET request to the networks endpoint
    Then I receive a valid response


  Scenario: Confirm Frankfurt country
    Given I send a GET request to the networks endpoint
    And I receive a valid response
    And I confirm the country is Germany
    Then I can return the latitude and longitude
