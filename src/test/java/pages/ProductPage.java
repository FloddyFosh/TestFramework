package pages;

import helpers.SeleniumHelper;
import org.openqa.selenium.By;

public class ProductPage {

    public final String baseUrl = "https://www.demoblaze.com/prod.html";
    private final By addToCartButton = By.xpath(
            "//a[contains(@class, 'btn') and contains(normalize-space(), 'Add to cart')]");

    public ProductPage load(int productId) {
        SeleniumHelper.navigateToPage(baseUrl + "?idp_=" + productId);
        return this;
    }

    public ProductPage addProductsToCart() {
        SeleniumHelper.click(addToCartButton);
        SeleniumHelper.acceptAlert();
        return this;
    }

}
