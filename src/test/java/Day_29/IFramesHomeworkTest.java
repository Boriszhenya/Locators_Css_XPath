package Day_29;

import com.example.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IFramesHomeworkTest extends BaseTest {
    @Test
    public void nestedFramesTest() {// - 1
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top").switchTo().frame("frame-middle");
        WebElement middleContent = driver.findElement(By.id("content"));
        assertEquals("MIDDLE", middleContent.getText());
        //driver.switchTo().defaultContent(); //перемещение назад
        driver.switchTo().parentFrame().switchTo().parentFrame(); //еще один вариант перемещение назад

        assertEquals("50%,50%", driver.findElement(By.xpath("//frameset")).getAttribute("rows"));
    }

    @Test
    public void elementsInFrames() throws InterruptedException {// - 2
        driver.get("https://selectorshub.com/iframe-scenario/");
        driver.switchTo().frame("pact1").switchTo().frame("pact2");
        WebElement currentCrushNameInput = driver.findElement(By.xpath("//input[@id='jex']"));
        assertEquals("Current Crush Name", currentCrushNameInput.getAttribute("placeholder"));

        driver.switchTo().frame("pact3");
        WebElement closeItButton = driver.findElement(By.xpath("//button[@id='close']"));
        closeItButton.click();
        Thread.sleep(275);
        assertEquals(Color.fromString("#c36"), Color.fromString(closeItButton.getCssValue("background-color")));
              ///Не совпадает цвет :( ??? я уже и нажимал на кнопку, тогда цвет совпадает но не проходит прозрачность :(
    }

//    @Test
//    public void shadowDomElement() {// - 3
//        driver.get("https://selectorshub.com/shadow-dom-in-iframe/");
//        driver.switchTo().frame("pact");
//
//        // Используем JavaScript Executor, чтобы получить shadow root
//        WebElement shadowHost = driver.findElement(By.id("snacktime"));
//         js.executeScript("return arguments[0].s", shadowHost);
//
//        // Теперь ищем элемент внутри shadow DOM
//        WebElement elementInShadowDOM = shadowRoot.findElement(By.id("college"));
//        elementInShadowDOM.click();
//
//        WebElement firstCollegeInput = shadowRoot.findElement(By.id("college"));
//        assertEquals("First College", firstCollegeInput.getAttribute("placeholder"));
//    }
}
