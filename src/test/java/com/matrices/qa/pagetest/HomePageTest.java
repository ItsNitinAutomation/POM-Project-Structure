package com.matrices.qa.pagetest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.matrices.qa.base.BaseClass;
import com.matrices.qa.pages.HomePage;

public class HomePageTest extends BaseClass
{
	HomePage hp;
	Logger log=Logger.getLogger(HomePageTest.class.getName());
	
	public HomePageTest() throws IOException
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
	public void getTitleTest()
	{
		log.info("*******************Title Test of Home Page Started*******************");
		String title = hp.getTitle();
		Assert.assertEquals(title, "Google");
		log.info("*******************Title Test of Home Page Closed*******************");
	}
	
	@Test(priority=1)
	public void searchContentTest()
	{
		log.info("*******************Search Content Test of Home Page Started*******************");
		hp.searchContent();
		log.info("*******************Search Content Test of Home Page Closed*******************");
	}
	
	@Test(priority=2)
	public void gotoNewPageTest() throws InterruptedException, IOException
	{
		log.info("*******************New Page Test of Home Page Started*******************");
		hp.gotoNewPage();
		log.info("*******************New Page Test of Home Page Closed*******************");
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		log.info("*******************Closing Browser*******************");
		Thread.sleep(3000);
		driver.close();
	}
}
