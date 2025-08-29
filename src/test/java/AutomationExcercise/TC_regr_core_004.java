package AutomationExcercise;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TC_regr_core_004 {

    WebDriver driver;
    WebDriverWait wait;
    String projectPath = System.getProperty("user.dir");

    ExtentReports extent;
    ExtentTest test;
    ExtentSparkReporter spark;

    @Test
    public void verifyDropdownButtons() throws InterruptedException {
        test = extent.createTest("Verify Dropdown Buttons on Product Page");

        try {
          
            driver.get("https://www.automationexercise.com/");
            test.info("Opened the Automation Exercise website successfully");

           
            WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']")));
            productsLink.click();
            test.info("Navigated to the Products page");

           
            Thread.sleep(3000);

          
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement categorySection = driver.findElement(By.xpath("//div[@class='left-sidebar']"));
            js.executeScript("arguments[0].scrollIntoView(true);", categorySection);
            test.info("Scrolled to the Category section");

           
            WebElement womenPlusIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='panel-heading']//a[@href='#Women']/span/i")));
            womenPlusIcon.click();
            test.info("Clicked on + icon under Women to expand subcategories");
            Thread.sleep(1500);

           
            boolean womenSubCategoriesVisible = driver.findElements(By.xpath("//div[@id='Women']//ul")).size() > 0;
            Assert.assertTrue(womenSubCategoriesVisible, "Women subcategories are not visible after clicking + icon!");
            test.pass("Women subcategories expanded successfully");

            WebElement menPlusIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='panel-heading']//a[@href='#Men']/span/i")));
            menPlusIcon.click();
            test.info("Clicked on + icon under Men to expand subcategories");
            Thread.sleep(1500);

            boolean menSubCategoriesVisible = driver.findElements(By.xpath("//div[@id='Men']//ul")).size() > 0;
            Assert.assertTrue(menSubCategoriesVisible, "Men subcategories are not visible after clicking + icon!");
            test.pass("Men subcategories expanded successfully");

           
            WebElement kidsPlusIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='panel-heading']//a[@href='#Kids']/span/i")));
            kidsPlusIcon.click();
            test.info("Clicked on + icon under Kids to expand subcategories");
            Thread.sleep(1500);

            boolean kidsSubCategoriesVisible = driver.findElements(By.xpath("//div[@id='Kids']//ul")).size() > 0;
            Assert.assertTrue(kidsSubCategoriesVisible, "Kids subcategories are not visible after clicking + icon!");
            test.pass("Kids subcategories expanded successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }

    
    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String brow) {
        System.out.println("This is Before Method");

        if (brow.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (brow.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (brow.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is After Method");
        driver.quit();
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is Before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("This is After Class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("This is Before Test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("This is After Test");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("This is Before Suite");
        spark = new ExtentSparkReporter(projectPath + "/test-output/ExtentReport_Dropdowns.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("This is After Suite");
        extent.flush();
    }
}
