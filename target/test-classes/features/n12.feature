@n11
  Feature: N11 Testcase using SeleniumWebDriver and Cucumber Framework
    @smoke @login
    Scenario: Go to website in valid credentials and add selected product to favorites and confirm
      Given go to the n11 homepage
      And the user opened the login page
      And login with valid credentials
      And in the search area write samsung and click search button
      When product page opens and go to product page "2"
      And Add the 3 product to favorites
      And going to Favorilerim / Listelerim page
      And  this product is the same as the product added to favorites
      And  the product added to favorites is being removed
      Then favorites page is now empty