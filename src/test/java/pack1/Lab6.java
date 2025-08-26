package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab6_ValidationInSelenium {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("vinod2003@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Vinod@123");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
		
		
		driver.findElement(By.linkText("Components")).click();
		driver.findElement(By.linkText("Monitors (2)")).click();
		
		driver.findElement(By.id("input-limit")).sendKeys("25");
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[1]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Specification")).click();
		
		WebElement specifyTab=driver.findElement(By.id("tab-specification"));
		if(specifyTab.isDisplayed()) {
			System.out.println("Specification details are displayed");
		}
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div[1]/button[1]")).click();
		Thread.sleep(3000);
		
		WebElement verifysuccess=driver.findElement(By.xpath("//*[@id=\"product-product\"]/div[1]"));
		if(verifysuccess.isDisplayed()) {
			System.out.println("message :"+verifysuccess.getText());
		}
		
		
		
		driver.findElement(By.name("search")).sendKeys("Mobile");
		
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		
		driver.findElement(By.xpath("//*[@id=\"description\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"button-search\"]")).click();
		
		driver.findElement(By.linkText("HTC Touch HD")).click();
		
		WebElement input=driver.findElement(By.xpath("//*[@id=\"input-quantity\"]"));
		input.clear();
		input.sendKeys("3");
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("button-cart")).click();
		
		
		
        WebElement cartMsg = driver.findElement(By.xpath("//*[@id=\"button-cart\"]"));
        
      
        String expectedMsg = "Success: You have added HTC Touch HD to your shopping cart!";

        if (cartMsg.getText().contains(expectedMsg)) {
            System.out.println("Successfully added to cart");
        }
        
        Thread.sleep(2000);
        
		driver.findElement(By.id("cart-total")).click();
		
		WebElement title = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr/td[2]/a"));
		
		String expectedTitle = "HTC Touch HD";
		
		if(title.getText().contains(expectedTitle)) {
			System.out.println("Name of the mobile is appearing");
		}
		else {
			System.out.println("Name of the mobile is not appearing");
		}
		
		driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong")).click();
		
		driver.findElement(By.linkText("Checkout")).click();
		
		driver.findElement(By.linkText("My Account")).click();
		
        driver.findElement(By.linkText("Logout")).click();
        
        WebElement verifyHeading = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        
        if(verifyHeading.getText().contains("Account Logout")) {
			System.out.println("Account is logged out successfully");
		}
        else {
        	System.out.println("Account is not logged out successfully");
        	
        }
        
        driver.findElement(By.linkText("Continue")).click();
        
        
        driver.quit();

		

		   
	}

}