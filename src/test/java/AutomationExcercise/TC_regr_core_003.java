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

public class TC_regr_core_003 {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    ExtentReports extent;
    ExtentTest test;
    ExtentSparkReporter spark;
    WebDriverWait wait;

    @Test
    public void verifyLogoutFunctionality() {
        test = extent.createTest("Verify Logout Functionality");

        try {
           
            driver.get("https://automationexercise.com/");
            test.info("Opened the website successfully");

            
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            test.info("Clicked on Login/Signup link");

            
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
            WebElement passwordInput = driver.findElement(By.xpath("//input[@data-qa='login-password']"));

           
            emailInput.sendKeys("peddamallu@gmail.com");
            passwordInput.sendKeys("Peddamallu@25");

          
            driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
            test.info("Submitted login form");

           
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logout')]")));

           
            boolean isLoggedIn = driver.findElements(By.xpath("//a[contains(text(),'Logout')]")).size() > 0;
            Assert.assertTrue(isLoggedIn, "Login failed - Logout button not found!");
            test.pass("Login successful");

            
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
            test.info("Clicked on Logout button");

            
            wait.until(ExpectedConditions.urlContains("/login"));

           
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://automationexercise.com/login", "Logout failed - Not redirected to login page");
            test.pass("Logout successful, redirected to login page");

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
        spark = new ExtentSparkReporter(projectPath + "/test-output/ExtentReport_Logout.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("This is After Suite");
        extent.flush();
    }
}
