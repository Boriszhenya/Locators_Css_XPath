package Day_29;

import com.example.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TablesTest extends BaseTest {
    @Test
    public void getHeadersTest() { // - 1
        driver.get("https://the-internet.herokuapp.com/tables");
        // Вывести в консоль все хедеры и их индексы
        List<WebElement> headersList = driver.findElements(By.cssSelector("#table1 th"));
        for (int i = 0; i < headersList.size(); i++) {
            System.out.println("Индекс-" + i + " " + headersList.get(i).getText());
        }
    }

    @Test
    public void getDueByFirstName() { // - 2
        driver.get("https://the-internet.herokuapp.com/tables");
        // Написать метод, чтобы получить из таблицы значение Due по имени, например для Frank должно быть 51
        assertEquals("$51.00", getDueByFirstName("Frank"));
        assertEquals("$100.00", getDueByFirstName("Jason"));
    }

    //в методже пример как можно сборать локатор
    private String getDueByFirstName(String name) {
        String locator = String.format("//*[@id='table1']//td[text()='%s']/../td[4]", name);
        return driver.findElement(By.xpath(locator)).getText();
    }

    @Test
    public void getRowIndexByLastName() { // - 3
        driver.get("https://the-internet.herokuapp.com/tables");
        getRowIndexByLastName("Bach");
        // Написать метод, чтобы получить индекс строки по значению Last Name
        assertEquals(1, getRowIndexByLastName("Bach"));
        assertEquals(3, getRowIndexByLastName("Conway"));
        assertEquals(-1, getRowIndexByLastName("Buffalo"));
    }

    private int getRowIndexByLastName(String name) {
        List<WebElement> list = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr"));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).findElement(By.tagName("td")).getText().equals(name))
                return i;
        }
        return -1;
    }

    @Test
    public void getRowBySomeValue() { // - 4
        driver.get("https://demo.aspnetawesome.com/GridFilterRowServerSideData#Grid-filter-row-server-data");
        driver.findElement(By.id("GridFrow2PageSize-awed")).click();
        driver.findElement(By.xpath("//li[.='100']")).click();
        // Найти первую строку со значением Ghidrimesti в столбце country
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tr"),99));
        WebElement neededRow = driver.findElement(By.xpath("//td[text()='Ghidrimesti']/.."));
        System.out.println(neededRow.getText());
        assertTrue(neededRow.getText().contains("Banana, Walnuts, Almonds"));
        // И записать все её клетки в переменную ниже
        List<WebElement> neededRowCells =neededRow.findElements(By.tagName("td"));
        //System.out.println(neededRowCells.size());
        assertEquals("2027", neededRowCells.get(0).getText());
        assertEquals("Miguel", neededRowCells.get(1).getText());
        assertEquals("Banana", neededRowCells.get(2).getText());
        assertEquals("100", neededRowCells.get(3).getText());
    }
}
