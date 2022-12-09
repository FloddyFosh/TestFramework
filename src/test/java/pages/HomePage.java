package pages;

import dataobjects.Product;
import helpers.SeleniumHelper;
import org.openqa.selenium.By;

public class HomePage {

    public final String baseUrl = "https://www.demoblaze.com/index.html";

    public final By cartPageButton = By.id("cartur");

    private final By filterByPhoneButton = By.xpath(
            "//a[contains(@class, 'list-group-item') and contains(normalize-space(), 'Phones')]");
    private final By filterByLaptopButton = By.xpath(
            "//a[contains(@class, 'list-group-item') and contains(normalize-space(), 'Laptops')]");
    private final By filterByMonitorButton = By.xpath(
            "//a[contains(@class, 'list-group-item') and contains(normalize-space(), 'Monitors')]");
    private final By previousButton = By.id("prev2");
    private final By nextButton = By.id("next2");

    public HomePage load() {
        SeleniumHelper.navigateToPage(baseUrl);
        return this;
    }

    public boolean homePageIsLoaded() {
        if (SeleniumHelper.waitForUrlToBe(baseUrl)) {
            return SeleniumHelper.waitForPageToBeLoaded();
        }
        return false;
    }

    public HomePage filterByProductCategory(Product.CategoryEnum category) {
        switch (category) {
            case PHONE -> SeleniumHelper.click(filterByPhoneButton);
            case LAPTOP -> SeleniumHelper.click(filterByLaptopButton);
            case MONITOR -> SeleniumHelper.click(filterByMonitorButton);
            case UNKNOWN -> {
                break;
            }
            default -> {
                break;
            }
        }
        return this;
    }

    public ProductPage navigateToProductPage(Product p) {
        filterByProductCategory(p.getCategory());

        By productLoc = By.xpath(String.format("//a[contains(normalize-space(), '%s')]", p.getName()));
        while (!SeleniumHelper.elementIsVisible(productLoc) && SeleniumHelper.elementIsVisible(nextButton)) {
            SeleniumHelper.click(nextButton);
        }

        SeleniumHelper.click(productLoc);
        return new ProductPage();
    }

    public CartPage navigateToCartPage() {
        SeleniumHelper.click(cartPageButton);
        return new CartPage();
    }
}