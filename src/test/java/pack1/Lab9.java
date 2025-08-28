package pack1;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Lab9
{
    WebDriver driver;

    @Test
    public void lab8Flow() {
        System.out.println("Executing Lab8");

        driver.get("https://tutorialsninja.com/demo/index.php?");

        // Click Desktops > Mac
        driver.findElement(By.xpath("//a[text()='Desktops']")).click();
        driver.findElement(By.xpath("//a[text()='Mac (1)']")).click();

        // Select "Name (A - Z)" from sort dropdown
        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");

        // Click Add to Cart on first product
        driver.findElement(By.xpath("(//button[contains(@onclick,'cart.add')])[1]")).click();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Lab9 Before Method");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Lab9 After Method");
        if (driver != null) {
            driver.quit();
        }
    }
}