package icicibank;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperClass {
	WebDriver driver;
	static int COUNTER=0;
		public WebDriver launchBrowser(String browser,String URL) throws InterruptedException
		{
			if(browser.contains("chrome"))   // Check if parameter passed as 'chrome'
			{
				System.setProperty("webdriver.chrome.driver", "F:\\F-DRIVE\\workspace\\ICICI\\DRIVERS\\chromedriver.exe"); //set path to chromedriver.exe
				//creating object in Chromeoptions
				ChromeOptions option=new ChromeOptions();
	            //disable notification in chrome 
				option.addArguments("--disable-notifications");
	            //create chrome instance
				driver=new ChromeDriver(option);
			}
			else if(browser.contains("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver", ".\\DRIVERS\\geckodriver.exe"); //set path to geckodriver.exe
				FirefoxOptions options = new FirefoxOptions();   //creating object in FirefoxOptions
				options.setProfile(new FirefoxProfile());
				options.addPreference("dom.webnotifications.enabled", false); //disable notification in firefox 
				//create firefox instance
				driver = new FirefoxDriver(options);
				
			}
			
			// gets url
			driver.get(URL); 
			//it maximize size of browser
			driver.manage().window().maximize();
			//delete all cookies present on history of page
			driver.manage().deleteAllCookies();
			//set implicit wait in driver
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// set page load time
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.switchTo().frame("paymframe");
			
			
			

			return driver;
		}
		
		
		public void scrollUp() throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-500)");
			//((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("/html/body/div[1]/div[4]/a")).click();
			
		}
		
		
		// Reading data from ExcelSheet(integer)
		public static int readExcel(String filename,String Sheet,int r,int c)  
		{
			int s=0;
			try {
				File f=new File(filename);               // gets the filename
				FileInputStream fin= new FileInputStream(f);   // creating object for FileInputStream
				XSSFWorkbook wb=new XSSFWorkbook(fin);            // creating object for XSSFWorkbook
				XSSFSheet sh= wb.getSheet(Sheet); 				//creating object for getSheet
				XSSFRow row=sh.getRow(r);                      // gets row number and stored in row object
				XSSFCell cell =row.getCell(c);     				// gets cell number and stored in cell object					          
				s= (int) cell.getNumericCellValue();            // retrives data from cell
				} catch (FileNotFoundException e) {
			
					e.printStackTrace();
				} catch (IOException e) {
			
					e.printStackTrace();
				}
			return s;
		}
		
		
		// reading data from excel (String)
		public static String readExcelString(String filename,String Sheet,int r,int c)  
		{
			String s=null;
			try {
				File f=new File(filename);               // gets the filename
				FileInputStream fin= new FileInputStream(f);   // creating object for FileInputStream
				XSSFWorkbook wb=new XSSFWorkbook(fin);            // creating object for XSSFWorkbook
				XSSFSheet sh= wb.getSheet(Sheet); 				//creating object for getSheet
				XSSFRow row=sh.getRow(r);                      // gets row number and stored in row object
				XSSFCell cell =row.getCell(c);     				// gets cell number and stored in cell object					          
				s=cell.getStringCellValue() ;           // retrives data from cell
				} catch (FileNotFoundException e) {
			
					e.printStackTrace();
				} catch (IOException e) {
			
					e.printStackTrace();
				}
			return s;
		}
		
		
		
		
		
		     // getting the data from particular element using explicit waits
			public String getTextFromElement(WebDriver driver,By location) {
				String text = null;
				try{
					//set object for wait
					WebDriverWait wait =new WebDriverWait(driver,20);
					//wait for location element until given time
				    WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(location));
				    //get text value
				    text=element.getText();
				     }
				catch(Exception e){ 
					//print error message if web Element is not found
					System.out.println("Web Element is not found in Page and location of Web Element is :"+location);
					e.printStackTrace();
				}
				return text;
			}
			
			
			     // sending the data to particular element using explicit waits
			public void sendTextToElement(WebDriver driver,By location,int amount) {
				
				try{
					//set object for wait
					WebDriverWait wait =new WebDriverWait(driver,20);
					//wait for location element until given time
				    WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(location));
				    //send value to element
				    String amount1=String.valueOf(amount);
				    
				    element.sendKeys(amount1);
				    
				     }
				catch(Exception e){ 
					//print error message if web Element is not found
					System.out.println("Web Element is not found in Page and location of Web Element is :"+location);
					e.printStackTrace();
				}
				
			}
			
			
			//function for click able elements in page using explicit waits
			public void clickOnElement(WebDriver driver,By location) {
				try {
				   WebDriverWait wait=new WebDriverWait(driver,20);
				   //wait for click elements in web page 
				   WebElement element =wait.until(ExpectedConditions.elementToBeClickable(location));
				   //clicks on the element
				   element.click();
				}
				catch(Exception e) {
					//print error message if web Element is not found
					System.out.println("Web Element is not found in Page and location of Web Element is :"+location);
					e.printStackTrace();
				
				}
				
			}
		
		
		
	
		public void takeScreenShot() {                       // capturing Screenshot
			
			String path=".\\SCREENSHOT\\";              //  Setting File Path  to store the ScreenShots
			String filename=COUNTER+".png";
			
			//Convert web driver object to TakeScreenshot
			//Call getScreenshotAs method to create image file
			
			File f1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
			//image file  destination to save
			 File f2= new File(path+filename);
			 try {
				FileUtils.copyFile(f1, f2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.out.println("Could not take Screenhot");
			}   
			 COUNTER++;
		}
		
		
		
		
		
}

