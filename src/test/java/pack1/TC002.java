package pack1;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class TC002 {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com");
		System.out.println("title is:"+driver.getTitle());
		driver.navigate().to("https://www.yahoo.com");
		System.out.println("title is:"+driver.getTitle());
		driver.navigate().back();
		System.out.println("After back title is:"+driver.getTitle());
		driver.navigate().forward();
		System.out.println("After forward title is:"+driver.getTitle());
	}
 
}