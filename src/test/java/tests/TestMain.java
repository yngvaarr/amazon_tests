package tests;

import java.util.List;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMain {

    @Test
    public void test_items_cost_less_200() {
        String pathToDrivers = "D:\\Projects\\JavaProjects\\side_packages\\";
        System.setProperty("webdriver.gecko.driver", pathToDrivers + "geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        //System.setProperty("webdriver.chrome.driver", pathToDrivers + "chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();
        String writtenText = "phones";
        driver.get("https://amazon.com/");

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(writtenText);
        driver.findElement(By.className("nav-input")).click();
        driver.findElement(By.xpath("//*[@id='p_89/Apple']//a[@class='a-link-normal s-navigation-item']")).click();
        for(int i = 1; i < 5; i++) {
            driver.findElement(By.xpath("//*[@class='a-last']")).click();
        }

        List<WebElement> elements = driver.findElements(By.xpath("//*[@class='a-section a-spacing-medium']"));
        System.out.println("Total elements found: " +elements.size());
        for(int id = 0; id < elements.size(); id++) {
            String name;
            double cost;
            boolean isExtended = elements.get(id).findElements(By.xpath(".//*[@class='a-size-base a-link-normal s-no-hover a-text-normal']")).size() > 0;
            // Иногда могут попадаться позиции, у которых жирным выделен ценник
            if (isExtended) {
                name = elements.get(id).findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
                cost = Double.parseDouble(elements.get(id).findElement(By.xpath(".//span[@class='a-price-whole']")).getText().replace("$", ""));
            }
            else {
                name = elements.get(id).findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
                cost = Double.parseDouble(elements.get(id).findElement(By.xpath(".//span[@class='a-color-base']")).getText().replace("$", ""));
            }
            if (cost <= 200) {
                System.out.println(name + " : " + cost);
            }
        }
        //driver.quit();
    }
}

