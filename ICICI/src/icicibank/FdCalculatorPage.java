package icicibank;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import icicibank.WrapperClass;

public class FdCalculatorPage extends WrapperClass{

	WebDriver driver;
	
	
	By typeofdeposit=By.xpath("//select[@id='ddlTypeOfFixedDeposit']");
	
	By loanamount=By.xpath("//form//input[@id='loanAmount']");
	
	By tenure=By.xpath("//input[@id='idDays']");
	
	By maturityvalue=By.xpath("//span[@id='spnMaturityValue']");
	
    By aggregateinterest=By.xpath("//span[@id='spnAIAmount']");
    
    By tenuredays=By.xpath("//input[@id='tenureDay']");
    
	
	public FdCalculatorPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void selectTypeOfFixedDeposit()
	{
		String deposit=readExcelString(".\\TESTDATA\\ICICI_data.xlsx","Sheet1",1,0);
		
		WebElement wb=driver.findElement(By.xpath("//select[@id='ddlTypeOfFixedDeposit']"));   // select the select webElement
		Select DD=new Select(wb);
		DD.selectByVisibleText(deposit);     
		
		
	}
	
	public void amountOfDeposit()
	{
		int amount=readExcel(".\\TESTDATA\\ICICI_data.xlsx","Sheet1",1,1);
		
		driver.findElement(By.xpath("//form//input[@id='loanAmount']")).clear();
		
		sendTextToElement(driver,loanamount,amount);
	}
	
	public String getMaturityValue()
	{
		String mvalue=getTextFromElement(driver,maturityvalue);
		return mvalue;
	}
	
	
	public String getAggregateInterest()
	{
		String interest=getTextFromElement(driver,aggregateinterest);
		return interest;
	}
	
	
	public void selectTenure()
	{
		clickOnElement(driver,tenure);
	}
	
	
	
	public void tenureDays()
	
	{
		driver.findElement(By.xpath("//input[@id='tenureDay']")).clear();
		
		sendTextToElement(driver,tenuredays,1000);
	}
	
	public void clickAnywhere() throws AWTException
	{
		 Actions actions = new Actions(driver);

		  Robot robot = new Robot();

		  robot.mouseMove(50,50);

		  actions.click().build().perform();
	}
	
	
	
	
}
