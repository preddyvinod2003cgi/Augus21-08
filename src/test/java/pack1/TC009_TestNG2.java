package pack1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TC009_TestNG2 {

    WebDriver driver;
    String projectpath = System.getProperty("user.dir");
    ExtentReports extent;
    ExtentSparkReporter spark;

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

       
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(projectpath + "\\AutomationReport.html");
        extent.attachReporter(spark);
    }

    @Test(dataProvider = "testdata")
    public void executeTest(String testCaseId, String steps, String url, String expectedResult) throws IOException {
        ExtentTest test = extent.createTest("Test Case: " + testCaseId);
        System.out.println("Executing Test Case: " + testCaseId);

        try {
           
            driver.get(url);
            test.info("Launched URL: " + url);

            if (testCaseId.equalsIgnoreCase("TC_regr_core_003")) {
               
                String email = extractDataFromSteps(steps, "email:\\s*([^\\s]+)");
                String password = extractDataFromSteps(steps, "password\\s*:\\s*([^\\s]+)");

               
                WebElement loginLink = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
                loginLink.click();
                test.info("Clicked on Signup/Login link");

                
                WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
                emailField.sendKeys(email);
                test.pass("Entered email: " + email);

               
                WebElement passwordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
                passwordField.sendKeys(password);
                test.pass("Entered password");

               
                WebElement loginButton = driver.findElement(By.xpath("//button[@data-qa='login-button']"));
                loginButton.click();
                test.pass("Clicked login button");

              
                WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
                logoutButton.click();
                test.info("Clicked logout button");

               
                if (driver.getCurrentUrl().equalsIgnoreCase("https://www.automationexercise.com/")) {
                    test.pass("Logout successful and redirected to homepage");
                } else {
                    test.fail("Logout failed. Current URL: " + driver.getCurrentUrl());
                }
            }

            else if (testCaseId.equalsIgnoreCase("TC_regr_core_004")) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

               
                driver.navigate().to("https://automationexercise.com/products");
                test.info("Navigated to Products page");

               
                String[] categories = {"Women", "Men", "Kids"};

                for (String category : categories) {
                    try {
                        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[@id='accordian']//a[contains(text(),'" + category + "')]")
                        ));
                        dropdown.click();
                        test.info("Clicked on " + category + " dropdown");

                        WebElement firstSubCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[@id='" + category + "']//ul/li/a")
                        ));

                        String subCategoryName = firstSubCategory.getText();
                        firstSubCategory.click();
                        test.pass("Clicked on " + category + " → " + subCategoryName);

                      
                        if (driver.getCurrentUrl().contains("category_products")) {
                            test.pass("Successfully navigated to " + category + " → " + subCategoryName + " products page");
                        } else {
                            test.fail("Failed to navigate for " + category + " dropdown. Current URL: " + driver.getCurrentUrl());
                        }

                       
                        driver.navigate().back();
                        test.info("Returned to Products page");

                    } catch (Exception e) {
                        test.fail("Failed to handle dropdown for " + category + " due to: " + e.getMessage());
                    }
                }
            }

            else {
                test.skip("No automation implemented for Test Case ID: " + testCaseId);
            }

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
        extent.flush();
    }

    @DataProvider
    public String[][] testdata() throws IOException {
        File file1 = new File(projectpath + "\\Testcases.xlsx");
        FileInputStream fs = new FileInputStream(file1);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet worksheet = workbook.getSheetAt(0);

        int rowcount = worksheet.getPhysicalNumberOfRows();
        String[][] data = new String[rowcount - 1][4];

        for (int i = 1; i < rowcount; i++) {
            data[i - 1][0] = worksheet.getRow(i).getCell(1).getStringCellValue();
            data[i - 1][1] = worksheet.getRow(i).getCell(4).getStringCellValue();
            data[i - 1][2] = worksheet.getRow(i).getCell(5).getStringCellValue()
                    .split("URL\\s*:\\s*")[1]
                    .trim();
            data[i - 1][3] = worksheet.getRow(i).getCell(6).getStringCellValue();
        }

        workbook.close();
        fs.close();
        return data;
    }

    private String extractDataFromSteps(String steps, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(steps);
        return matcher.find() ? matcher.group(1).trim() : "";
    }
}
