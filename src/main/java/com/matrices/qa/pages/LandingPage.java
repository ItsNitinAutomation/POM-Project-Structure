package com.matrices.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.matrices.qa.base.BaseClass;

public class LandingPage extends BaseClass
{
	@FindBy(xpath="//img[@alt='iLovePDF']")
	WebElement logo;
	
	@FindBy(id="pickfiles")
	WebElement selectWordFileBtn;
	
	@FindBy(id="processTask")
	WebElement downloadBtn;
	
	Wait<WebDriver> wait;
	WebElement btn;
	
	WebDriverWait wt;
	HomePage hp;
	
	public LandingPage() throws IOException
	{
		super();
	}
	
	
	public void initialize() throws IOException
	{
		BaseClass.initializeDriver();
		hp=new HomePage();
	}
	

	public String validateTitle() 
	{
		return driver.getTitle();
	}
	

	public void uploadFile() throws AWTException, InterruptedException
	{
		String error="NA";
		
		wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
	
		WebElement uploadBtn = wait.until(new Function<WebDriver, WebElement>()
		{

			@Override
			public WebElement apply(WebDriver t) 
			{
				btn=t.findElement(By.id("pickfiles"));
				return btn;
			}		
			
		});
		
		uploadBtn.click();
		
		
		String file="C:\\Users\\nitin.maske\\Desktop\\Resume.docx";
		StringSelection scr=new StringSelection(file);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Clipboard cp=tk.getSystemClipboard();
		cp.setContents(scr,null);
		
		Robot rc=new Robot();
		
		Thread.sleep(2000);
		rc.keyPress(KeyEvent.VK_CONTROL);
		rc.keyPress(KeyEvent.VK_V);
		
		Thread.sleep(2000);
		rc.keyRelease(KeyEvent.VK_CONTROL);
		rc.keyRelease(KeyEvent.VK_V);
		
		Thread.sleep(2000);
		rc.keyPress(KeyEvent.VK_TAB);
		rc.keyRelease(KeyEvent.VK_TAB);
		
		Thread.sleep(2000);
		rc.keyPress(KeyEvent.VK_TAB);
		rc.keyRelease(KeyEvent.VK_TAB);
		
		Thread.sleep(2000);
		rc.keyPress(KeyEvent.VK_ENTER);
		rc.keyRelease(KeyEvent.VK_ENTER);
		
		try
		{
			error=driver.findElement(By.xpath("//div[contains(text(),'Upload error')]")).getText();		
			
		}catch(Exception e) {}
		
		if(!(error.equals("NA")))
		{
			System.out.println("Invalid file format");
		}
		
		else
		{
			System.out.println("File uploaded successfully");	
		}
	}
	
	public void downloadFile()
	{
		wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		
		WebElement btnclk=wait.until(new Function<WebDriver,WebElement>()
		{

			@Override
			public WebElement apply(WebDriver t) 
			{
				WebElement btn1 = t.findElement(By.id("processTask"));
				return btn1;
			}
			
			
		});
		
		btnclk.click();	
		WebElement resultTitle=driver.findElement(By.xpath("//h1[@class='title2' and contains(text(),'WORD file has been converted to PDF')]"));
		wt=new WebDriverWait(driver,Duration.ofSeconds(20));
		wt.until(ExpectedConditions.visibilityOf(resultTitle));		
		
	}
}
