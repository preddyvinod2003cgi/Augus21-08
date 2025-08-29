package pack1;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
WebDriver driver;

   public LoginPage(WebDriver driver) {
	   this.driver=driver;
   }


	By uname=By.name("username");
	By pword=By.name("password");
	By submit=By.xpath("//button[@type='submit']");
	By Dashbord=By.xpath("//h6[text()='Dashboard']");
	
	
	public void enterusername(String username)
	{
		driver.findElement(uname).sendKeys(username);
	}
	
	public boolean usernameisdisplayed()
	{
		return driver.findElement(uname).isDisplayed();
	}
	
	public boolean dashisdisplayed()
	{
		return driver.findElement(uname).isDisplayed();
	}
	
	public @Nullable String unamegetattributrValue()
	{
		return driver.findElement(uname).getAttribute("placeholder");
	}
	
	public void enterpassword(String password)
	{
		driver.findElement(pword).sendKeys(password);
	}
	
	
	public void clickbutton()
	{
		driver.findElement(submit).click();
	}

	public String unamegetattributevalue() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clickonbutton() {
		// TODO Auto-generated method stub
		
	}
	
}
