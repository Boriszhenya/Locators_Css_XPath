package Day_29;

import com.example.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TablesHomeworkTest extends BaseTest {
    @Test
    public void tablesAssertionsTest() {
        driver.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
        //Предмет интереса на этой странице - Demo Webtable 2 (Dynamic Table)
        //Добавьте ассерт, что в таблице присутствуют только 4 строки зданий
        List<WebElement> tabl2 = driver.findElements(By.xpath("//table[@summary='Sample Table']/tbody/tr"));
        assertEquals(4, tabl2.size());

        //Добавьте ассерт, что в 6-й строке таблицы (последняя строка) есть только два столбца
        List<WebElement> tabl2Foot = driver.findElements(By.xpath("//table[@summary='Sample Table']/tfoot/tr/*[@style]"));
        assertEquals(2, tabl2Foot.size());

        //Выведите в консоль всю информацию о втором самом высоком здании в таблице (не привязывайтесь к порядку строк)

        List<WebElement> listSecond = driver.findElements(By.xpath("//*[text()='601m']/../../*[@style]"));
        List<WebElement> listHead = driver.findElements(By.xpath("//thead/tr/th"));
        for (int i = 0; i < listSecond.size(); i++) {
            System.out.println(listHead.get(i).getText() + " - " + listSecond.get(i).getText());
        }
    }

    @Test
    public void tablesSortingTest() {
        driver.get("https://demowf.aspnetawesome.com/#Grid-search-using-parent-binding");
        driver.findElement(By.id("ContentPlaceHolder1_OCountry-awed")).click();
        driver.findElement(By.xpath("//li[.='Westfall']")).click();

        //Напишите метод, который будет принимать на вход WebElement таблицу, String имя столбца и String значение
        //A возвращать первую соответствующую строку как WebElement
        //Если id не поменяются, то этот ассерт должен работать =)
        //assertEquals("1225", getFirstRow(tableElement, "Person", "Zazzles").findElement(By.tagName("td")).getText());


        WebElement tableElement = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_Grid1']"));

        assertEquals("557", getFirstRow(tableElement, "Person", "Sam").findElement(By.tagName("td")).getText());


    }

    private WebElement getFirstRow(WebElement tableElement, String nameColumn, String value) {

        WebElement tableHeader = tableElement.findElement(By.xpath("//td[@data-i='0']/.."));
        List<WebElement> listHead = tableHeader.findElements(By.tagName("td"));
        int position = -1;
        for (int i = 0; i < listHead.size(); i++) {
            if (listHead.get(i).getText().equals(nameColumn)) {
                position = i;
                break;
            }
        }

        String locator = String.format("//*[@id='ContentPlaceHolder1_Grid1']//tr/td[%s][text()='%s']/..", position + 1, value);
        System.out.println(locator);

        WebElement result = driver.findElement(By.xpath(locator));
        System.out.println(result.getText());
        System.out.println(result.findElement(By.xpath("/td")).getText());
        return result;
    }

}
