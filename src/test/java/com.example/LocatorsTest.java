package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocatorsTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Получаем последнюю версию драйвера браузера Chrome
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();

        //contentSettings.put("cookies",2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);

        // Создаём новый объект класса ChromeDriver
        driver = new ChromeDriver(options);

        String baseUrl = "https://www.saucedemo.com/";
        // Открываем главную страницу демо-сайта
        driver.get(baseUrl);
        // Открываем бразуер на полный экран
        driver.manage().window().maximize();

        // Ждём пока страница появится - этот способ подходит только для демонстрации
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCssLocators() {
        WebElement userNameInput = driver.findElement(By.cssSelector("[placeholder='Username']"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder='Password']"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
        loginButton.click();

        WebElement tShirtAddToCartButton = driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt"));
        tShirtAddToCartButton.click();

        WebElement bikeLightPageLink = driver.findElement(By.cssSelector("#item_0_img_link"));
        bikeLightPageLink.click();

        WebElement bikeLightAddToCartButton = driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light"));
        bikeLightAddToCartButton.click();

        WebElement cartLink = driver.findElement(By.cssSelector("#shopping_cart_container"));
        cartLink.click();

        assertEquals("Sauce Labs Bolt T-Shirt", driver.findElements(By.className("inventory_item_name")).get(0).getText());
        assertEquals("Sauce Labs Bike Light", driver.findElements(By.className("inventory_item_name")).get(1).getText());

        WebElement checkoutButton = driver.findElement(By.cssSelector("#checkout"));
        checkoutButton.click();

        assertEquals("First Name", driver.findElement(By.name("firstName")).getAttribute("placeholder"));
        assertEquals("Last Name", driver.findElement(By.name("lastName")).getAttribute("placeholder"));
        assertEquals("Zip/Postal Code", driver.findElement(By.name("postalCode")).getAttribute("placeholder"));
    }

    @Test
    public void testXpathLocators() {
        WebElement userNameInput = driver.findElement(By.xpath("//input[@id='user-name']"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();

        WebElement tShirtAddToCartButton = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        tShirtAddToCartButton.click();

        WebElement bikeLightPageLink = driver.findElement(By.xpath("//a[@id='item_0_img_link']"));
        bikeLightPageLink.click();

        WebElement bikeLightAddToCartButton = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']"));
        bikeLightAddToCartButton.click();

        WebElement cartLink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartLink.click();

        assertEquals("Sauce Labs Bolt T-Shirt", driver.findElements(By.className("inventory_item_name")).get(0).getText());
        assertEquals("Sauce Labs Bike Light", driver.findElements(By.className("inventory_item_name")).get(1).getText());

        WebElement checkoutButton = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkoutButton.click();

        assertEquals("First Name", driver.findElement(By.name("firstName")).getAttribute("placeholder"));
        assertEquals("Last Name", driver.findElement(By.name("lastName")).getAttribute("placeholder"));
        assertEquals("Zip/Postal Code", driver.findElement(By.name("postalCode")).getAttribute("placeholder"));
    }

    @AfterEach
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }
}
