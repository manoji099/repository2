package icicibank;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import icicibank.WrapperClass;

public class Home extends WrapperClass{
	
	WebDriver driver;
	
	
	By logo=By.tagName("img");
	
	public Home(WebDriver driver)
	{
		this.driver=driver;
		
	}

	public void logoClick()
	{
		clickOnElement(driver,logo);
	}
	
	
}
