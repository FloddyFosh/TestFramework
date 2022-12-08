package pages;

import dataobjects.Product;
import helpers.SeleniumHelper;
import org.openqa.selenium.By;

import java.math.BigDecimal;
import java.util.List;
import static globals.World.browser;
public class CartPage {

    public final String base_url = "https://www.demoblaze.com/cart.html";

    public final By totalOrderSum = By.id("totalp");
    public final By cartContents = By.id("tbodyid");

    public CartPage load() {
        SeleniumHelper.navigateToPage(base_url);
        return this;
    }

    public BigDecimal getTotalOrderSumShown() {
        return new BigDecimal(SeleniumHelper.getElementText(totalOrderSum));
    }

    public boolean productInCart(Product p) {
        String xPathLoc = String.format("//td[contains(normalize-space(),'%s')]", p.getName());
        return SeleniumHelper.elementIsVisible(By.xpath(xPathLoc));
    }

}
