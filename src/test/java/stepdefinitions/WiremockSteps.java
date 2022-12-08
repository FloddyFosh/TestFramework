package stepdefinitions;

import helpers.ApiHelper;
import helpers.WiremockHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WiremockSteps {

    @Given("^the Wiremock server is started$")
    public void startWiremockServer() {

        new WiremockHelper()
            .startWireMockServer();
    }

    @Given("^the Wiremockstub 1 is started")
    public void startStub1() {

        new WiremockHelper().setupStub1();
    }

    @Given("^the Wiremockstub 2 is started")
    public void startStub2() {

        new WiremockHelper().setupStub2();
    }

    @Then("^the Wiremock server is stopped")
    public void stopWiremockServer() {

        new WiremockHelper()
            .stopWireMockServer();
    }

    @When("you ask Jochem about the weather for {string}")
    public void youAskJochemAboutTheWeatherForToday(String day) {
        ApiHelper
                .askJochemWeather(day);
    }
}