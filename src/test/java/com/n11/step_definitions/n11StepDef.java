package com.n11.step_definitions;

import com.n11.pages.HomePage;
import com.n11.pages.LoginPage;
import com.n11.pages.MyAccountsPage;
import com.n11.pages.SearchPage;
import com.n11.utilies.BrowserUtils;
import com.n11.utilies.ConfigurationReader;
import com.n11.utilies.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class n11StepDef {

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    SearchPage searchPage = new SearchPage();
    MyAccountsPage myAccountsPage = new MyAccountsPage();
    String favsProduct;
    String choosingProduct;


    @Given("go to the n11 homepage")
    public void go_to_the_n11_homepage() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        homePage.privPoliciyBtn.click();
        System.out.println("I opened the n11.com.tr");
        String actualUrl = Driver.get().getCurrentUrl();
        Assert.assertEquals("Verify this website", url, actualUrl);

    }

    @When("the user opened the login page")
    public void the_user_opened_the_login_page() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.SignInBtn)).click();
    }


    @Then("login with valid credentials")
    public void login_with_valid_credentials()  {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        loginPage.fbLogin(username, password);
        String expectedTitle = "hayat sana gelir";
        Assert.assertEquals("Verify pageSubTitle", expectedTitle, homePage.getPageSubTitle());
        System.out.println("PageSubTitle = " + homePage.getPageSubTitle());

    }

    @When("in the search area write {} and click search button")
    public void in_the_search_area_write_and_click_search_button(String product) {
        homePage.searchProduct(product);
        searchPage.getResult();
        System.out.println("the result of the product = " + searchPage.getResult());
        

    }


    @Then("product page opens and go to product page {string}")
    public void product_page_opens_and_go_to_product_page(String expectedPage) {
        searchPage.ProductsPagesBtn(expectedPage);
        BrowserUtils.waitFor(3);
        String actualPageNumber = String.valueOf(searchPage.pageNumber.getAttribute("value"));
        Assert.assertEquals(expectedPage, actualPageNumber);
        System.out.println("actualNumber = " + actualPageNumber);


    }

    @Then("Add the {int} product to favorites")
    public void add_the_product_to_favorites(Integer number) {
        number = number-1;
        searchPage.AddToFavBtn(number);
        choosingProduct = searchPage.products.get(number).getText();

    }

    @When("going to {} page")
    public void going_to_page(String pagesId) {
        searchPage.hoverUp();
        searchPage.navigateToMyAccountPages(pagesId);
    }


    @When("this product is the same as the product added to favorites")
    public void this_product_is_the_same_as_the_product_added_to_favorites() {
        favsProduct = myAccountsPage.favProduct();
        System.out.println("favsProduct = " + favsProduct);
        System.out.println("choosingProduct = " + choosingProduct);
        Assert.assertEquals(favsProduct, choosingProduct);


    }

    @When("the product added to favorites is being removed")
    public void the_product_added_to_favorites_is_being_removed() {
        myAccountsPage.deletedFavProduct();

    }

    @Then("favorites page is now empty")
    public void favorites_page_is_now_empty() {
        myAccountsPage.emptyFavPage();
        String expectedResult = "İzlediğiniz bir ürün bulunmamaktadır.";
        String actualResult =myAccountsPage.emptyFav.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("expectedResult = " + expectedResult);
        System.out.println("actualResult = " + actualResult);


    }


}
