Feature: GET API Call testing

Scenario: Verification of the GET API call
    Given url is given
    When serviceget url is added
    Then status code must be "200"
