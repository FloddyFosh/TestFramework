package stepdefinitions;

import helpers.ApiHelper;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;

public class GeneralSteps {

    @When("^browser is started$")
    public void browserStarted() {

        System.out.println("Still works");
        new HomePage()
            .load();

        Assert.assertTrue("Carousel on homepage not found", new HomePage().landedOnHomePage());
    }

    @When("^you ask Jochem an open question$")
    public void askJochemOpenQuestion() {

        ApiHelper.askJochem();
    }
}