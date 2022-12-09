package pages;

import helpers.SeleniumHelper;
import org.openqa.selenium.By;

public class ProductPage {

    public final String base_url = "https://www.demoblaze.com/prod.html";
    private final By addToCartButton = By.xpath("//a[contains(@class, 'btn') and contains(normalize-space(), 'Add to cart')]");

    public ProductPage load(int productID) {
        SeleniumHelper.navigateToPage(base_url + "?idp_=" + productID);
        return this;
    }

    public ProductPage addProductsToCart() {
        SeleniumHelper.click(addToCartButton);
        SeleniumHelper.acceptAlert();
        return this;
    }

}
