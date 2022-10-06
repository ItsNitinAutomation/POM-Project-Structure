package com.matrices.qa.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import com.matrices.qa.base.BaseClass;

public class HomePage extends BaseClass
{
	@FindBy(name="q")
	WebElement searchBox;
	
	@FindBy(name="btnK")
	WebElement searchBtn;	
	
	@FindBy(xpath="//a[@href=\"https://www.ilovepdf.com/word_to_pdf\"]")
	WebElement linkText;
	
	public HomePage() throws IOException
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	
	public void initialize()
	{
		BaseClass.initializeDriver();
	}
	
	Wait<WebDriver> wait;
	WebElement link;
	
	
	public String getTitle()
	{
		String title = driver.getTitle();
		return title;
	}
	
	
	public void searchContent()
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(searchBox).keyDown(Keys.SHIFT).sendKeys(pr.getProperty("searchtext")).keyUp(Keys.SHIFT).build().perform();
		//searchBox.sendKeys(pr.getProperty("searchtext"));
		searchBtn.sendKeys(Keys.ENTER);
	}
	
	
	public LandingPage gotoNewPage() throws InterruptedException, IOException
	{
		wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(3000))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		
		wait.until(new Function<WebDriver,WebElement>()
		{
			@Override
			public WebElement apply(WebDriver t) 
			{
				link = t.findElement(By.xpath("//a[@href=\"https://www.ilovepdf.com/word_to_pdf\"]"));
				return link;
			}			
		});
		
		//Thread.sleep(3000);
		link.click();
		
		return new LandingPage();
	}
}
