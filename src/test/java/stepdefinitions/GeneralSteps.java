package stepdefinitions;

import dataobjects.Product;
import helpers.ApiHelper;
import helpers.SeleniumHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;

public class GeneralSteps {

    // ------------------------------------------------------------------------------------------------------
    // Test Steps
    @When("^browser is started$")
    public void browserStarted() {
        new HomePage()
            .load();

        //Assert.assertTrue("Carousel on homepage not found", new HomePage().landedOnHomePage());
    }

    @When("^you ask Jochem an open question$")
    public void askJochemOpenQuestion() {
        ApiHelper.askJochem();
    }
}