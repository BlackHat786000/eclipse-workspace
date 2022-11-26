package com.n00bC0der.code;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.email.durgesh.Email;

public class AutoBet_AWS {
	public static void main(String[] args) throws Exception {
		
		int notify = 0;
		int alert = 0;
		int monitor = 0;
		String favs_team_name;
		String dogs_team_name;
		float favs_lay_odds;
		
//		String match_url = "https://fairexch9.com/#/fullmarket/20218222440479/4";
//		String punter_lay_team = "Delhi Capitals";	//FAVORITES_TEAM i.e. the team you are betting against at
//		float punter_lay_odds = 1.50f;
//		//float rebook_lay_odds = 1.80f;
//		float punter_lay_amount = 5000;
		
System.out.println("**************** Welcome To Punter's AutoBet - set book both sides at desired odds ****************\n\n");
		
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Enter Match_URL : ");
		String match_url = sc.nextLine();
		System.out.print("Enter Punter_Lay_Team : ");
		String punter_lay_team = sc.nextLine();
		System.out.print("Enter Punter_Lay_Odds : ");
		float punter_lay_odds = Float.parseFloat(sc.nextLine());
		System.out.print("Enter Punter_Lay_Amount : ");
		float punter_lay_amount = Float.parseFloat(sc.nextLine());
		System.out.print("Enter Rebook_Lay_Odds : ");
		float rebook_lay_odds = Float.parseFloat(sc.nextLine());
		System.out.print("Enter Rebook_Lay_Amount : ");
		float rebook_lay_amount = Float.parseFloat(sc.nextLine());
		
		sc.close();
	
//		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver79.exe");
//		WebDriver driver = new OperaDriver();
//		OperaOptions options = new OperaOptions();
//		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
//		WebDriver driver = new OperaDriver(options);
		
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Local\\Google\\Chrome\\User Data");
//		options.addArguments("--headless");
//		WebDriver driver = new ChromeDriver(options);
		
		driver.navigate().to(match_url);
		driver.manage().window().maximize();
		
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[1]/input"))).sendKeys("colarohit");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[2]/input"))).sendKeys("Abcd1234");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/button"))).click();
		System.out.println("\nLogged IN....\n");
		
		String home_team_name = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/span[1]"))).getText();
		String away_team_name = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[1]/span[1]"))).getText(); 
		
		System.out.println("****Welcome To Punter's AutoBet - set book both sides at desired odds****");
		System.out.println("\nMonitoring "+home_team_name+" vs "+away_team_name+"\n");
		
		
		while(monitor<2) {
			try {
//			Thread.sleep(1000);
			String home_lay_odds_in_string = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a")))
					.getText().split("\n")[0];
			float home_lay_odds = Float.parseFloat(home_lay_odds_in_string);
			
			String home_back_odds_in_string = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[4]/a"))).getText().split("\n")[0]; 
			float home_back_odds = Float.parseFloat(home_back_odds_in_string);
			
			String away_lay_odds_in_string = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[5]/a")))
					.getText().split("\n")[0];
			float away_lay_odds = Float.parseFloat(away_lay_odds_in_string);
			
			String away_back_odds_in_string = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[4]/a"))).getText().split("\n")[0];;
			float away_back_odds = Float.parseFloat(away_back_odds_in_string);
			
			String initial_balance = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong"))).getText();
			
			if(away_back_odds * 100 < home_back_odds * 100) {
				favs_team_name = away_team_name;
				favs_lay_odds = away_lay_odds;
				dogs_team_name = home_team_name;
			} else {
				favs_team_name = home_team_name;
				favs_lay_odds = home_lay_odds;
				dogs_team_name = away_team_name;
			}
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/span[1]"))).click();
			
			if(favs_lay_odds * 100 <= punter_lay_odds * 100 && !home_back_odds_in_string.equals(away_back_odds_in_string)) {
				
				System.out.println("FAVORITES "+favs_team_name+" LAYING ODDS **"+favs_lay_odds+"** ARE NOW LESS THAN or equal to "+punter_lay_odds+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]");
	
				if(favs_team_name.equals(punter_lay_team)) {
					
					System.out.println("Now Attempting to LAY "+favs_team_name+" @ "+favs_lay_odds+" or below for amount : "+punter_lay_amount+" rs.");
					
					if(favs_team_name.equals(home_team_name)) {
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a"))).click();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_lay_odds));
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[3]/input"))).sendKeys(Float.toString(punter_lay_amount));
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Bets']"))).click();
					} else if(favs_team_name.equals(away_team_name)) {
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[5]/a"))).click();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_lay_odds));
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[3]/input"))).sendKeys(Float.toString(punter_lay_amount));
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[4]/td/dl/dd[4]/button"))).click();
					}	
				
					try {
				String toast_message = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='to-message toastr-message ng-star-inserted']"))).getText();
				System.out.println(toast_message);
				} catch(TimeoutException e) {
					System.out.println("BET PLACED");
					System.out.println("Initial Balance: "+initial_balance);
					String updated_balance = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong"))).getText();
					System.out.println("Updated Balance: "+updated_balance);
					if(initial_balance.equals(updated_balance))
						{System.out.println("balance still not updated, please check for your bet manually");}
					else {System.out.println("****YOUR BET HAS BEEN PLACED SUCCESSFULLY****");
//-------------------------------------------------------------------------------------------------------------------
					
					try {
						Email email = new Email("autobet786@gmail.com", "bhajjiterminator");
						email.setFrom("autobet786@gmail.com", "AutoBet Notification - "+(notify++));
						email.setSubject(home_team_name+" vs "+away_team_name);
						email.setContent("<h1>This is to inform you that your laying bet has been successfully placed on "+favs_team_name+" @ "+favs_lay_odds+" for amount "+punter_lay_amount+" Rs.</h1>", "text/html");
						email.addRecipient("yadavudit786@gmail.com");
						email.send();
					} catch (Exception e1) {
						System.out.println("Huh huh  _(-_-)  Something got really messed up in Email Sending Mechanism....");
					}
					
//-------------------------------------------------------------------------------------------------------------------
					monitor = monitor + 1;
					if(monitor<2) {
					System.out.println("\n\n****Firing reverse set book both sides mechanism now....****");
					punter_lay_team = dogs_team_name;
					punter_lay_odds = rebook_lay_odds;
					punter_lay_amount = rebook_lay_amount;
					//punter_lay_amount = punter_lay_amount * (favs_lay_odds - 1);
					System.out.println("\nTARGET : Team To Lay Now -> "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+"\n\n");
					} else {System.out.println("\n\n****Congrats !! Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+"****");
					System.out.println("AutoBet exiting now....");
					driver.quit();
					} }
				}
				}
				}
			 else if(favs_lay_odds * 100 > punter_lay_odds * 100 && !home_back_odds_in_string.equals(away_back_odds_in_string)) {
				System.out.println("FAVORITES "+favs_team_name+" LAYING ODDS **"+favs_lay_odds+"** ARE GREATER THAN "+punter_lay_odds+", AUTO BET IS WAITING...."+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]");
			} else if(home_back_odds_in_string.equals(away_back_odds_in_string)) {
				System.out.println("\nBOTH TEAMS ARE NOW AVAILABLE TO BACK @ "+home_back_odds+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]\n");
			} } catch (WebDriverException f) {
				System.out.println("\n\n\n\n****************************************EXCEPTION CAUGHT - REATTEMPTING EVERYTHING AGAIN****************************************\n\n\n\n");
//-------------------------------------------------------------------------------------------------------------------
				
				try {
					Email email = new Email("autobet786@gmail.com", "bhajjiterminator");
					email.setFrom("autobet786@gmail.com", "AutoBet Notification - Alert "+(alert++));
					email.setSubject(home_team_name+" vs "+away_team_name);
					email.setContent("<h1>Alert Notification - This is to inform you that something got messed up in AutoBet flow....</h1>", "text/html");
					email.addRecipient("yadavudit786@gmail.com");
					email.send();
				} catch (Exception ee) {
					System.out.println("Huh huh  (-_-)  Something got really messed up in Email Sending Mechanism....");
				}
				
//-------------------------------------------------------------------------------------------------------------------
				continue;
			} 
		} 
	}
}