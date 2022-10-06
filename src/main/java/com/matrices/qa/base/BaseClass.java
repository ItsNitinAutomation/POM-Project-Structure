package com.matrices.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	public static WebDriver driver;
	public static Properties pr;
	public static File file;
	public static FileInputStream fis;
	
	public BaseClass() throws IOException
	{
		file=new File("D:\\Personal\\Automation Programs\\Interview_Prepare\\src\\main\\java\\com\\matrices\\qa\\config\\config.properties");
		fis=new FileInputStream(file);
		pr=new Properties();
		pr.load(fis);
	}
	
	public static void initializeDriver()
	{
		String path="D:\\Personal\\Automation Programs\\Interview_Prepare\\Driver\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver", path);
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.navigate().to(pr.getProperty("url"));
	}
}
