

Feature: AmazonMobileSearch
  This is used to search product in Amazon site.
    
Scenario: Search mobile withing the range and verify sort
    Given user navigates to amazon and select mobile cataegory
    And after selecting brand and entering range clicks Go button
    When user sorts search product by high to low prices
    Then verify product name and if sort works in decreasing mode