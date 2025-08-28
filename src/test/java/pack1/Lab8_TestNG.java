package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Lab8_TestNG {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
        Reporter.log("Opened TutorialNinja Demo site", true);
    }

    @Test(priority = 1)
    public void desktopsMacTest() {
        // Verify Title
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Your Store"), "Title verification failed!");
        Reporter.log("Page title verified: " + actualTitle, true);

        // Click on Desktops → Mac (submenu)
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.partialLinkText("Mac")).click();  // FIXED: works even if text is "Mac (1)"
        Reporter.log("Navigated to Mac section", true);

        // Verify Mac heading
        WebElement macHeading = driver.findElement(By.xpath("//h2[contains(text(),'Mac')]"));
        Assert.assertTrue(macHeading.isDisplayed(), "Mac heading not displayed!");
        Reporter.log("Mac heading verified", true);

        // Select Sort by Name (A-Z)
        Select sortDropdown = new Select(driver.findElement(By.id("input-sort")));
        sortDropdown.selectByVisibleText("Name (A - Z)");
        Reporter.log("Selected sorting option: Name (A - Z)", true);

        // Add to Cart
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div[2]/div[2]/button[1]")).click();
        Reporter.log("Clicked Add to Cart button", true);

        // Verify Success Message
        WebElement successMsg = driver.findElement(By.cssSelector(".alert-success"));
        Assert.assertTrue(successMsg.getText().contains("Success"), "Add to Cart failed!");
        Reporter.log("Product successfully added to cart", true);
    }

    @Test(priority = 2)
    public void searchTest() {
        // Enter 'Mobile' and Search
        driver.findElement(By.name("search")).sendKeys("Mobile");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        Reporter.log("Searched for 'Mobile'", true);

        // Verify Search page title
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Search"), "Search page title not found!");
        Reporter.log("Search page loaded successfully", true);

        // Clear search box and search with product description
        By searchBox = By.name("search");
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys("Monitors");
        driver.findElement(By.name("sub_category")).click();  
        driver.findElement(By.id("button-search")).click();
        Reporter.log("Searched with 'Monitors' including descriptions", true);

        // Verify results
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Monitors"), "Search results not correct!");
        Reporter.log("Verified search results for 'Monitors'", true);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        Reporter.log("Browser closed", true);
    }
}