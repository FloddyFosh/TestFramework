package stepdefinitions;

import dataentities.Customer;
import globals.World;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.HomePage;

public class PurchaseSteps {

    private final World world;

    public PurchaseSteps(World injectedWorld) {

        world = injectedWorld;
    }

    @Given("^the homepage is loaded$")
    public void browserStarted() {

        new HomePage()
            .load();

        Assert.assertTrue("Carousel on homepage not found", new HomePage().landedOnHomePage());
    }

    @When("^the customer adds a phone (.*) to its cart$")
    public void addPhoneToCart(String product) {

        new HomePage()
            .load()
            .navigateToPhone(product)
            .addToCart();
    }

    @When("^the customer adds a laptop (.*) to its cart$")
    public void addLaptopToCart(String product) {

        new HomePage()
            .load()
            .navigateToLaptop(product)
            .addToCart();
    }

    @When("^the customer places an order$")
    public void placeOrder() {

        world.setCustomer(new Customer());

        new CartPage()
            .load()
            .placeOrder(world.getCustomer());
    }

    @Then("^the (.*) is added to the cart$")
    public void productIsAddedToCart(String product) {

        new CartPage()
            .load();

        Assert.assertTrue("Product not found in table", new CartPage().productInCart(product));
    }

    @Then("^the total order sums to (.*) euro$")
    public void sumCartIs(int total) {

        new CartPage()
            .load();

        Assert.assertEquals(String.format("Total value of %s not found", total),
            total, new CartPage().totalCartValue());
    }

    @Then("^the order is processed successfully$")
    public void orderProcessedSuccessfully() {

        Assert.assertTrue("The order was not created successfully",
            new CartPage().orderProcessedSuccessfully(world.customer));
    }
}