//FindElements
package pack1;


import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class TC004 {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.myntra.com/");
		//driver.findElement(By.xpath("//button[text()='Continue shopping']")).click();
		List<WebElement> alllinks=driver.findElements(By.tagName("a"));
		System.out.println("Totle links:"+alllinks.size());
		for(WebElement link:alllinks)
		{
			System.out.println("The link is:"+link.getText());
		}
	}
 
}