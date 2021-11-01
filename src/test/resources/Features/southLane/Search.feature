Feature: Search

#  Background: Login successfully
#    Given user has already logged in to the application

  @SmokeTest @Test-2
  Scenario: search with valid key
    Given go to home page
    When searching for "Ha Noi"
    And click on the link of result search of "Ha Noi"
    Then verify the current date display correct
    And verify city name is "Hanoi, VN"
    And verify the weather display correct
