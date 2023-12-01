package Day_30_HW_Review;

import com.example.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WindowsManagingHomeworkTest extends BaseTest {
    @Test
    public void fourWindowsTest() {
        driver.get("https://selectorshub.com/xpath-practice-page/");
        String handleMein = driver.getWindowHandle();
        WebElement checkoutHereHover = driver.findElement(By.xpath("//div[@class='dropdown']/button"));
        actions.moveToElement(checkoutHereHover).perform();

        wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector(".dropdown > .dropdown-content > a")));

        driver.findElement(By.linkText("Join Training")).click();
        //wait.until(numberOfWindowsToBe(2));
        driver.findElement(By.linkText("Try TestCase Studio")).click();
       // wait.until(numberOfWindowsToBe(3));
        driver.findElement(By.linkText("Consider a small Donation and support this page.")).click();
        wait.until(numberOfWindowsToBe(4));


        //Не меняйте порядок ассертов!
        //Перейдите обратно на окно изначальной страницы, чтобы ассерт ниже прошёл

        driver.switchTo().window(handleMein);
        assertEquals("Click here to practice iframe and nested iframe scenarios.", driver.findElement(By.xpath("//h4[.='Memory Test']/..//a")).getText());

        //Перейдите на окно открытое по ссылке Join Training, чтобы ассерт ниже прошёл
        goToWindowByTitle("Bootcamp - SelectorsHub");
        assertEquals("SelectorsHub Technical Boot Camp", driver.findElement(By.tagName("h2")).getText());

        //Перейдите на окно открытое по ссылке Donate, чтобы ассерт ниже прошёл Donate - SelectorsHub
        goToWindowByTitle("Donate - SelectorsHub");
        assertEquals("Click to be a Patron", driver.findElements(By.cssSelector(".elementor-button-text")).get(4).getText());

        //Перейдите на окно открытое по ссылке Try TestCase Studio, чтобы ассерт ниже прошёл
        goToWindowByTitle("SelectorsHub- Free Productivity Booster Tools For Testers");
        assertEquals("Try our FREE recorder plugin 'TestCase Studio' to auto generate test steps & Screenshot", driver.findElement(By.xpath("//span[.='Try Now']/../../../../../..//h2")).getText());

        //Теперь проверяем что окон четыре
        assertEquals(4, driver.getWindowHandles().size());

        //Теперь закройте все окна кроме изначального, чтобы ассерты ниже прошли
        for (String p : driver.getWindowHandles()) {
            if (!p.equals(handleMein)) {
                driver.switchTo().window(p).close();
            }
        }
        driver.switchTo().window(handleMein);
        assertEquals(1, driver.getWindowHandles().size());
        assertEquals("Xpath Practice Page | Shadow dom, nested shadow dom, iframe, nested iframe and more complex automation scenarios.", driver.getTitle());

        //Запустите тест несколько раз (хотя бы 3) чтобы убедиться, что всё точно сделано правильно ;)
    }

    void goToWindowByTitle(String link) {
        for (String p : driver.getWindowHandles()) {
            if (driver.switchTo().window(p).getTitle().equals(link)) {
                break;
            }
        }
    }

}
