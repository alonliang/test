package test;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.formatter.model.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class Hooks {
    public static WebDriver driver;


    @Before
    public void openBrowser() throws MalformedURLException {

        if ("Linux".equals(System.getProperty("os.name"))) {
            Capabilities chromeCapabilities = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4447/wd/hub"), chromeCapabilities);
        }else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

    }

    @After
    public void embedScreenshot(Scenario scenario) {
        System.out.println("Close Browser...");
        try {
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");

        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        driver.quit();
    }
}