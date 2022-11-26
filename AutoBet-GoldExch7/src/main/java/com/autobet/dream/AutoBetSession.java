package com.autobet.dream;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoBetSession {

	public static void main(String[] args) {
		
		int monitor = 0;
		String sname = null;
		String yes_achieved = "          ( Waiting.................... )";
		String not_achieved = "          ( Waiting.................... )";
		boolean Y = true;
		boolean N = true;
		boolean flag = false;
		
		System.out.println("\n********** Welcome To Punter's AutoBetSession v3.2 (GoldExch7) - set the session both sides as you want **********\n\n");
		
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Enter Match URL : ");
		String match_url = sc.nextLine();
		System.out.print("Enter Session ID : ");
		String sid = sc.nextLine().toLowerCase().replaceAll("\\s", "_").replaceAll("[()]", "");
		System.out.print("Enter Bet Mode [ Y / N / A ] : ");
		String mode = sc.nextLine();
		System.out.print("Enter Target YES : ");
		int yes_target = Integer.parseInt(sc.nextLine());
		System.out.print("Enter Target NOT : ");
		int not_target = Integer.parseInt(sc.nextLine());
		System.out.print("Enter Bet Amount : ");
		String amount = sc.nextLine();
		
		sc.close();
		
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://goldexch7.com");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		
		System.out.println("\nLogging in....\n");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_phone"))).sendKeys("Rahu04");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_password"))).sendKeys("4444");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("LinkButton1"))).click();
		System.out.println("\nLogged IN....\n");
		
		driver.get(match_url);
		
		while(monitor < 2) {
		
		try {
			
		flag = false;
		
		String session = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id(sid))).getText();
		
		String not = session.split("\n")[1];
		
		if(not.equals("Ball Running") || not.equals("SUSPENDED")) {
			System.out.println(not);
		} else {
			
			sname = session.split("\n")[0];
			String noto = session.split("\n")[2];
			String yes = session.split("\n")[3];
			String yeso = session.split("\n")[4];
			
			System.out.println("*********** "+sname+" ***********");
			System.out.println("CURRENT YES  ::  "+yes+"  @  "+yeso);
			System.out.println("CURRENT NOT  ::  "+not+"  @  "+noto);
			System.out.println("TARGET YES  ::  " + yes_target + " @ 100 " + yes_achieved);
			System.out.println("TARGET NOT  ::  " + not_target + " @ 100 " + not_achieved);
			
			int yes_runs = Integer.parseInt(yes);
			int not_runs = Integer.parseInt(not);
			int yes_odds = Integer.parseInt(yeso);
			int not_odds = Integer.parseInt(noto);
			flag = true;
			
			switch(mode) {
			
			case "Y" : if (yes_runs <= yes_target && yes_odds == 100) {
				System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("CURRENT YES = " + yes_runs + " IS NOW EQUAL TO // LESS THAN TARGET YES = " + yes_target);
				System.out.println("NOW ATTEMPTING TO PLACE YES BET ON " + yes_runs + " RUNS");
				
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fancyback"+sid)));
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("fancyback"+sid))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys(amount);
				wait.until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
				
				String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.className("jq-toast-single"))).getText();
					if(toast_message.contains("Bet Place Successfully")) {
					driver.navigate().refresh();
					System.out.println(toast_message);
					System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					++monitor;
					mode = "N";
					yes_achieved = "          ( DONE @ " + yes_runs + " RUNS , ODDS @ " + yes_odds + " )";
					} else {
						System.out.println(toast_message);
						System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					}
				} break;
			
			case "N" : if (not_runs >= not_target && not_odds == 100) {
				System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("CURRENT NOT = " + not_runs + " IS NOW EQUAL TO // MORE THAN TARGET NOT = " + not_target);
				System.out.println("NOW ATTEMPTING TO PLACE NOT BET ON " + not_runs + " RUNS");
				
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fancylay"+sid)));
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("fancylay"+sid))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys("100");
				wait.until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
				
				String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.className("jq-toast-single"))).getText();
					if(toast_message.contains("Bet Place Successfully")) {
					driver.navigate().refresh();
					System.out.println(toast_message);
					System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					++monitor;
					mode = "Y";
					not_achieved = "          ( DONE @ " + not_runs + " RUNS , ODDS @ " + not_odds + " )";
					} else {
						System.out.println(toast_message);
						System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					}
				} break;
			
			case "A" : if (yes_runs <= yes_target && Y && yes_odds == 100) {
				System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("CURRENT YES = " + yes_runs + " IS NOW EQUAL TO / LESS THAN TARGET YES = " + yes_target);
				System.out.println("NOW ATTEMPTING TO PLACE YES BET ON " + yes_runs + " RUNS");
				
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fancyback"+sid)));
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("fancyback"+sid))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys("100");
				wait.until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
				
				String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.className("jq-toast-single"))).getText();
					if(toast_message.contains("Bet Place Successfully")) {
					driver.navigate().refresh();
					System.out.println(toast_message);
					System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					++monitor;
					Y = false;
					yes_achieved = "          ( DONE @ " + yes_runs + " RUNS , ODDS @ " + yes_odds + " )";
					} else {
						System.out.println(toast_message);
						System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						
					}
					
				} else if (not_runs >= not_target && N && not_odds == 100) {
					System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("CURRENT NOT = " + not_runs + " IS NOW EQUAL TO / MORE THAN TARGET NOT = " + not_target);
					System.out.println("NOW ATTEMPTING TO PLACE NOT BET ON " + not_runs + " RUNS");
					
					WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fancylay"+sid)));
					wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
					wait.until(ExpectedConditions.elementToBeClickable(By.id("fancylay"+sid))).click();
					wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys("100");
					wait.until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
					
					String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.className("jq-toast-single"))).getText();
						if(toast_message.contains("Bet Place Successfully")) {
						driver.navigate().refresh();
						System.out.println(toast_message);
						System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						++monitor;
						N = false;
						not_achieved = "          ( DONE @ " + not_runs + " RUNS , ODDS @ " + not_odds + " )";
						} else {
							System.out.println(toast_message);
							System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						}
					}
			
			default : System.out.println("\nPlease enter valid value for Bet Mode"); monitor = 1000;
			
		} // switch
			
	} // else
		 
		 } catch(StaleElementReferenceException s) {
			 if(flag) {
				 driver.navigate().refresh();
				 System.out.println("\n********** stale element reference exception while attempting to bet ********** ----> << page will refresh now >>\n");
				 flag = false;
			 } else {
				 System.out.println("\n<< stale element reference exception while fetching session >> ----> << waiting for element to be available >>\n");	 
			 }
		 } catch(TimeoutException t) {
			 driver.navigate().refresh();
			 System.out.println("\n\n<< timeout exception >> ----> << Market for your session is not active now. Please use valid session id >>\n\n");
		 } catch(WebDriverException w) {
			 driver.navigate().refresh();	
			 System.out.println("\n<< web driver exception >> ----> << page will refresh now >>\n");
			 System.out.println("\n"+w.toString());
		 }
		
		
		
		} // while
		
		System.out.println("\n\nCongratulations !!  Your Session - "+sname+" has been set both sides");
		System.out.println("\nAutoBetSession v3.0 is going to exit now,  BYE BYE :)");
		driver.quit();
		
	} // main

} // class
