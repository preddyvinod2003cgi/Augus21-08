package pack1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC003 {

    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Create a ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        // Open Google
        driver.get("https://www.google.com");

        // Print the page title
        System.out.println("Title is: " + driver.getTitle());

        // Print the current URL
        System.out.println("URL is: " + driver.getCurrentUrl());

        // Print the page source
        System.out.println("Page Source is: " + driver.getPageSource());

        // Close the browser
        driver.quit();
    }
}
