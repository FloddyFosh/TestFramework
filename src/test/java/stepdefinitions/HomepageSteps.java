package stepdefinitions;

import dataobjects.Customer;
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

    private Customer customer;

    @ParameterType(".*")
    public Product.CategoryEnum product(String pcategory) {
        return Product.CategoryEnum.valueOf(pcategory.toUpperCase());
    }

    // ------------------------------------------------------------------------------------------------------
    // 'Given' step definitions
    @Given("the homepage is loaded")
    public void theHomepageIsLoaded() {
        new HomePage()
                .load();

        Assert.assertTrue("Homepage " + new HomePage().baseUrl + " was not loaded.",
                new HomePage().homePageIsLoaded());
    }

    // ------------------------------------------------------------------------------------------------------
    // 'When'/'And' step definitions

    @When("the customer adds a {product} {string} to its cart")
    public void theCustomerAddsProductToCart(Product.CategoryEnum productCategory, String productName) {
        Product prod = new Product(productName, productCategory);

        new HomePage()
                .load()
                .navigateToProductPage(prod)
                .addProductsToCart();
    }

    @When("the customer places an order")
    public void theCustomerPlacesAnOrder() {
        customer = new Customer("Chris Bakker", "Nederland", "Hoofddorp", "1234 5678 9012 3456", "01", "22");

        new CartPage()
                .load()
                .placeOrder(customer);
    }

    // ------------------------------------------------------------------------------------------------------
    // 'Then' step definitions

    @Then("the {string} is added to the cart")
    public void theProductIsAddedToTheCart(String productName) {
        Product prod = new Product(productName);

        new HomePage()
                .load()
                .navigateToCartPage();

        Assert.assertTrue(String.format("Product %s was not shown as added to the cart.",
                prod.getName()), new CartPage().productIsInCart(prod));
    }

    @Then("the total order sums to {int} euro")
    public void theTotalOrderSumsToEuro(int amount) {
        new HomePage()
                .load()
                .navigateToCartPage();

        Assert.assertEquals(String.format("Total order does not sum to %s.",
                amount), new CartPage().getTotalOrderSumShown().intValueExact(), amount);
    }

    @Then("the order is processed successfully")
    public void theOrderIsProcessedSuccessfully() {
        Assert.assertTrue("The order is not processed successfully.",
                new CartPage().orderIsSuccessfull(customer));
    }
}