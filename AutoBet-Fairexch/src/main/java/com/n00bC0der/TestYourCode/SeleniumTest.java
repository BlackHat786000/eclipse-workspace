package com.n00bC0der.TestYourCode;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	public static void main(String[] args) throws InterruptedException {

		String URL = "https://geminiexch.com/#/fullmarket/202201016384624/2";

		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver.exe");
		OperaOptions options = new OperaOptions();
		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
		WebDriver driver;
		driver = new OperaDriver(options);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
//				.pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);

		driver.navigate().to(URL);
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[4]/a"))).click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), "1.90");
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[3]/input"))).sendKeys("100");
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Bets']"))).click();

//		float exposure = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[2]/a/strong/span"))).getText());
//		System.out.println(exposure);
		
//		DecimalFormat df = new DecimalFormat("0.00");
		float f = 100/3;
		
		float punter_lay_amount = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[2]/a/strong/span"))).getText());
		System.out.println(Float.toString(punter_lay_amount));
		
		System.out.println(Float.toString((int) f));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[4]/a"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), "1.80");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[3]/input"))).sendKeys(Float.toString((int) f));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[4]/td/dl/dd[4]/button"))).click();

	}

}
