package pages;

import helpers.SeleniumHelper;
import org.openqa.selenium.By;

public class HomePage {

    private final By carouselSlide = By.xpath("//div[contains(@class,'carousel slide')]");

    public HomePage load() {

        SeleniumHelper.navigateToPage("https://www.demoblaze.com/index.html");
        return this;
    }

    public boolean landedOnHomePage() {

        return SeleniumHelper.elementIsVisible(carouselSlide);
    }
}