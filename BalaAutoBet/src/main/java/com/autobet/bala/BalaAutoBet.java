package com.autobet.bala;

import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BalaAutoBet {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver97U.exe");
	    ChromeOptions options = new ChromeOptions();

	    // Fixing 255 Error crashes
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");

	    // Options to trick bot detection
	    // Removing webdriver property
	    options.addArguments("--disable-blink-features=AutomationControlled");
	    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	    options.setExperimentalOption("useAutomationExtension", null);

	    // Changing the user agent / browser fingerprint
	    options.addArguments("window-size=1920,1080");
	    options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

	    // Other
	    options.addArguments("disable-infobars");
	    
	    options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Local\\Google\\Chrome\\User Data");
	    
	    WebDriver driver = new ChromeDriver(options);
	    
	    // Setting Navigator as undefined
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
		
		driver.get("https://balabets.com/login");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("User Name"))).sendKeys(Keys.chord(Keys.CONTROL, "a"),"Balademo01");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("Password"))).sendKeys(Keys.chord(Keys.CONTROL, "a"),"Ab123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
		System.out.println("\nLogged IN....\n");
		Thread.sleep(5000);
		
		driver.navigate().to("https://balabets.com/game-detail/31153763");
		
		String home_team_name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/span/b"))).getText();
		String away_team_name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[3]/div[2]/div[1]/span/b"))).getText(); 
		
		System.out.println("****Welcome To Punter's AutoBet - set book both sides at desired odds****");
		System.out.println("\nMonitoring "+home_team_name+" vs "+away_team_name+"\n");
		
		while(true) {
			String o = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[5]/span[1]"))).getText();
			System.out.println(o);
		}

	}

}
