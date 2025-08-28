package pack1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Lab9_TestNG {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(String browser) {
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
    }

    @Test(dataProvider = "dp")
    public void ecommerceTest(Integer testCaseId, String searchTerm) throws InterruptedException {
        driver.get("https://tutorialsninja.com/demo/index.php?");

        // Verify title
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Your Store"), "Page title doesn't contain expected text.");

        // Navigate to Desktops > Mac
        driver.findElement(By.xpath("//a[text()='Desktops']")).click();
        driver.findElement(By.xpath("//a[text()='Mac (1)']")).click();

        // Verify Mac heading is displayed
        WebElement macHeading = driver.findElement(By.xpath("//h2[text()='Mac']"));
        Assert.assertTrue(macHeading.isDisplayed(), "'Mac' heading is not displayed.");

        // Select 'Name (A - Z)' from sort dropdown
        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");

        // Click 'Add to Cart' for first item
        driver.findElement(By.xpath("(//button[contains(@onclick,'cart.add')])[1]")).click();

        // Search flow:
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        Thread.sleep(2000);

        // Clear and enable search in description
        searchBox = driver.findElement(By.name("search"));
        searchBox.clear();

        WebElement descCheckbox = driver.findElement(By.name("description"));
        if (!descCheckbox.isSelected()) {
            descCheckbox.click();
        }

        // Search again with same term
        searchBox.sendKeys(searchTerm);
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        Thread.sleep(2000);
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider
    public Object[][] dp() {
        return new Object[][] {
            { 1, "Mobile" },
            { 2, "Monitors" }
        };
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("This is after Class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("This is before Test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("This is after Test");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("This is before Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("This is after Suite");
    }
}