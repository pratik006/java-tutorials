package com.barsha;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="username") WebElement username;
	@FindBy(id="password") WebElement password;
	@FindBy(css="select[name=loginType]") WebElement loginType;
	@FindBy(id="login") WebElement login;

	/*public HomePage doLoginSuccess(String usernameVal, String passwordVal, String userTypeVal) {
		username.sendKeys(usernameVal);
		password.sendKeys(passwordVal);
		new Select(loginType).selectByVisibleText(userTypeVal);
		login.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		return null;//initPage(HomePage.class);
	}*/
	
	public String getSussessMsg() {
		return driver.findElement(By.xpath("//*[@id=\"passwordSentMsg\"]")).getText();
	}

	public LoginPage doLoginFailure(String usernameVal, String passwordVal, String userTypeVal) {
		username.sendKeys(usernameVal);
		password.sendKeys(passwordVal);
		new Select(loginType).selectByVisibleText(userTypeVal);
		login.click();
       /* Alert alert = driver.switchTo().alert();		
        String alertMessage= driver.switchTo().alert().getText();	 
        System.out.println(alertMessage);	  		
       	alert.accept();		*/
      //  new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		return new LoginPage(driver);
	}
	

	@FindBy(css="select[name=loginAs]") WebElement loginAs;
	@FindBy(id="goButton") WebElement goButton;
	public LoginPage doForgotPassword() {
		driver.findElement(By.linkText("Forgot Password")).click();
		String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		 // switch to popup window
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.cssSelector("#tab1 > div > input")).click();
		driver.findElement(By.cssSelector("#userID")).sendKeys("P256013589");
		new Select(loginAs).selectByVisibleText("Pensioner");
		goButton.click();
		wait("#passwordSentMsg");
		driver.findElement(By.cssSelector("#popupContactClose")).click();

		// driver.switchTo().window(getMainWindowHandle);  // switch back to parent window
		//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		return new LoginPage(driver);
		}
	
	
	public LoginPage doForgotPasswordNegative() {
		
		driver.findElement(By.linkText("Forgot Password")).click();
		String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		 // switch to popup window
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.cssSelector("#tab1 > div > input")).click();
		goButton.click();
		//String x=dogetNegativeMsg();
		//System.out.println(x);
		/* Alert alert = driver.switchTo().alert();		
	        String alertMessage= driver.switchTo().alert().getText();	 
	        System.out.println(alertMessage);	  		
	       	alert.accept();	
		
		driver.findElement(By.cssSelector("#popupContactClose")).click();*/
		return new LoginPage(driver);
		
	}
	
	public String dogetNegativeMsg() {
		Alert alert = driver.switchTo().alert();		
        String alertMessage= driver.switchTo().alert().getText();	 
        System.out.println(alertMessage);	  		
       	alert.accept();	
		return alertMessage;
		
	}
	
public LoginPage doForgotPasswordNegativeSecond() {
		
		driver.findElement(By.linkText("Forgot Password")).click();
		String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		 // switch to popup window
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.cssSelector("#tab1 > div > input")).click();
		driver.findElement(By.cssSelector("#userID")).sendKeys("P256013589");
		goButton.click();
		return new LoginPage(driver);
		
	}

public LoginPage doForgotPasswordNegativeLast() {
	
	driver.findElement(By.linkText("Forgot Password")).click();
	String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	 // switch to popup window
	driver.switchTo().window(subWindowHandler);
	driver.findElement(By.cssSelector("#tab1 > div > input")).click();
	new Select(loginAs).selectByVisibleText("Pensioner");
	goButton.click();
	return new LoginPage(driver);
	}	

public LoginPage doRegistartionTest() {
	driver.findElement(By.cssSelector("#nav > li > a")).click();
	driver.findElement(By.cssSelector("#nav > li:nth-child(1) > ul > li:nth-child(1) > a")).click();
	return new LoginPage(driver);
}

public LoginPage doTestUserIdEmp() {
	driver.findElement(By.linkText("Know your User ID")).click();
	String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	 // switch to popup window
	driver.switchTo().window(subWindowHandler);
	driver.findElement(By.cssSelector("#ktabs > li.active > a")).click();
	driver.findElement(By.cssSelector("#popupContactCloseKnowId > img")).click();
	return new LoginPage(driver);
}
public String getUserIdEmp()
{
	return getTextByCss("#ktab1 > p");
}


public LoginPage doTestUserIdPens() {
	driver.findElement(By.linkText("Know your User ID")).click();
	String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	 // switch to popup window
	driver.switchTo().window(subWindowHandler);
	driver.findElement(By.xpath("//*[@id=\"ktabs\"]/li[2]/a")).click();
	driver.findElement(By.cssSelector("#popupContactCloseKnowId > img")).click();
	return new LoginPage(driver);
}
public String getUserIdPens()
{
	return getTextByCss("#ktab2 > p");
}

public LoginPage doTestUserIdDDO() {
	driver.findElement(By.linkText("Know your User ID")).click();
	String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	 // switch to popup window
	driver.switchTo().window(subWindowHandler);
	driver.findElement(By.xpath("//*[@id=\"ktabs\"]/li[3]/a")).click();
	driver.findElement(By.cssSelector("#popupContactCloseKnowId > img")).click();
	return new LoginPage(driver);
}
public String getUserIdDDO()
{
	return getTextByCss("#ktab3 > p");
}

public LoginPage doTestUserIdHOD() {
	driver.findElement(By.linkText("Know your User ID")).click();
	String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	 // switch to popup window
	driver.switchTo().window(subWindowHandler);
	driver.findElement(By.xpath("//*[@id=\"ktabs\"]/li[4]/a")).click();
	driver.findElement(By.cssSelector("#popupContactCloseKnowId > img")).click();
	return new LoginPage(driver);
}
public String getUserIdHOD()
{
	return getTextByCss("#ktab4 > p");
}

public LoginPage doTestComplaint() {
	driver.findElement(By.linkText("Any Issue/Complaint")).click();
	String getMainWindowHandle = driver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	 // switch to iframe window
	
	driver.switchTo().frame("complaintFrame");
	driver.findElement(By.cssSelector("input[name='personName']")).sendKeys("barsha");
	driver.findElement(By.cssSelector("input[name='emporPensionerId']")).sendKeys("1234");
	driver.findElement(By.id("emp")).click();
	
	

	
	
	return new LoginPage(driver);
}

public String getComplaintMsg()
{
	return getTextByCss("body > form > div > div:nth-child(2) > h1");
}
}
