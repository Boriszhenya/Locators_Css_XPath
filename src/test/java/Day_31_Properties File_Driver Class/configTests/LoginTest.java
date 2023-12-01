package configTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigurationReader;

public class LoginTest extends TestBase {

    @Test
    public void loginTest(){
        driver.get(ConfigurationReader.get("url"));
        driver.findElement(By.cssSelector("#rcc-confirm-button")).click();
        driver.findElement(By.cssSelector("#loginpage-input-email")).sendKeys(ConfigurationReader.get("userName"));
        driver.findElement(By.cssSelector("#loginpage-form-pw-input")).sendKeys(ConfigurationReader.get("userPassword"));
        driver.findElement(By.cssSelector("#loginpage-form-btn")).click();
        String headerText = driver.findElement(By.cssSelector("#dashboard-h1")).getText();
        Assert.assertEquals(headerText, "Dashboard");
    }

    @Test
    public void loginTest2(){
        driver.get(ConfigurationReader.get("url"));
        driver.findElement(By.cssSelector("#rcc-confirm-button")).click();
        driver.findElement(By.cssSelector("#loginpage-input-email")).click();
        driver.findElement(By.cssSelector("#loginpage-form-pw-input")).click();
    }

    @Test
    public void loginTest3(){
        driver.get(ConfigurationReader.get("url"));
        driver.findElement(By.cssSelector("#rcc-confirm-button")).click();
    }
}
