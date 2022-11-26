package com.n00bC0der.code;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class KP_AstroSportsReport {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		String URL = "https://www.birthastro.com/kp-astrology";
		
		String name = "Newcastle United vs Leeds United";
		
		String dates = "17";
		String months = "9";
		String years = "2021";
		
		int hours = 8;
		int minutes = 0;
		String meridians = "PM";
		
		String country = "United Kingdom";
		String place = "Newcastle";
		
		int till_hour = 8;
		int till_minute = 30;
		int interval = 5;
		
		int IST_hours = 0;
		int IST_minutes = 30;
		String IST_meridian = "AM";
		
		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver.exe");
		OperaOptions options = new OperaOptions();
		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
		WebDriver driver;
		driver = new OperaDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		System.out.println("\n\n****************************************** KP Report is being generated as per Moon Muhurata. This may take several minutes ******************************************");
		
		PrintStream o = new PrintStream(new File("C://Users//yadav//Desktop//KP_AstroSportsReports//" + name + ".txt"));
		PrintStream console = System.out;
		System.setOut(o);

		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Sports Contest Details :-");
		System.out.println("Name : " + name);
		System.out.println("Date : " + dates + "-" + months + "-" + years);
		System.out.println("Time : " + hours + ":" + minutes + " " + meridians);
		System.out.println("Place : " + place + ", " + country + "\n");
		
		JFrame frame = new JFrame();
		JProgressBar bar = new JProgressBar(0,100);
		bar.setValue(0);
		bar.setBounds(0,0,420,50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli", Font.BOLD, 25));
		bar.setForeground(Color.green);
		bar.setBackground(Color.red);
		frame.add(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
		
		int total_minutes = (till_hour * 60 + till_minute) - (hours * 60 + minutes);
		int no_of_loops;
		int one = 0;
		if(minutes == 0) {one = 1;}
		if(total_minutes % interval != 0) {
			no_of_loops = (total_minutes / interval) + 1 + one;
		} else {
			no_of_loops = total_minutes / interval + one;
		}
		
		double i_bar = 100.0 / no_of_loops;
		double increment_bar = 0;
//---------------------------------------------------------------------------------------------------------------------
		for( ; minutes <=59 ; minutes = minutes + interval) {
			
		try {
		
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		
		driver.findElement(By.id("h_name")).sendKeys(name);
		
		Select date = new Select(driver.findElement(By.id("h_date")));
		date.selectByValue(dates);
		Select month = new Select(driver.findElement(By.id("h_month")));
		month.selectByValue(months);
		Select year = new Select(driver.findElement(By.id("h_year")));
		year.selectByValue(years);
		
		Select hour = new Select(driver.findElement(By.id("h_hour")));
		hour.selectByValue(Integer.toString(hours));
		Select minute = new Select(driver.findElement(By.id("h_minute")));
		minute.selectByValue(Integer.toString(minutes));
		Select meridian = new Select(driver.findElement(By.id("h_meridian")));
		meridian.selectByValue(meridians);
		
		Select your_country = new Select(driver.findElement(By.id("YourCountry")));
		your_country.selectByVisibleText(country);
		
		driver.findElement(By.id("h_place")).sendKeys(Keys.chord(Keys.CONTROL, "a"), place);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div/fieldset/div/form/div[3]/div/span/span/div/span/div[1]"))).click();
		Thread.sleep(5000);
		
		driver.findElement(By.id("generateHoroscope")).click();
		Thread.sleep(5000);
		
		WebElement Element = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div[3]/div[2]/fieldset/table"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		
		String moon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div/div/div[3]/div[2]/fieldset/table/tbody/tr[3]"))).getText();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Moon Muhurata @ "+hours+":"+minutes+" "+meridians+" ("+IST_hours+":"+IST_minutes+" "+IST_meridian+" IST) :- ");
		System.out.println(moon);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div/div/div[3]/div[1]/ul/li[4]/a"))).click();
		
		System.out.println("\nKP Planet Significatior @ "+hours+":"+minutes+" "+meridians+" ("+IST_hours+":"+IST_minutes+" "+IST_meridian+" IST) :- ");
		System.out.println(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div/div/div[3]/div[2]/fieldset/table"))).getText());
		
		if(IST_minutes + interval >= 60) {
			IST_hours = IST_hours + 1;
			IST_minutes = ((IST_minutes + interval) - 60) - interval; 
		}
		IST_minutes = IST_minutes + interval;
		
		if(minutes + interval >= 60) {
			hours = hours + 1;
			minutes = ((minutes + interval) - 60) - interval; 
		}
		
		if(hours == till_hour && minutes >= till_minute) {
			System.setOut(console);
			System.out.println("\n\n\n\n\n\n\n\n******************************************** Your KP Report has been generated successfully. Thank You for your patience *********************************************");
			minutes = 1000;
		}
		increment_bar = increment_bar + i_bar;
		bar.setValue((int) increment_bar);
		} catch (WebDriverException e) {
			System.setOut(console);
			System.out.println("\n\n\n\n\n\n\n\n************************************************************* Exception Caught - Reattempting Everything Again *************************************************************");
			continue;
		}
		} bar.setString("Done! :)"); driver.close();
	}
}