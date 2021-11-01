Feature: Search with invalid key

#  Background: Login successfully
#    Given user has already logged in to the application

  @SmokeTest @Test-1
  Scenario Outline: search with valid key and invalid key
    Given go to home page
    When searching for <key>
    And click on the link of result search of <key>
    Then verify the current date display correct
    And verify city name is <city>
    And verify the weather display correct

  Examples:
    | key      | city         |
    | Ha Noi   | Hanoi, VN    |
    | Vung Tau | Vung Tau, VN |
    | Ha Noi   | Fail test 1   |
#  Add a failed case Search Ha Noi to take screenshot
