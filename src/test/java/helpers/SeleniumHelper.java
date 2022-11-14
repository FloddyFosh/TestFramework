package helpers;

import globals.Globals;
import globals.World;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static globals.World.browser;

public class SeleniumHelper {

  // Browser functions

  public static void navigateToPage(String urlToNavigateTo) {

    browser.navigate().to(urlToNavigateTo);
  }

  public static boolean waitForUrlToBe(String expectedUrl) {

    return waitForUrlToBe(expectedUrl, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static boolean waitForUrlToBe(String expectedUrl, int timeout) {
    try {
      new WebDriverWait(browser, Duration.ofSeconds(timeout)).until(
              ExpectedConditions.urlToBe(expectedUrl));
      return true;
    } catch (TimeoutException ex) {
      return false;
    }
  }

  // -----------------
  //
  // Basic functions

  public static void sendKeys(By by, String textToSend) {

    sendKeys(by, textToSend, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static void sendKeys(By by, String textToSend, int timeout) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(by));
      browser.findElement(by).sendKeys(textToSend);
    } catch (TimeoutException ex) {
      Assert.fail(String.format("Error in sendKeys(): Element %s could not be found", by.toString()));
    } catch (StaleElementReferenceException | ElementNotInteractableException ex) {
      new WebDriverWait(browser, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(by));
      browser.findElement(by).sendKeys(textToSend);
    }
  }

  public static void sendKeys(By by, Keys keyToSend) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.elementToBeClickable(by));
      browser.findElement(by).sendKeys(keyToSend);
    } catch (TimeoutException ex) {
      Assert.fail(String.format("Error in sendKeys(): Element %s could not be found", by.toString()));
    }
  }

  public static void click(By by) {

    click(by, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static void click(By by, int timeout) {

    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(timeout)).until(
              ExpectedConditions.elementToBeClickable(by));
      World.browser.findElement(by).click();
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String.format("Error in click(): Element %s could not be found", by.toString()));
    } catch (StaleElementReferenceException sere) {
      new WebDriverWait(World.browser, Duration.ofSeconds(timeout)).until(
              ExpectedConditions.elementToBeClickable(by));
      World.browser.findElement(by).click();
    } catch (ElementClickInterceptedException ecie) {
      System.out.println("ElementClickInterceptedException: " + ecie);
      sleepToDebug(5000);
      browser.findElement(by).click();
    }
  }

  public static void select(By by, String valueToBeSelected) {

    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.elementToBeClickable(by));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String.format("Error in select(): Dropdown element %s could not be found", by.toString()));
    }

    try {
      new Select(World.browser.findElement(by)).selectByVisibleText(valueToBeSelected);
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String.format("Error in select(): Value '%s' could not be selected from dropdown element %s",
              valueToBeSelected, by.toString()));
    }
  }

  public static void acceptAlert() {
    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.alertIsPresent());
      Alert alert = World.browser.switchTo().alert();
      alert.accept();
    } catch (TimeoutException | NoAlertPresentException ex) {
      System.out
              .printf("Didn't see an alert to accept within %d seconds, moving on...%n", Globals.DEFAULT_UI_TIMEOUT);
    }
  }

  // -------------
  //
  // Get functions

  public static String getElementText(By by) {

    return getElementText(by, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static String getElementText(By by, int timeout) {

    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(timeout)).until(
              ExpectedConditions.visibilityOfElementLocated(by));
      return World.browser.findElement(by).getText();
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String.format("Error in getElementText(): Element %s could not be found", by.toString()));
    }
    return "";
  }

  public static String getElementValue(By by) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.visibilityOfElementLocated(by));
      return browser.findElement(by).getAttribute("value");
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String.format("Error in getElementText(): Element %s could not be found", by.toString()));
    }
    return "";
  }

  public static String getElementAttribute(By by, String attribute) {

    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.visibilityOfElementLocated(by));
      return World.browser.findElement(by).getAttribute(attribute);
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String.format("Error in getElementText(): Element %s could not be found", by.toString()));
    }
    return "";
  }

  // Wait functions

  public static void waitForElementToBeClickable(By by) {

    waitForElementToBeClickable(by, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static void waitForElementToBeClickable(By by, int timeout) {
    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(timeout)).until(
              ExpectedConditions.elementToBeClickable(by));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(
              String.format("Error in waitForElementToBeVisible(): Element %s could not be found", by.toString()));
    }
  }

  public static void waitForElementToBeVisible(By by) {

    waitForElementToBeVisible(by, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static void waitForElementToBeVisible(By by, int timeoutInSeconds) {

    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(timeoutInSeconds)).until(
              ExpectedConditions.visibilityOfElementLocated(by));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(
              String.format("Error in waitForElementToBeVisible(): Element %s could not be found", by.toString()));
    }
  }

  public static void waitAndClickForElementToBeVisible(By element, By click) {

    waitAndClickForElementToBeVisible(element, click, 5, 2);
  }

  public static void waitAndClickForElementToBeVisible(By element, By click, int timeoutInSeconds, int maxRetries) {

    int i = 0;

    while (i < maxRetries) {
      try {
        new WebDriverWait(World.browser, Duration.ofSeconds(timeoutInSeconds)).until(
                ExpectedConditions.visibilityOfElementLocated(element));
        return;
      } catch (TimeoutException | NoSuchElementException ex) {
        System.out.println("No such element found, this is refresh number " + i);
        click(click);
        i++;
      }
    }
    Assert.fail(String.format("Error in waitForElementToBeVisibleAndClick(): Element %s could not be found",
            element.toString()));
  }

  public static void waitForElementToDisappear(By by) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.invisibilityOfElementLocated(by));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert
              .fail(String.format("Error in waitForElementToAppear(): Element %s did not disappear", by.toString()));
    }
  }

  public static boolean elementContainsText(By by, String partialElementText) {

    return elementContainsText(by, partialElementText, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static boolean elementContainsText(By by, String partialElementText, int timeout) {
    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(timeout)).until(
              ExpectedConditions.textToBePresentInElementLocated(by, partialElementText));
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      return false;
    }
  }

  public static void waitForElementToContainText(By by, String partialElementText) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.textToBePresentInElementLocated(by, partialElementText));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String
              .format("Error in waitForElementToContainText(): Element %s did not contain text '%s'", by.toString(),
                      partialElementText));
    }
  }

  public static void waitForElementToNotContainPartialText(By by, String partialElementText) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(by, partialElementText)));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String
              .format("Error in waitForElementToNotContainText(): Element %s did contain text '%s'", by.toString(),
                      partialElementText));
    }
  }

  public static void waitForElementToNotContainExactText(By by, String partialElementText) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.not(ExpectedConditions.textToBe(by, partialElementText)));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String
              .format("Error in waitForElementToNotContainText(): Element %s did contain text '%s'", by.toString(),
                      partialElementText));
    }
  }

  public static void waitForAttributeToContainValue(By by, String attribute, String partialValue) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.attributeContains(by, attribute, partialValue));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String
              .format("Error in waitForAttributeToContainValue(): Attribute value %s does not contain '%s'",
                      by.toString(), partialValue));
    }
  }

  public static void waitForAttributeToBeValue(By by, String attribute, String value) {

    try {
      new WebDriverWait(browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.attributeToBe(by, attribute, value));
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String
              .format("Error in waitForAttributeToContainValue(): Attribute value %s is not equal to '%s'",
                      by.toString(), value));
    }
  }

  public static boolean waitForElementToContainValue(By by, String partialElementValue) {
    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.textToBePresentInElementValue(by, partialElementValue));
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      return false;
    }
  }

  // ---------
  //
  // Is functions

  public static boolean elementIsVisible(By by) {

    return elementIsVisible(by, Globals.DEFAULT_UI_TIMEOUT);
  }

  public static boolean elementIsVisible(By by, int timeout) {
    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(timeout)).until(
              ExpectedConditions.visibilityOfElementLocated(by));
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      return false;
    }
  }

  // ----------------
  //
  // Special functions

  public static void waitAndSwitchToIframe(By by) {

    try {
      new WebDriverWait(World.browser, Duration.ofSeconds(Globals.DEFAULT_UI_TIMEOUT)).until(
              ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
      sleepToDebug(1000);
    } catch (TimeoutException | NoSuchElementException ex) {
      Assert.fail(String.format("Error in waitAndSwitchToIFrame(): IFrame %s could not be found", by.toString()));
    }
  }

  public static void switchBackToDefaultContentFromIframe() {

    World.browser.switchTo().defaultContent();
  }

  public static void selectTabWithRetryUntilActive(By by) {

    int count = 0;
    while (
            (count < Globals.MAX_NUMBER_OF_RETRIES_FOR_ANNOYING_WEB_ELEMENTS)
                    &&
                    !(browser.findElement(by).getAttribute("class").contains("active"))
    ) {
      System.out.printf("Click #%d for annoying tab%n", count);
      count++;
      sleepToDebug(500);
      browser.findElement(by).click();
    }
  }

  public static void switchToTabNumber(int tabNumber) {

    ArrayList<String> tabs = new ArrayList<>(World.browser.getWindowHandles());
    World.browser.switchTo().window(tabs.get(tabNumber));
  }

  public static void sleepToDebug(long milliSeconds) {

    try {
      Thread.sleep(milliSeconds);
    } catch (InterruptedException ie) {
      System.out.println("Exception: " + ie);
    }
  }
}
