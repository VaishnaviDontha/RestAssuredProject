Feature: PUT API Call testing

  Scenario: Verification of the PUT API call
    Given actual url is defined with url and serviceput url
    When headers and payload are added
    Then api response should return 200