package pages;

import dataobjects.Customer;
import dataobjects.Product;
import helpers.SeleniumHelper;
import org.openqa.selenium.By;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static globals.World.browser;
public class CartPage {

    public final String base_url = "https://www.demoblaze.com/cart.html";

    public final By totalOrderSum = By.id("totalp");
    public final By placeOrderButton = By.xpath("//button[normalize-space()='Place Order']");
    public final By cartContents = By.id("tbodyid");

    //Place Order Form
    public final By orderForm = By.xpath("//div[@id='orderModal']//form");
    public final By orderFormName = By.xpath("//input[@id='name']");
    public final By orderFormCountry = By.xpath("//input[@id='country']");
    public final By orderFormCity= By.xpath("//input[@id='city']");
    public final By orderFormCard = By.xpath("//input[@id='card']");
    public final By orderFormMonth = By.xpath("//input[@id='month']");
    public final By orderFormYear = By.xpath("//input[@id='year']");
    public final By orderFormPurchaseButton = By.xpath("//button[normalize-space()='Purchase']");
    public final By purchaseAlert = By.xpath("//div[contains(@class,'sweet-alert')]");
    public final By purchaseAlertContents = By.xpath("//p[contains(@class,'lead text-muted')]");

    public CartPage load() {
        SeleniumHelper.navigateToPage(base_url);
        return this;
    }

    public BigDecimal getTotalOrderSumShown() {
        return new BigDecimal(SeleniumHelper.getElementText(totalOrderSum));
    }

    public boolean productIsInCart(Product p) {
        String xPathLoc = String.format("//td[contains(normalize-space(),'%s')]", p.getName());
        return SeleniumHelper.elementIsVisible(By.xpath(xPathLoc));
    }

    public CartPage placeOrder(Customer c) {
        SeleniumHelper.click(placeOrderButton);
        if(SeleniumHelper.elementIsVisible(orderForm)) {
            SeleniumHelper.sendKeys(orderFormName, c.getName());
            SeleniumHelper.sendKeys(orderFormCountry, c.getCountry());
            SeleniumHelper.sendKeys(orderFormCity, c.getCity());
            SeleniumHelper.sendKeys(orderFormCard, c.getCreditcardNumber());
            SeleniumHelper.sendKeys(orderFormMonth, c.getCreditcardMonth());
            SeleniumHelper.sendKeys(orderFormYear, c.getCreditcardYear());
        }
        SeleniumHelper.click(orderFormPurchaseButton);
        return this;
    }

    public boolean orderIsSuccessfull(Customer c) {
        if(SeleniumHelper.elementIsVisible(purchaseAlert)) {
            String contentText = SeleniumHelper.getElementText(purchaseAlertContents);
            return contentText.contains(String.format("Card Number: %s\nName: %s\n",c.getCreditcardNumber(), c.getName()));
        }
        return false;
    }

}
