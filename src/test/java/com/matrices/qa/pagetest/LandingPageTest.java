package com.matrices.qa.pagetest;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.matrices.qa.base.BaseClass;
import com.matrices.qa.pages.HomePage;
import com.matrices.qa.pages.LandingPage;

public class LandingPageTest extends BaseClass
{
	LandingPage lp;
	HomePage hp;
	
	Logger log=Logger.getLogger(LandingPageTest.class.getName());
	
	public LandingPageTest() throws IOException
	{
		super();
	}
	
	@BeforeTest
	public void initialize() throws IOException
	{
		log.info("*******************Initializing Driver*******************");
		BaseClass.initializeDriver();		
		hp=new HomePage();
	}
	
	@Test(priority=0)
	public void searchContentTest()
	{
		hp.searchContent();
	}
	
	
	@Test(priority=1)
	public void gotoNewPageTest() throws InterruptedException, IOException
	{
		lp=hp.gotoNewPage();
	}
	
	@Test(priority=2)
	public void validateLogoTest()
	{
		String isLogo = lp.validateTitle();
		Assert.assertEquals(isLogo,"Convert Word to PDF. Documents DOC to PDF");
	}
	
	@Test(priority=3)
	public void uploadFileTest() throws AWTException, InterruptedException
	{
		lp.uploadFile();
	}
	
	@Test(priority=4)
	public void downloadFileTest() throws InterruptedException
	{
		lp.downloadFile();
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(4000);
		driver.close();
	}
}
