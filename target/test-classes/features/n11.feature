Feature: N11 Testcase


  @log
  Scenario:
    Given go to the n11 homepage
    When the user opened the login page
    Then login with valid credentials


  @login
  Scenario:
    When in the search area write samsung and click search button
    Then product page opens and go to product page "2"
    Then Add the 3 product to favorites

  @loginnn
  Scenario:
    When going to Favorilerim / Listelerim page
    And  this product is the same as the product added to favorites
    And  the product added to favorites is being removed
    Then favorites page is now empty




        