package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
		
	public Base() {
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
			
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		ChromeOptions	option=new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(option);
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			
		}
	
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		System.out.println(prop.getProperty("url"));
		
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}
	
	
	
	
	
}
