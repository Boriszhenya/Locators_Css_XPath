package Day_26_Browser_interactions;

import com.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class WindowsTest extends BaseTest {

    @Test
    void twoWindowsTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/windows");

        String titleFirstWindow = driver.getTitle();
        System.out.println("Заголовок первой страницы: " + titleFirstWindow);

        driver.findElement(By.cssSelector("#content > div > a")).click();
        Thread.sleep(2000);

        System.out.println("Заголовок страницы: " + driver.getTitle());
        String firstWindowsHandles = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String tab: windowHandles) {
            if (!tab.equals(firstWindowsHandles))
                driver.switchTo().window(tab);
        }
        System.out.println("Заголовок страницы: " + driver.getTitle());
    }

    @Test
    void multipleWindowsTest() {
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.cssSelector("#content > div > a")).click();
        driver.findElement(By.cssSelector("#content > div > a")).click();
        driver.findElement(By.cssSelector("#content > div > a")).click();
        driver.findElement(By.cssSelector("#content > div > a")).click();

        Set<String> windowsHandle = driver.getWindowHandles();

        for (String tab: windowsHandle) {
            driver.switchTo().window(tab);
            if(driver.getTitle().equals("New Window")){
                break;
            }
        }
        System.out.println(driver.getTitle());
    }

    @Test
    void newWindowTest(){
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://the-internet.herokuapp.com");
    }
}
