package pages;

import helpers.SeleniumHelper;
import org.openqa.selenium.By;

public class HomePage {

    private final By carouselSlide = By.xpath("//div[contains(@class,'carousel slide')]");
    private final By buttonAddToCart = By.xpath("//a[contains(text(),'Add to cart')]");
    private final By buttonFilterPhones = By.xpath("//a[contains(text(),'Phones')]");
    private final By buttonFilterLaptops = By.xpath("//a[contains(text(),'Laptops')]");



    public HomePage load() {

        SeleniumHelper.navigateToPage("https://www.demoblaze.com/index.html");
        return this;
    }

    public boolean landedOnHomePage() {

        return SeleniumHelper.elementIsVisible(carouselSlide);
    }

    public HomePage navigateToPhone(String phone) {

        SeleniumHelper.click(buttonFilterPhones);

        String xpathLocator = String.format("//a[contains(text(),'%s')]", phone);
        By phoneLink = By.xpath(xpathLocator);
        SeleniumHelper.click(phoneLink);
        return this;
    }

    public HomePage navigateToLaptop(String laptop) {

        SeleniumHelper.click(buttonFilterLaptops);

        String xpathLocator = String.format("//a[contains(text(),'%s')]", laptop);
        By phoneLink = By.xpath(xpathLocator);
        SeleniumHelper.click(phoneLink);
        return this;
    }

    public HomePage addToCart() {

        SeleniumHelper.click(buttonAddToCart);
        SeleniumHelper.acceptAlert();
        return this;
    }
}