package pack1;
import org.testng.annotations.Test;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
 
public class TC009_TestNG {
	WebDriver driver;
  @Test(dataProvider="logindata")
  public void f(String username, String password) {
	  System.out.println("This is the test");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		WebElement uname=driver.findElement(By.name("username"));
		if(uname.isDisplayed())
		{
			uname.sendKeys(username);
		System.out.println("Get placeholder:"+uname.getAttribute("placeholder"));
		}
		else
		{
			System.out.println("username is not displayed");
		}
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("username")).sendKeys(Keys.ENTER);

  }

  @Parameters("browser")
  @BeforeMethod
  public void beforeMethod(String brow) {
	  System.out.println("This is Before Method");
	  if(brow.equalsIgnoreCase("chrome"))
	  {
	  WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
	  }
	  if(brow.equalsIgnoreCase("edge"))
	  {
	  WebDriverManager.edgedriver().setup();
		 driver=new EdgeDriver();
	  }
	  if(brow.equalsIgnoreCase("firefox"))
	  {
	  WebDriverManager.firefoxdriver().setup();
		 driver=new FirefoxDriver();
	  }
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }
 
  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is After Method");
	  driver.quit();
  }
 
 
  @DataProvider
  public Object[][] logindata() {
    return new Object[][] {
      new Object[] { "Admin", "admin123" },
      new Object[] { "vinod", "welcome" },
    };
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
  }
 
  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is After Suite");
  }
 
}