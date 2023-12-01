package com.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//        List<WebElement> leftBlock = driver.findElements(By.xpath("//span[@class='group-header']"));
       // List<WebElement> leftBlock = driver.findElements(By.xpath("//div[@class='header-text']"));

        List<WebElement> leftBlock = driver.findElements(By.xpath("//div[@class='header-text']"));

        Assertions.assertEquals(6, leftBlock.size());
        Assertions.assertEquals("Elements", leftBlock.get(0).getText());
        //Assertions.assertEquals("Elements\n ", leftBlock.get(0).getText());

        /*Вопрос почему закоменченый локатор выдает не просто слово "Elements" а добавляет к нему переход на
        новую строку и пробел*/

    }

    @Test
    public void testRandomItemVisibility() { // 2
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
        // Проверить есть ли на странице и отображается ли элемент меню Gallery, вывести это в консоль
        // Если отображает то ассерт что элементов меню 5, если нет, то 4

        List<WebElement> elements = driver.findElements(By.xpath("//ul/li"));
        WebElement elementGallery = driver.findElement(By.xpath("//ul//*[text()='Gallery']"));
        if (elements.size() == 5) {
            System.out.println("Yra");
            Assertions.assertEquals("Gallery", elementGallery.getText());
        } else {
            System.out.println("No");
            Assertions.assertEquals(5, elements.size());
        }
    }

    @Test
    public void testCheckBoxSemiAppearance() { // 3
        driver.get("https://demoqa.com/checkbox");
        // Раскройте дерево (Home -> Documents) и кликните Office
        // Проверьте, что у оффиса полная галочка, а у Documents и Home "половинчатая"

        WebElement buttonHome = driver.findElement(By.xpath("//input[@id='tree-node-home']/../../button"));
        buttonHome.click();

        WebElement buttonDocuments = driver.findElement(By.xpath("//input[@id='tree-node-documents']/../../button"));
        buttonDocuments.click();

        WebElement buttonOffice = driver.findElement(By.xpath("//span[text()='Office']"));
        buttonOffice.click();


        WebElement officeBox = driver.findElement(By.xpath("//*[@for='tree-node-office']//*[name()='svg']"));
        assertTrue(officeBox.getAttribute("class").contains("icon-check"));

        WebElement documentBox = driver.findElement(By.xpath("//*[@for='tree-node-documents']//*[name()='svg']"));
        assertTrue(documentBox.getAttribute("class").contains("icon-half-check"));

        WebElement homeBox = driver.findElement(By.xpath("//*[@for='tree-node-home']//*[name()='svg']"));
        assertTrue(homeBox.getAttribute("class").contains("icon-half-check"));


    }

    @Test
    public void testFilteringToPizza() { // 4
        driver.get("https://demo.aspnetawesome.com/GridFilterRowServerSideData#Grid-filter-row-server-data");
        // Введите в фильтры такие значения, чтобы осталась только пицца с id 563
        // Проверьте, что отображается только одна строка и в этой строке ожидаемые значения (например id, person, chef)

        WebElement foodInput = driver.findElement(By.xpath("//*[@id='GridFrow2fltFood-awed']"));
        foodInput.click();
        WebElement foodInputText = driver.findElement(By.xpath("//*[@id='GridFrow2fltFood-dropmenu']//input"));
        foodInputText.sendKeys("Pizza", Keys.ENTER);

        WebElement chefInput = driver.findElement(By.xpath("//*[@id='GridFrow2fltChef-awed']"));
        chefInput.click();
        WebElement chefInputText = driver.findElement(By.xpath("//*[@id='GridFrow2fltChef-dropmenu']//input"));
        chefInputText.sendKeys("Naked Chef", Keys.ENTER);

        WebElement personInputText = driver.findElement(By.xpath("//*[@id='GridFrow2fltPerson-awed']"));
        personInputText.sendKeys("Jeremy", Keys.ENTER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='awe-content awe-tablc']//tbody//tr"));

        Assertions.assertEquals(1, list.size());

        List<WebElement> listA = driver.findElements(By.xpath("//div[@class='awe-content awe-tablc']//tbody//tr/td"));

        Assertions.assertEquals("649", listA.get(0).getText());
        Assertions.assertEquals("Pizza", listA.get(2).getText());
        Assertions.assertEquals("Naked Chef", listA.get(7).getText());
    }

    @Test
    public void testSumAndOrderMessageE2E() { // 5
        driver.get("https://www.saucedemo.com/");
        // Войдите в систему как standard_user, отсортируйте товары по цене от маленькой до большой
        // Добавьте в корзину самый дешевый и второй самый дорогой товар, передите в корзину продолжите с заказом
        // Введите данные -> далее; проверьте что общая сумма 41.02 -> Завершите заказ; проверьте что сообщения:
        // "Thank you for your order!" и "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        // Нажмите "Back Home" и каким хотите способом убедитесь, что вы на главной странице

        String login = "standard_user";
        String password = "secret_sauce";

        WebElement loginInput = driver.findElement(By.id("user-name"));
        loginInput.sendKeys(login, Keys.ENTER);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password, Keys.ENTER);

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();

        WebElement buttonFilter = driver.findElement(By.className("product_sort_container"));
        buttonFilter.click();

        WebElement lowCost = driver.findElement(By.xpath("//*[contains(text(),'low to high')]"));
        lowCost.click();

        List<WebElement> listAddToCard = driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']"));

        listAddToCard.get(0).click();
        listAddToCard.get(listAddToCard.size() - 2).click();

        WebElement container = driver.findElement(By.id("shopping_cart_container"));
        container.click();

        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();

        WebElement firstName = driver.findElement(By.xpath("//*[@id='first-name']"));
        firstName.sendKeys("Borys", Keys.TAB);

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Pedorenko", Keys.TAB);

        WebElement zip = driver.findElement(By.id("postal-code"));
        zip.sendKeys("04758");


        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();

        WebElement totalPrice = driver.findElement(By.xpath("//*[@class='summary_info_label summary_total_label']"));
//******эту часть кода я позаиствовал в сети :))) потому как не смог выделить отдельно числа от слов :(
//        Pattern pattern = Pattern.compile("\\$([0-9]+\\.[0-9]+)");
//        Matcher matcher = pattern.matcher(totalPrice.getText());
//        matcher.find();
//        String extractedNumber = matcher.group(1);

        String[] splitted = totalPrice.getText().split("\\$");
        double actualPrice = splitted.length > 1 ? Double.parseDouble(splitted[1]) : 0;

//********
        double extractedNumber;
        //Assertions.assertTrue(41.02==actualPrice);
Assertions.assertEquals("41.02",splitted[1]);
        WebElement buttonFinish = driver.findElement(By.id("finish"));
        buttonFinish.click();

        WebElement elementText = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your order!')]"));
        Assertions.assertTrue(elementText.isEnabled());

        WebElement elementText2 = driver.findElement(By.xpath("//*[contains(text(),'Your order has been dispatched, and will arrive just as fast as the pony can get there!')]"));
        Assertions.assertTrue(elementText2.isEnabled());

        WebElement buttonBackHome = driver.findElement(By.id("back-to-products"));
        buttonBackHome.click();

        WebElement titleMainPage = driver.findElement(By.xpath("//title[contains(text(),'Swag Labs')]"));
        Assertions.assertTrue(titleMainPage.isEnabled());
    }
}
