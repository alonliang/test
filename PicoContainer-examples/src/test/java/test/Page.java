package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page {

    public WebDriver driver;

    public Page() {
        this.driver = Hooks.driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "kw")
    public WebElement input;

    @FindBy(id = "su")
    public WebElement searchButton;

    public void openPage() throws Exception{
        driver.get("http://baidu.com");
        Thread.sleep(1000);
    }

    public void search(String value) throws Exception {
        input.sendKeys(value);
        searchButton.click();
        Thread.sleep(2000);
    }



}
