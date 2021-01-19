package DashBoard;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Data.Xls_Reader;

import org.openqa.selenium.support.ui.Select;
import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;

public class DasboardItems {

	 static boolean h;
	static int attempts ;
	WebElement uploadElement;
     String  actualResult;
	String expectedResult;
	org.openqa.selenium.chrome.ChromeDriver driver;
	
@BeforeTest
	
public void loginNsetup() throws MalformedURLException, InterruptedException 
	{
	Xls_Reader reader=new  Xls_Reader("./Educian/src/main/java/Data/login.xls");
	
 
	String username=reader.getCellData("login", 0, 2);
	 System.out.println(username);
		expectedResult="Dashboard";

		System.setProperty("webdriver.chrome.driver","C:\\Users\\MinzaMushtaq3\\Downloads\\chromedriver.exe");
		 driver =  new org.openqa.selenium.chrome.ChromeDriver();


		driver.navigate().to("https://test.educian.com/");

		driver.findElement(By.cssSelector("#inputEmail")).sendKeys("qaeducian@gmail.com");
		driver.findElement(By.cssSelector("#inputPassword")).sendKeys("qa@1234");

		driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
		Thread.sleep(2000);



		try
		{  
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[4]/div[7]/button[2]")));
			actualResult= driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]")).getText();
			System.out.println(actualResult);

		}
		catch(Exception e)
		{
			System.out.println("the login failed");
		}
		Assert.assertEquals(actualResult, expectedResult,"The login failed");

	}
	
 @Test(priority=1)
public void searchStudent() throws InterruptedException
{
    String name="Ahsan Shafi";
	driver.findElement(By.cssSelector("#studentnav")).click();
	driver.findElement(By.cssSelector("#studentStudentList")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@placeholder='Name or Class or Roll No.']")).sendKeys(name);
	expectedResult=name;
	driver.findElement(By.cssSelector("#searchbtn > button")).click();
	actualResult= driver.findElement(By.xpath("//td[contains(text(),'Ahsan')]")).getText();
	System.out.println(actualResult);
	Assert.assertEquals(actualResult, expectedResult,"not able to search student");

}



public void uploadphoto() throws InterruptedException, IOException
{
	attempts=0;
	driver.findElement(By.cssSelector("div.ibox-content:nth-child(1) > a")).click();
	Thread.sleep(2000);

    uploadElement = driver.findElement(By.xpath("//label[normalize-space()='Upload Photo']"));
    uploadElement.click();
    while(attempts < 2) {
	   try {
	    	Runtime.getRuntime().exec("C:\\Users\\MinzaMushtaq3\\Desktop\\AutoIt\\file.exe");
	        break;
	 } catch(Exception e) {
	  }
	   attempts++;
	}
    
    driver.findElement(By.xpath("//label[normalize-space()='Take Photo']")).click();// take a picture for student photo
	
    
    ChromeOptions option=new ChromeOptions();
	option.addArguments("use-fake-ui--for-media-stream");
	Thread.sleep(5000);
	driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]")).click();

	
}

@Test(priority=2)
public void createstudent() throws InterruptedException, IOException
{
   attempts=0;
	 
	driver.findElement(By.cssSelector("div.ibox-content:nth-child(1) > a")).click();//add student button
	driver.findElement(By.cssSelector("#first_name")).sendKeys("Auto");// first name
	driver.findElement(By.cssSelector("#last_Name")).sendKeys("LastName");//Last Name
	driver.findElement(By.cssSelector("#dob")).sendKeys("12/11/2010");//dob
	
	    
	Select drpCategory = new Select(driver.findElement(By.cssSelector("#student_category")));//category
	Thread.sleep(2000);
	// drpCategory.selectByValue("5e563ca35e637630a0006316");//fg
		        
	Select drpClass = new Select(driver.findElement(By.cssSelector("#classname")));//class
	drpClass.selectByValue("5e43f5cf9642d70918000f69");//10th
    driver.findElement(By.cssSelector("#emailId")).sendKeys("ulzaman@lelafe.com");//email
	driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("98190081991888");//phone
	driver.findElement(By.cssSelector("#addressline1")).sendKeys("Rangereth, land one pro");//address 1
	driver.findElement(By.cssSelector("#city")).sendKeys("Jammu ");//city
	driver.findElement(By.cssSelector("#state")).sendKeys("jammu and kashmir");//state
	driver.findElement(By.cssSelector("#pin")).sendKeys("190001");//pin
	Select drpCountry = new Select(driver.findElement(By.cssSelector("#country")));//country
	drpCountry.selectByValue("United States");//usa
	driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();//next
	driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();//submit
	try
	{
	  h=driver.findElement(By.xpath("//span[@id='examinationTypeHelper']")).isDisplayed();
	
 	
	}
	catch(Exception e)
	{
		
	}

finally {
	Assert.assertTrue(h);
}
}
@Test(priority=3)

public void paginationStudentList()   throws InterruptedException, IOException {
	driver.findElement(By.cssSelector("#studentnav")).click();
	driver.findElement(By.cssSelector("#studentStudentList")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();//pagination
	
	/*driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();//click next
	driver.findElement(By.xpath("//a[contains(text(),'Previous')]")).click();//click previous*/
}
@Test(priority=5)
public void logout() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(By.xpath("//strong[contains(text(),'(ADMIN)')]")).click();//click Admin
	driver.findElement(By.xpath("//ul[contains(@class,'dropdown-menu animated"
			+ " fadeInRight m-t-xs')]//a[contains(@class,'logout')][normalize-space()='Log out']")).click();
}


@Test(priority=4)



public void advancedSearch() throws InterruptedException
{ 
	 expectedResult="Ahsan Shafi";
	driver.findElement(By.cssSelector("#studentnav")).click();
	driver.findElement(By.cssSelector("#studentStudentList")).click();
	driver.findElement(By.xpath("//button[normalize-space()='Advanced Search']")).click();
	driver.findElement(By.xpath("//input[@id='advsr_firstname']")).sendKeys("Ahsan");
	driver.findElement(By.xpath("//input[@id='advsr_admissionno']")).sendKeys("SIEI/100/2020");
	driver.findElement(By.xpath("//button[@id='advancsearchbtn']")).click(); 
	Thread.sleep(5000);
	actualResult =driver.findElement(By.xpath("//td[contains(text(),'Shafi')]")).getText();
    System.out.println(actualResult);
    Assert.assertEquals(actualResult, expectedResult,"not able to search student");
}
}