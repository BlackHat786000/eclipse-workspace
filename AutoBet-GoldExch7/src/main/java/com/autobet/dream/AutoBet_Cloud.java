package com.autobet.dream;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.email.durgesh.Email;

public class AutoBet_Cloud {

	public static void main(String[] args) throws InterruptedException {
		
		int notify = 0;
		int alert = 0;
		int monitor = 0;
		String favs_team_name;
		String dogs_team_name;
		float favs_lay_odds;
		
		System.out.println("**************** Welcome To Punter's AutoBet - set book both sides at desired odds ****************\n\n");
		
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Enter Match URL : ");
		String match_url = sc.nextLine();
		System.out.print("Enter Punter Lay Team : ");
		String punter_lay_team = sc.nextLine();
		System.out.print("Enter Punter Lay Odds : ");
		float punter_lay_odds = Float.parseFloat(sc.nextLine());
		System.out.print("Enter Punter Lay Amount : ");
		int punter_lay_amount = Integer.parseInt(sc.nextLine());
		System.out.print("Enter Rebook Lay Odds : ");
		float rebook_lay_odds = Float.parseFloat(sc.nextLine());
		System.out.print("Enter Rebook Lay Amount : ");
		int rebook_lay_amount = Integer.parseInt(sc.nextLine());
		
		sc.close();
		
		System.setProperty("webdriver.opera.driver", "C:\\drivers\\operadriver.exe");
		WebDriver driver = new OperaDriver();
		
		driver.get("https://dreamipl9.in");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_phone"))).sendKeys("vivek96");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_password"))).sendKeys("0909");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("LinkButton1"))).click();
		System.out.println("\nLogged IN....\n");
		
		driver.navigate().to(match_url);
		
		String home_team_name = wait.until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_team01"))).getText();
		String away_team_name = wait.until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_team02"))).getText(); 
		
		System.out.println("****Welcome To Punter's AutoBet - set book both sides at desired odds****");
		System.out.println("\nMonitoring "+home_team_name+" vs "+away_team_name+"\n");
		
		while(monitor<2) {
			
			try {
			
			String home_lay_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.id("FTRate_K1"))).getText();
			float home_lay_odds = Float.parseFloat(home_lay_odds_in_string);
			
			String home_back_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.id("FTRate_L1"))).getText();
			float home_back_odds = Float.parseFloat(home_back_odds_in_string);
			
			String away_lay_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.id("STRate_K1"))).getText();
			float away_lay_odds = Float.parseFloat(away_lay_odds_in_string);
			
			String away_back_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.id("STRate_L1"))).getText();
			float away_back_odds = Float.parseFloat(away_back_odds_in_string);
			
			if(away_back_odds * 100 < home_back_odds * 100) {
				favs_team_name = away_team_name;
				favs_lay_odds = away_lay_odds;
				dogs_team_name = home_team_name;
			} else {
				favs_team_name = home_team_name;
				favs_lay_odds = home_lay_odds;
				dogs_team_name = away_team_name;
			}
			
			
			if(favs_lay_odds * 100 <= punter_lay_odds * 100 && !home_back_odds_in_string.equals(away_back_odds_in_string)) {
				
				System.out.println("FAVORITES "+favs_team_name+" LAYING ODDS **"+favs_lay_odds+"** ARE NOW LESS THAN or equal to "+punter_lay_odds+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]");
				
					if(favs_team_name.equals(punter_lay_team)) {
					
					System.out.println("Now Attempting to LAY "+favs_team_name+" @ "+favs_lay_odds+" or below for amount : "+punter_lay_amount+" rs.");
					
					if(favs_team_name.equals(home_team_name)) {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("FTRate_K1"))).click();
					} else if(favs_team_name.equals(away_team_name)) {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("STRate_K1"))).click();
					}
					
					wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys(Integer.toString(punter_lay_amount));
					wait.until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("jq-toast-single")));
//					Thread.sleep(3000);
					
					try {
						
					String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.className("jq-toast-single"))).getText();
					
					if(toast_message.contains("Bet Place Successfully")) {
						
						System.out.println(toast_message);
						
						try {
							Email email = new Email("autobet786@gmail.com", "bhajjiterminator");
							email.setFrom("autobet786@gmail.com", "AutoBet Notification - "+(++notify));
							email.setSubject(home_team_name+" vs "+away_team_name);
							email.setContent("<h1>This is to inform you that your laying bet has been successfully placed on "+favs_team_name+" @ "+favs_lay_odds+" for amount "+punter_lay_amount+" Rs.</h1>", "text/html");
							email.addRecipient("yadavudit786@gmail.com");
							email.send();
						} catch (Exception e1) {
							System.out.println("Huh huh  _(-_-)  Something got really messed up in Email Sending Mechanism....");
						}
						
						monitor = monitor + 1;
						
						if(monitor<2) {
						System.out.println("\n\n****Firing reverse set book both sides mechanism now............****");
						punter_lay_team = dogs_team_name;
						punter_lay_odds = rebook_lay_odds;
						punter_lay_amount = rebook_lay_amount;
						System.out.println("\nTARGET :- Team To Lay Now -> "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+"\n\n");
						} else {
						System.out.println("\n\n****Congrats !!  Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+"****");
						System.out.println("Punter's AutoBet exiting now........");
						driver.quit();
						}
						
					} else {
						System.out.println(toast_message);
					}
					
					} catch(WebDriverException e) {
						System.out.println(e.toString());
						System.out.println("\n\n<< continuing the flow >>\n\n");
					}
					
				}
					
			} else if(favs_lay_odds * 100 > punter_lay_odds * 100 && !home_back_odds_in_string.equals(away_back_odds_in_string)) {
				System.out.println("FAVORITES "+favs_team_name+" LAYING ODDS **"+favs_lay_odds+"** ARE GREATER THAN "+punter_lay_odds+", AUTO BET IS WAITING...."+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]");
			} else if(home_back_odds_in_string.equals(away_back_odds_in_string)) {
				System.out.println("\nBOTH TEAMS ARE NOW AVAILABLE TO BACK @ "+home_back_odds+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]\n");
			} } catch (WebDriverException f) {
				
				System.out.println("\n\n\n\n******************************* MARKET SUSPENDED -> WEB DRIVER EXCEPTION CAUGHT -> REATTEMPTING EVERYTHING AGAIN *******************************\n\n\n\n");

				try {
					Email email = new Email("autobet786@gmail.com", "bhajjiterminator");
					email.setFrom("autobet786@gmail.com", "AutoBet Notification - Alert "+(++alert));
					email.setSubject(home_team_name+" vs "+away_team_name);
					email.setContent("<h1>Alert Notification - This is to inform you that something got messed up in AutoBet flow....</h1>", "text/html");
					email.addRecipient("yadavudit786@gmail.com");
					email.send();
				} catch (Exception ee) {
					System.out.println("Huh huh  (-_-)  Something got really messed up in Email Sending Mechanism....");
				}
				
				continue;
			}

	}

}}
