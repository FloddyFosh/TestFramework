package pages;

import dataentities.Customer;
import helpers.SeleniumHelper;
import org.openqa.selenium.By;

public class CartPage {

    private final By totalValue = By.xpath("//h3[contains(@id,'totalp')]");
    private final By buttonPlaceOrder = By.xpath("//button[contains(@class,'btn-success')]");
    private final By inputName = By.xpath("//input[@id='name']");
    private final By inputCountry = By.xpath("//input[@id='country']");
    private final By inputCity = By.xpath("//input[@id='city']");
    private final By inputCreditcard = By.xpath("//input[@id='card']");
    private final By inputMonth = By.xpath("//input[@id='month']");
    private final By inputYear = By.xpath("//input[@id='year']");
    private final By buttonFinishOrder = By.xpath("//button[contains(text(),'Purchase')]");
    private final By textSuccessfullOrder = By.xpath("//h2[text()='Thank you for your purchase!']");

    public CartPage load() {

        SeleniumHelper.navigateToPage("https://www.demoblaze.com/cart.html");
        return this;
    }

    public boolean productInCart(String product) {

        String xpathLocator = String.format("//td[contains(text(),'%s')]", product);
        By productInTable = By.xpath(xpathLocator);
        return SeleniumHelper.elementIsVisible(productInTable);
    }

    public int totalCartValue() {

        String value = SeleniumHelper.getElementText(totalValue);
        return Integer.parseInt(value);
    }

    public void placeOrder(Customer customer) {

        SeleniumHelper.click(buttonPlaceOrder);
        SeleniumHelper.sleepToDebug(500);
        SeleniumHelper.sendKeys(inputName, customer.getName());
        SeleniumHelper.sendKeys(inputCountry, customer.getCountry());
        SeleniumHelper.sendKeys(inputCity, customer.getCity());
        SeleniumHelper.sendKeys(inputCreditcard, customer.getCreditcard());
        SeleniumHelper.sendKeys(inputMonth, customer.getCreditcardMonth());
        SeleniumHelper.sendKeys(inputYear, customer.getCreditcardYear());
        SeleniumHelper.click(buttonFinishOrder);
    }

    public boolean orderProcessedSuccessfully(Customer customer) {

        if (!SeleniumHelper.elementIsVisible(textSuccessfullOrder)) {
            return false;
        }

        String xpathLocator = String.format("//p[text()='Amount: %s USD']", "790");
        By textTotalAmount = By.xpath(xpathLocator);
        if (!SeleniumHelper.elementIsVisible(textTotalAmount)) {
            return false;
        }

        String xpathLocator2 = String.format("//p[text()='Card Number: %s']", customer.getCreditcard());
        By textCreditCardNumber = By.xpath(xpathLocator2);
        if (!SeleniumHelper.elementIsVisible(textCreditCardNumber)) {
            return false;
        }

        String xpathLocator3 = String.format("//p[text()='Name: %s']", customer.getName());
        By textName = By.xpath(xpathLocator3);
        return SeleniumHelper.elementIsVisible(textName);
    }
}