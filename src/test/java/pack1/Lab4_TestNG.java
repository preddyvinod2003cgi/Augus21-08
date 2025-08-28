package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

@Test
public class Lab4_TestNG {

    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
    	
    	 if (browser.equalsIgnoreCase("chrome")) {
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
         } else if (browser.equalsIgnoreCase("firefox")) {
             WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
         } else if (browser.equalsIgnoreCase("edge")) {
             WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
         } else {
             throw new IllegalArgumentException("Browser not supported: " + browser);
         }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
    }
    

    public void lab_4() {
        // Enter 'Mobile' and Search
        driver.findElement(By.name("search")).sendKeys("Mobile");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        // Verify Search page title
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Search"), "Search page title not found!");

        // Clear search box and search with product description
        By searchBox = By.name("search");
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys("Monitors");
        driver.findElement(By.name("sub_category")).click();  
        driver.findElement(By.id("button-search")).click();

        // Verify results
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Monitors"), "Search results not correct!");
    }

    @AfterMethod
    public void CloseBrowser() {
        driver.quit();
    }
}