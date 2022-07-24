Feature: Verification of a GET API Call by passing parameters in feature file.

  Scenario Outline: Verifying the GET API Call of a test website
    Given GET api actualUrl is combined with url "<url>" and serviceurl "<serviceurl>"
    When the actualUrl is hit to the server
    Then the response code of the API must be 200

    Examples:
    | url | serviceurl |
    | https://reqres.in | /api/unknown/2 |
    | https://reqres.in | /api/   |
