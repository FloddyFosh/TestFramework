package globals;

import io.cucumber.java.Scenario;
import lombok.Data;
import org.openqa.selenium.WebDriver;

/******************************
 * The World class contains all objects that are shared between different steps in Cucumber scenarios
 * It's injected into the stepdefinitions classes using a mechanism called dependency injection, by
 * means of the cucumber-picocontainer library. For more information as well as some examples, see
 * http://www.thinkcode.se/blog/2017/04/01/sharing-state-between-steps-in-cucumberjvm-using-picocontainer
 *
 * This will ONLY WORK if cucumber-picocontainer is added as maven-dependency in pom.xml
 */

@Data
public class World {

  /******************************
   * Browser variables
   */
  public static WebDriver browser;
  public String runnerLocation;

  /******************************
   * Test-related configuration
   */
  public Scenario scenario;
}
