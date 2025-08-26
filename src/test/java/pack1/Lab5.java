package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab5 {

    public static void main(String[] args) {

       
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
        driver.manage().window().maximize();
        System.out.println("Browser launched successfully and URL opened!");
        //i am taking here register account as test case 1
        try {
           

            // First Name
            WebElement firstName = driver.findElement(By.id("input-firstname"));
            firstName.sendKeys("John");

            //Last Name
            WebElement lastName = driver.findElement(By.id("input-lastname"));
            lastName.sendKeys("Doe");

            // Email 
            WebElement email = driver.findElement(By.id("input-email"));
            email.sendKeys("john.doe" + System.currentTimeMillis() + "@gmail.com");

            // Telephone
            WebElement telephone = driver.findElement(By.id("input-telephone"));
            telephone.sendKeys("9876543210");

            // Password
            WebElement password = driver.findElement(By.id("input-password"));
            password.sendKeys("Test@123");

            // Confirm Password
            WebElement confirmPassword = driver.findElement(By.id("input-confirm"));
            confirmPassword.sendKeys("Test@123");

            // Click Privacy Policy Checkbox
            WebElement privacyPolicy = driver.findElement(By.name("agree"));
            privacyPolicy.click();

            // Click Continue button
            WebElement continueBtn = driver.findElement(By.xpath("//input[@value='Continue']"));
            continueBtn.click();

            // Verify Registration Success
            WebElement successMsg = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']"));
            if (successMsg.isDisplayed()) {
                System.out.println("Test Case 1 Passed: Registration successful!");
            } else {
                System.out.println("Test Case 1 Failed: Registration failed!");
            }

          
          
          //Test case2

          
            driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

            // Enter blank First Name
            driver.findElement(By.id("input-firstname")).sendKeys("");
            driver.findElement(By.xpath("//input[@value='Continue']")).click();

            // Verify error message for minimum length
            WebElement minError = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"));
            if (minError.isDisplayed()) {
                System.out.println("Test Case 2 Passed: First Name minimum length validation working!");
            } else {
                System.out.println("Test Case 2 Failed: First Name minimum length validation failed!");
            }

          
          
           //Testcase3

            // Navigate back to registration page
            driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

            // Enter more than 32 characters in First Name
            driver.findElement(By.id("input-firstname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG");
            driver.findElement(By.xpath("//input[@value='Continue']")).click();

            // Verify error message for maximum length
            WebElement maxError = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"));
            if (maxError.isDisplayed()) {
                System.out.println("Test Case 3 Passed: First Name maximum length validation working!");
            } else {
                System.out.println("Test Case 3 Failed: First Name maximum length validation failed!");
            }

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

      
        driver.quit();
        System.out.println("Browser closed successfully!");
    }
}