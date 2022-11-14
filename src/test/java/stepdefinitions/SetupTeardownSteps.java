package stepdefinitions;

import globals.Globals;
import globals.World;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.restassured.RestAssured;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SetupTeardownSteps {

    private final World world;

    public SetupTeardownSteps(World injectedWorld) {

        world = injectedWorld;
    }

    @Before
    public void setup(Scenario scenario) {

        System.out.println("** Setting up for testrun");
        showTestName(scenario);
        initRestAssured();
        initBrowser();
        world.scenario = scenario;
    }

    @After
    public void teardown(Scenario scenario) {

        System.out.println("** Running teardown");
        closeBrowser(scenario);
        printResult(scenario);
    }

    public void initBrowser() {

        ChromeOptions chromeOptions = new ChromeOptions();

        world.setRunnerLocation(System.getProperty("runner", Globals.DEFAULT_RUNNER));

        switch (world.getRunnerLocation().toLowerCase()) {
            case "local" -> {
                System.out.println("##### LOCAL #####");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--use-fake-ui-for-media-stream");
                chromeOptions.addArguments("--disable-blink-features=\"BlockCredentialedSubresources\"");

                //Get beta Chrome
                chromeOptions.setBinary("C:/Program Files/Google/Chrome Beta/Application/chrome.exe");

                World.browser = new ChromeDriver(chromeOptions);
            }
            case "macos" -> {
                System.out.println("##### LOCAL #####");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--use-fake-ui-for-media-stream");
                chromeOptions.addArguments("--disable-blink-features=\"BlockCredentialedSubresources\"");

                World.browser = new ChromeDriver(chromeOptions);
            }
            case "gitlab" -> {
                System.out.println("##### GITLAB #####");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1920x1080");
                chromeOptions.addArguments("--use-fake-ui-for-media-stream");
                chromeOptions.addArguments("--use-fake-device-for-media-stream");
                chromeOptions.addArguments("--disable-blink-features=\"BlockCredentialedSubresources\"");
                chromeOptions.addArguments("--disable-async-dns");

                try {
                    World.browser =
                        new RemoteWebDriver(new URL("http://selenium__standalone-chrome:4444/wd/hub/"), chromeOptions);
                } catch (MalformedURLException mue) {
                    System.out.println("MalformedURLException occured during setting up of RemoteWebDriver:\n" + mue);
                }
            }
            default -> Assert.fail(String.format("Unknown value '%s' for property 'runner', please specify value "
                    + "'gitlab' for running tests on GitLab instance, or omit for local test execution",
                world.getRunnerLocation()));
        }

        World.browser.manage().window().maximize();
    }

    private void initRestAssured() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public void showTestName(Scenario scenario) {

        System.out.printf("**** Running scenario: %s%n", scenario.getName());
    }

    public void closeBrowser(Scenario scenario) {

        scenario.log(String.format("Current URL: %s", World.browser.getCurrentUrl()));
        scenario.attach(((TakesScreenshot) World.browser).getScreenshotAs(
            OutputType.BYTES), "image/png", scenario.getName().strip() + "_end.png");
        World.browser.quit();
    }

    public void printResult(Scenario scenario) {

        Status status = scenario.getStatus();
        System.out.printf("**** Test result: %s%n\n", status);
    }
}
