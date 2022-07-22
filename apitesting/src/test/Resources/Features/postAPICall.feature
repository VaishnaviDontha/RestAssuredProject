Feature: POST API Call testing

  Scenario: Verification of the POST API call
    Given actual url is defined with url and servicepost url
    When headers and payload is added
    Then api response must return 201