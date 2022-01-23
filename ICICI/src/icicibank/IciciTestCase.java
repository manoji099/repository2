package icicibank;



import org.testng.annotations.Test;

import icicibank.FdCalculatorPage;
import icicibank.Home;
import icicibank.WrapperClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class IciciTestCase extends WrapperClass {
	WebDriver driver;
	
	FdCalculatorPage fdcalculator ;
	
	Home home;
	
	Properties prop = new Properties();
	FileInputStream iput = null;
 
  @BeforeClass
  @Parameters("browser")
  public void launchBrowser(String browser) throws InterruptedException, IOException {

		iput = new FileInputStream("config.properties");
		prop.load(iput);
		String a = prop.getProperty("driver");
	  
	  driver= launchBrowser(browser,"https://www.icicibank.com/calculators/fd-calculator.html");
  }
  
  
  
  @Test
  public void testCase() throws AWTException, InterruptedException {
	  fdcalculator = new  FdCalculatorPage(driver);
	  
	  home = new Home(driver);
	  //scrollDown();
	  
	  fdcalculator.selectTypeOfFixedDeposit();
	  //scrollDown();
	  
	  fdcalculator.amountOfDeposit();
	  
	  fdcalculator.clickAnywhere();
	  
	  String value= fdcalculator.getMaturityValue();
	  System.out.println("Maturity Value = "+value);
	  
	  
	  String interest=fdcalculator.getAggregateInterest();
	  System.out.println("Aggregate Interest = "+interest);
	  
	  
	  fdcalculator.selectTenure();
	  
	  fdcalculator.tenureDays();
	  Thread.sleep(3000);
	  
      fdcalculator.clickAnywhere();
	  
	  String value1= fdcalculator.getMaturityValue();
	  System.out.println("Maturity Value for 1000 days = "+value1);
	  
	  
	  String interest1=fdcalculator.getAggregateInterest();
	
	  System.out.println("Aggregate Interest for 1000 days = "+interest1);
	  
	  driver.switchTo().defaultContent();
	  //scrollUp();
	  ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	  home.logoClick();
	  driver.findElement(By.xpath("//*[@id=\"modal-close\"]")).click();
	  Thread.sleep(3000);
	  takeScreenShot();	  
	  
  }
  
  
  @AfterClass
  public void closeBrowser() {
        //close current browser
        driver.close();
  }



}

