package Day_30_HW_Review;

import com.example.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.tagName;

public class windowsManagingHomeworkTestZola extends BaseTest {
    @Test
    public void fourWindowsTest() {
        driver.get("https://selectorshub.com/xpath-practice-page/");
        WebElement checkoutHereHover = driver.findElement(By.xpath("//div[@class='dropdown']/button"));
        String defaultWindow = driver.getWindowHandle();
        actions.moveToElement(checkoutHereHover).perform();

        driver.findElement(By.linkText("Join Training")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        String windowHandleTraining = driver.getWindowHandle();

        driver.switchTo().window(defaultWindow);
        actions.moveToElement(checkoutHereHover).perform();
        driver.findElement(By.linkText("Try TestCase Studio")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        String windowHandleTestCase = driver.getWindowHandle();

        driver.switchTo().window(defaultWindow);
        actions.moveToElement(checkoutHereHover).perform();
        driver.findElement(By.linkText("Consider a small Donation and support this page.")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(4));
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        String windowHandleDonation = driver.getWindowHandle();

        //Не меняйте порядок ассертов!
        //Перейдите обратно на окно изначальной страницы, чтобы ассерт ниже прошёл
        driver.switchTo().window(defaultWindow);
        assertEquals("Click here to practice iframe and nested iframe scenarios.", driver.findElement(By.xpath("//h4[.='Memory Test']/..//a")).getText());

        //Перейдите на окно открытое по ссылке Join Training, чтобы ассерт ниже прошёл
        driver.switchTo().window(windowHandleTraining);
        assertEquals("SelectorsHub Technical Boot Camp", driver.findElement(tagName("h2")).getText());

        //Перейдите на окно открытое по ссылке Donate, чтобы ассерт ниже прошёл
        driver.switchTo().window(windowHandleDonation);
        assertEquals("Click to be a Patron", driver.findElements(By.cssSelector(".elementor-button-text")).get(4).getText());

        //Перейдите на окно открытое по ссылке Try TestCase Studio, чтобы ассерт ниже прошёл
        driver.switchTo().window(windowHandleTestCase);
        assertEquals("Try our FREE recorder plugin 'TestCase Studio' to auto generate test steps & Screenshot", driver.findElement(By.xpath("//span[.='Try Now']/../../../../../..//h2")).getText());

        //Теперь проверяем что окон четыре
        assertEquals(4, driver.getWindowHandles().size());

        //Теперь закройте все окна кроме изначального, чтобы ассерты ниже прошли
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(defaultWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        assertEquals(1, driver.getWindowHandles().size());

        driver.switchTo().window(defaultWindow);
        assertEquals(
                "Xpath Practice Page | Shadow dom, nested shadow dom, iframe, nested iframe and more complex automation scenarios.",
                driver.getTitle()
        );
        //Запустите тест несколько раз (хотя бы 3) чтобы убедиться, что всё точно сделано правильно ;)
    }
}