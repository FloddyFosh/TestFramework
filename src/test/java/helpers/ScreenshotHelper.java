package helpers;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {

    public static void screenshot(WebDriver driver, String imageName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(String.format("ScreenShots/%s.png", imageName)));
            System.out.println("ScreenShot Taken");
        } catch (Exception e) {
            System.out.println("Exception while taking ScreenShot " + e.getMessage());
        }
    }
}
