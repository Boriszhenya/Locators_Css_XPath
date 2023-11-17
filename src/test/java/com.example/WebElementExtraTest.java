package com.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebElementExtraTest extends BaseTest {

    @Test
    public void testColorFromString() {
        driver.get("https://selectorshub.com/xpath-practice-page/");
        WebElement checkoutButton = driver.findElement(By.xpath("//button[.='Checkout here']"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("#4caf50", Color.fromString(checkoutButton.getCssValue("background-color")).asHex());
    }

    @Test
    public void testHoverColorComparison() {
        driver.get("https://selectorshub.com/xpath-practice-page/");
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        // Эта последовательность действий переносит мышь на нужный элемент (hover)
        new Actions(driver).moveToElement(submitButton).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Color.fromString("#c36"), Color.fromString(submitButton.getCssValue("background-color")));
    }

    @Test
    public void testMenuItemsCount() { // 1
        driver.get("https://demoqa.com/elements");
        // Проверить что на верхнем уровне в левом меню ровно шесть элементов и первый из них Elements
    }

    @Test
    public void testRandomItemVisibility() { // 2
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
        // Проверить есть ли на странице и отображается ли элемент меню Gallery, вывести это в консоль
        // Если отображает то ассерт что элементов меню 5, если нет, то 4
    }

    @Test
    public void testCheckBoxSemiAppearance() { // 3
        driver.get("https://demoqa.com/checkbox");
        // Раскройте дерево (Home -> Documents) и кликните Office
        // Проверьте, что у оффиса полная галочка, а у Documents и Home "половинчатая"
    }

    @Test
    public void testFilteringToPizza() { // 4
        driver.get("https://demo.aspnetawesome.com/GridFilterRowServerSideData#Grid-filter-row-server-data");
        // Введите в фильтры такие значения, чтобы осталась только пицца с id 563
        // Проверьте, что отображается только одна строка и в этой строке ожидаемые значения (например id, person, chef)
    }

    @Test
    public void testSumAndOrderMessageE2E() { // 5
        driver.get("https://www.saucedemo.com/");
        // Войдите в систему как standard_user, отсортируйте товары по цене от маленькой до большой
        // Добавьте в корзину самый дешевый и второй самый дорогой товар, передите в корзину продолжите с заказом
        // Введите данные -> далее; проверьте что общая сумма 41.02 -> Завершите заказ; проверьте что сообщения:
        // "Thank you for your order!" и "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        // Нажмите "Back Home" и каким хотите способом убедитесь, что вы на главной странице
    }
}
