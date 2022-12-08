package stepdefinitions;

import dataobjects.Product;
import helpers.ApiHelper;
import helpers.SeleniumHelper;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.HomePage;

public class HomepageSteps {
    @ParameterType(".*")
    public Product.categoryEnum product(String pCategory) {
        return Product.categoryEnum.valueOf(pCategory.toUpperCase());
    }

    // ------------------------------------------------------------------------------------------------------
    // 'Given' step definitions
    @Given("the homepage is loaded")
    public void theHomepageIsLoaded() {
        new HomePage()
                .load();

        Assert.assertTrue("Homepage " + new HomePage().base_url + " was not loaded.", new HomePage().homePageIsLoaded());
    }

    // ------------------------------------------------------------------------------------------------------
    // 'When'/'And' step definitions

    @When("the customer adds a {product} {string} to its cart")
    public void theCustomerAddsAProductToCart(Product.categoryEnum productCategory, String productName) {
        Product prod = new Product(productName, productCategory);

        new HomePage()
                .load()
                .navigateToProductPage(prod)
                .clickAddToCart()
                .clickOKAlert();
    }

    // ------------------------------------------------------------------------------------------------------
    // 'Then' step definitions

    @Then("the {string} is added to the cart")
    public void theProductIsAddedToTheCart(String productName) {
        Product prod = new Product(productName);

        new HomePage()
                .load()
                .navigateToCartPage();

        Assert.assertTrue("Product " + prod.getName() + " was not shown as added to the cart.", new CartPage().productInCart(prod));
    }

    @Then("the total order sums to {int} euro")
    public void theTotalOrderSumsToEuro(int amount) {
    }

}