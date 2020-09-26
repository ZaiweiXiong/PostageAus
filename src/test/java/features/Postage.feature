@post
Feature: Calculate Postage from the Australia post website.

  Scenario: Calculate Postage
  to verify express post and parcel post in listed down
    Given user go to Australia Post Calculate postage
    When user Enter a source "2151" and destination postcode "2150" and selecting from the dropdown list
    And  user click on the Go button
    Then Validate the cost express post and parcel post 
    
   Scenario Outline: Calculate Postage with size weight and date
    to verify express post and parcel post in listed down
    Given user go to Australia Post Calculate postage
    When user Enter a source "2151" and destination postcode "2150" and selecting from the dropdown list
    And  user click on the Go button
    Then  user type <size>,<weight>,<date> for calculate postage
    Then Validate the cost express post and parcel post after setting size and date
    Examples:
      | size  |weight | date | 
      | 10   |   18   | 2020-09-27|
      