Feature: GET API Call testing

Scenario: Verification of the GET API call
    Given actual url is defined with url and servicedelete url
    When headers and actualurl is hit
    Then api should retturn status code as 204
