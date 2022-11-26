package com.autobet.sky1exchange;

import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoBet_Sky1exchange {

	public static void main(String[] args) throws InterruptedException {
		
		String home_lay = "/html/body/div[4]/div[2]/div[6]/div[9]/table/tbody/tr[3]/td[7]";
		String home_back = "/html/body/div[4]/div[2]/div[6]/div[9]/table/tbody/tr[3]/td[6]";
		String away_lay = "/html/body/div[4]/div[2]/div[6]/div[9]/table/tbody/tr[4]/td[7]";
		String away_back = "/html/body/div[4]/div[2]/div[6]/div[9]/table/tbody/tr[4]/td[6]";
//		String input_stake = "/html/body/div[4]/div[3]/div[1]/div[2]/div[1]/div[3]/dl/dd[2]/input";
//		String toast = "/html/body/div[4]/div[3]/div[1]/ul/li[2]";
		
		MyFrame frame = new MyFrame();
		
		int monitor = 0;
		String home_team_name = null;
		String away_team_name = null;
		String favs_team_name = null;
		String dogs_team_name = null;
		float favs_lay_odds = 0;
		float favs_back_odds;
		String punter_back_team = null;
		float punter_back_odds = 0;
		int punter_back_amount = 0;
		
		System.out.println("\n********** Welcome To Punter's AutoBet (Sky1exchange) - set book both sides at desired odds **********\n\n");
		
		String email = frame.EMAIL.getText();
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		boolean valid_mail = matcher.matches();
		
		String match_url = frame.match_url.getText();
		String punter_lay_team = frame.PLT.getText();
		float punter_lay_odds = Float.parseFloat(frame.PLO.getText());
		int punter_lay_amount = Integer.parseInt(frame.PLA.getText());
		String MODE = frame.MODE.getSelectedItem().toString();
		System.out.println("\nMODE SELECTED : "+MODE);
		
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		@SuppressWarnings("rawtypes")
		DriverService.Builder serviceBuilder = new ChromeDriverService.Builder().withSilent(true);
		ChromeOptions options = new ChromeOptions();
		ChromeDriverService chromeDriverService = (ChromeDriverService) serviceBuilder.build();
		chromeDriverService.sendOutputTo(new OutputStream() {
			@Override
			public void write(int b) {
			}
		});
		WebDriver driver = new ChromeDriver(chromeDriverService, options);
		
		driver.get("https://sky1exchange.com/exchange/member/login");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		Actions actions = new Actions(driver);
		
		System.out.println("\nLogging in....\n");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginName"))).sendKeys(frame.USERNAME.getText());
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys(String.valueOf(frame.PASSWORD.getPassword()));
		System.out.print("Enter Validation Code : ");
		Scanner scan = new Scanner(System.in);
		String validationCode = scan.nextLine();
		scan.close();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("validCode"))).sendKeys(validationCode);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn"))).click();
		System.out.println("\nLogged IN....\n");
		Thread.sleep(2500);
		
		driver.navigate().to(match_url);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("closeStreamingBox"))).click();
		WebElement home_team = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[2]/div[6]/div[9]/table/tbody/tr[3]/th/p")));
		try {
			// GETTING HOME & AWAY TEAM NAMES
			home_team_name = home_team.getText();
			away_team_name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[2]/div[6]/div[9]/table/tbody/tr[4]/th/p"))).getText();
			System.out.println("\n**** Welcome To Punter's AutoBet - set book both sides at desired odds ****");
			System.out.println("\nMonitoring "+home_team_name+" vs "+away_team_name+"\n");
		} catch(TimeoutException e) {
			System.out.println("\nMARKET DOES NOT EXIST, AUTOBET WILL EXIT NOW  PLEASE RESTART AUTOBET WITH CORRECT MARKET-MATCH URL");
			driver.quit();
			System.exit(0);
		} 
		
		while(monitor<2) {
			
			if(MODE.equals("ONE TIME BACK")) {
				punter_back_team = frame.PLT.getText();
				punter_back_odds = Float.parseFloat(frame.PBO.getText());
				punter_back_amount = Integer.parseInt(frame.PBA.getText());
				monitor = 1;
				break;
			}
			
			try {
			
			String home_lay_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(home_lay))).getText().split("\n")[0];
			float home_lay_odds = Float.parseFloat(home_lay_odds_in_string);
			
			String home_back_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(home_back))).getText().split("\n")[0];
			float home_back_odds = Float.parseFloat(home_back_odds_in_string);
			
			String away_lay_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(away_lay))).getText().split("\n")[0];
			float away_lay_odds = Float.parseFloat(away_lay_odds_in_string);
			
			String away_back_odds_in_string = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(away_back))).getText().split("\n")[0];
			float away_back_odds = Float.parseFloat(away_back_odds_in_string);
			
			if(MODE.equals("STANDARD LAY") || MODE.equals("REBOOK AS YOU WANT") || MODE.equals("REBOOK CUT LOSS") || MODE.equals("LAY THEN BACK AS YOU WANT") || MODE.equals("LAY THEN BACK CUT LOSS") || MODE.equals("ONE TIME LAY") || monitor == 1) {
			if(away_back_odds * 100 < home_back_odds * 100) {
				favs_team_name = away_team_name;
				favs_lay_odds = away_lay_odds;
				dogs_team_name = home_team_name;
			} else {
				favs_team_name = home_team_name;
				favs_lay_odds = home_lay_odds;
				dogs_team_name = away_team_name;
			}
			
			} else if(MODE.equals("AUTO LAY ANY")) {
				if(away_back_odds * 100 < home_back_odds * 100) {
					favs_team_name = away_team_name;
					favs_lay_odds = away_lay_odds;
					dogs_team_name = home_team_name;
					punter_lay_team = favs_team_name;
				} else {
					favs_team_name = home_team_name;
					favs_lay_odds = home_lay_odds;
					dogs_team_name = away_team_name;
					punter_lay_team = favs_team_name;
				}
			}
			
			if(favs_lay_odds * 100 <= punter_lay_odds * 100 && favs_team_name.equals(punter_lay_team) && !home_back_odds_in_string.equals(away_back_odds_in_string)) {
				
				System.out.println("FAVORITES "+favs_team_name+" LAYING ODDS **"+favs_lay_odds+"** ARE NOW LESS THAN or equal to "+punter_lay_odds+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]");
				System.out.println("Now Attempting to LAY "+favs_team_name+" @ "+favs_lay_odds+" or below for amount : "+punter_lay_amount+" rs.");
					
					if(favs_team_name.equals(home_team_name)) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(home_lay))).click();
					} else if(favs_team_name.equals(away_team_name)) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(away_lay))).click();
					}
					
					String beforeBetBal = wait.until(ExpectedConditions.elementToBeClickable(By.id("betCredit"))).getText();
					
					driver.findElement(By.tagName("body")).sendKeys(Keys.TAB);
					WebElement currentElement = driver.switchTo().activeElement();
					currentElement.sendKeys(Integer.toString(punter_lay_amount), Keys.ENTER);
//					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(toast)));
					Thread.sleep(6000);
					
					String afterBetBal = wait.until(ExpectedConditions.elementToBeClickable(By.id("betCredit"))).getText();
					
					try {
						
//					String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.id("errorMsg"))).getText();
					
					if(!beforeBetBal.equals(afterBetBal)) {
						
						monitor = monitor + 1;
						
						System.out.println("\n-> Bet Place Successfully");
						
						if(valid_mail) {
							Mail.sendmail("laying",home_team_name, away_team_name, favs_team_name, favs_lay_odds, punter_lay_amount, MODE, email);
							} else if(email.equals("OPTIONAL")) {
							} else {
								System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
							}
						
						if(MODE.equals("ONE TIME LAY")) {
							System.out.println("\n\n**** Congratulations !!  Your ONE TIME LAYING BET has been successfully placed on "+favs_team_name+" @ "+favs_lay_odds+" for amount "+punter_lay_amount+" Rs. ****");
							System.out.println("Punter's AutoBet exiting now........");
							driver.quit();
							Thread.sleep(2500);
							System.exit(0);
						}
						
						if(monitor<2) {
						System.out.println("\n\n**** Firing reverse set book both sides mechanism now............ ****");
						
						punter_lay_team = dogs_team_name;
						
						if(MODE.equals("REBOOK AS YOU WANT")) {
						punter_lay_odds = Float.parseFloat(frame.RLO.getText());
						punter_lay_amount = Integer.parseInt(frame.RLA.getText());
						} else if(MODE.equals("REBOOK CUT LOSS")) {
							punter_lay_odds = Float.parseFloat(frame.RLO.getText());
							punter_lay_amount = (int) (punter_lay_amount * (favs_lay_odds - 1));	// exposure balance
							if(punter_lay_amount < 100) {
								punter_lay_amount = 100;
							}
						} else if(MODE.equals("LAY THEN BACK AS YOU WANT")) {
							punter_back_team = favs_team_name;
							punter_back_odds = Float.parseFloat(frame.PBO.getText());
							punter_back_amount = Integer.parseInt(frame.PBA.getText());
							System.out.println("\nTARGET ----> Team To Back Now --> "+punter_back_team+" @ "+punter_back_odds+" For Rs. "+ punter_back_amount+"\n\n");
							break;
						} else if(MODE.equals("LAY THEN BACK CUT LOSS")) {
							float exposure = punter_lay_amount * (favs_lay_odds - 1);	// exposure balance
							punter_back_team = favs_team_name;
							punter_back_odds = Float.parseFloat(frame.PBO.getText());
							punter_back_amount = (int) (exposure / (punter_back_odds - 1));
							if(punter_back_amount < 100) {
								punter_back_amount = 100;
							}
							System.out.println("\nTARGET ----> Team To Back Now --> "+punter_back_team+" @ "+punter_back_odds+" For Rs. "+ punter_back_amount+"\n\n");
							break;
						}
						
						System.out.println("\nTARGET :- Team To Lay Now -> "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+"\n\n");
						} else {
						System.out.println("\n\n**** Congratulations !!  Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+" ****");
						System.out.println("Punter's AutoBet exiting now........");
						Thread.sleep(2500);
						driver.quit();
						}
						
					} else {
						System.out.println("\n-> Bet Not Placed\n");
						wait.until(ExpectedConditions.elementToBeClickable(By.id("cancelAll"))).click();
					}
					
					} catch(WebDriverException e) {
						System.out.println(e.toString());
						System.out.println("\n\n<< continuing the flow >>\n\n");
					}
					
					
			} else if(!(home_back_odds == away_back_odds)) {
				System.out.println("FAVORITES "+favs_team_name+" LAYING ODDS ** "+favs_lay_odds+" ** , AUTO BET IS WAITING...."+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]");
			} else if(home_back_odds == away_back_odds) {
				System.out.println("\nBOTH TEAMS ARE NOW AVAILABLE TO BACK @ "+home_back_odds+"    [ Team To Lay : "+punter_lay_team+" @ "+punter_lay_odds+" for Rs. "+punter_lay_amount+" ]\n");
			} 
			
			
			} catch (WebDriverException f) {
				
				driver.navigate().refresh();
				System.out.println("\n\n**** MARKET IS SUSPENDED, AUTOBET IS WAITING FOR MARKET TO BECOME ACTIVE.... ****\n\n"+f);
				
				if(valid_mail) {
					Mail.sendalert(home_team_name, away_team_name, MODE, email);
				} else if(email.equals("OPTIONAL")) {
				} else {
					System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
				}
				
				continue;
			}

	} // WHILE - 1st loop
		

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		BACK LOGIC
//--------------------------------------------------------------------------------------------------------------------------------------------------------------

		while(monitor<2) {
			
			try {
				
				// GETTING ALL ODDS
				float home_back_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.id("FTRate_L1"))).getText());
				float home_lay_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.id("FTRate_K1"))).getText());
				float away_back_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.id("STRate_L1"))).getText());
				float away_lay_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.id("STRate_K1"))).getText());
				
					// LOGIC TO ASSIGN FAVS & DOGS
					if(away_back_odds * 100 < home_back_odds * 100) {
						favs_team_name = away_team_name;
						favs_back_odds = away_back_odds;
						favs_lay_odds = away_lay_odds;
						dogs_team_name = home_team_name;
					} else {
						favs_team_name = home_team_name;
						favs_back_odds = home_back_odds;
						favs_lay_odds = home_lay_odds;
						dogs_team_name = away_team_name;
					}
				
				// MAIN CONDITION OF BACK BET
				if(favs_back_odds * 100 >= punter_back_odds * 100 && favs_team_name.equals(punter_back_team) && !(home_back_odds == away_back_odds)) {
					
					System.out.println("\nFAVORITES "+favs_team_name+" BACKING ODDS ** "+favs_back_odds+" ** ARE NOW MORE THAN OR EQUAL TO "+punter_back_odds+"    [ TEAM TO BACK : "+punter_back_team+" @ "+punter_back_odds+" FOR Rs. "+ punter_back_amount+" ]");
					System.out.println("Now Attempting To Back "+favs_team_name+" @ "+favs_back_odds+" Or Above For Amount : "+punter_back_amount+" Rs.");
						
						// PLACE BETS CODE FOR BACK
						if(favs_team_name.equals(home_team_name)) {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("FTRate_L1"))).click();
						} else if(favs_team_name.equals(away_team_name)) {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("STRate_L1"))).click();
						}
						
						wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys(Integer.toString(punter_lay_amount));
						wait.until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("jq-toast-single")));
//						Thread.sleep(3000);	
						
						// LOGIC TO PRINT FAILURE TOASTS OR CATCH SUCCESS BET
						try {
							
							String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.id("errorMsg"))).getText();
							
							if(toast_message.contains("Bet Place Successfully")) {
								
								monitor = monitor + 1;
								
								System.out.println("\n-> "+toast_message);
								
								// EMAIL NOTIFICATION
								if(valid_mail) {
									Mail.sendmail("backing",home_team_name, away_team_name, favs_team_name, favs_back_odds, punter_back_amount, MODE, email);
								} else if(email.equals("OPTIONAL")) {
								} else {
									System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
								}
								
								if(MODE.equals("ONE TIME BACK")) {
									System.out.println("\n\n**** Congratulations !!  Your ONE TIME BACKING BET has been successfully placed on "+favs_team_name+" @ "+favs_back_odds+" for amount "+punter_back_amount+" Rs. ****");
									System.out.println("Punter's AutoBet exiting now........");
									driver.quit();
									Thread.sleep(2500);
									System.exit(0);
								}
								
								System.out.println("\n\n**** Congratulations !! Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+" ****");
								System.out.println("\nAUTOBET EXITING NOW....");
								driver.quit();
								Thread.sleep(2500);
								
							} else {
								System.out.println("\n-> "+toast_message+"\n");
							}
							
							} catch(WebDriverException e) {
								System.out.println(e.toString());
								System.out.println("\n\n<< continuing the flow >>\n\n");
							} // CATCH
			
						
					} else if(favs_team_name.equals(punter_back_team) && !(home_back_odds == away_back_odds)) {
						System.out.println("FAVORITES "+favs_team_name+" BACKING ODDS ** "+favs_back_odds+" **, AUTOBET IS WAITING....    [ TEAM TO BACK : "+punter_back_team+" @ "+punter_back_odds+" FOR Rs. "+ punter_back_amount+" ]");
					} else if(home_back_odds == away_back_odds) {
						System.out.println("\n\nBOTH TEAMS ARE NOW AVAILABLE TO BACK @ "+home_back_odds+"    [ TEAM TO BACK : "+punter_back_team+" @ "+punter_back_odds+" FOR Rs. "+ punter_back_amount+" ]\n\n");
						
					// TABLES TURNED LOGIC
					} else if(!favs_team_name.equals(punter_back_team) && favs_lay_odds * 100 <= 1.99 * 100) {
						
						System.out.println("\n\n**** TABLES HAVE BEEN TURNED. FAVORITES NOW = "+favs_team_name+" | FAVORITES LAYING ODDS = "+favs_lay_odds+" ****\n\n");
						int ttla = (int) (punter_back_amount * (punter_back_odds - 1));
						System.out.println("\nIMMEDIATELY GOING TO LAY FAVORITES "+favs_team_name+" @ ODDS "+favs_lay_odds+" FOR RS. "+ttla);
						
						// PLACE BETS CODE FOR LAY - Tables Turned
						if(favs_team_name.equals(home_team_name)) {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("FTRate_K1"))).click();
							} else if(favs_team_name.equals(away_team_name)) {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("STRate_K1"))).click();
							}
							
							wait.until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys(Integer.toString(ttla));
							wait.until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("jq-toast-single")));
//							Thread.sleep(3000);
							
							try {
								
							String toast_message = wait.until(ExpectedConditions.elementToBeClickable(By.className("jq-toast-single"))).getText();
							
							if(toast_message.contains("Bet Place Successfully")) {
								
								monitor = monitor + 1;
								
								System.out.println(toast_message);
								
								if(valid_mail) {
									Mail.sendmail("laying (tables turned)",home_team_name, away_team_name, favs_team_name, favs_lay_odds, ttla, MODE, email);
									} else if(email.equals("OPTIONAL")) {
									} else {
										System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
									}
								
								if(MODE.equals("ONE TIME BACK")) {
									System.out.println("\n\n**** Congratulations !!  Your ONE TIME BACKING BET (TABLES TURNED - LAYING BET) has been successfully placed on "+favs_team_name+" @ "+favs_lay_odds+" for amount "+ttla+" Rs. ****");
									System.out.println("Punter's AutoBet exiting now........");
									driver.quit();
									Thread.sleep(2500);
									System.exit(0);
								}
								
								System.out.println("\n\n**** Congratulations !! Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+" ****");
								System.out.println("\nAUTOBET EXITING NOW....");
								driver.quit();
								Thread.sleep(2500);
						
							} else {
								System.out.println(toast_message);
							}
							
							} catch(WebDriverException e) {
								System.out.println(e.toString());
								System.out.println("\n\n<< continuing the flow >>\n\n");
							} 
					
					} // END OF ELSE IF - Tables Turned
					
				
				} catch (WebDriverException f) {
					
					driver.navigate().refresh();
					System.out.println("\n\n**** MARKET IS SUSPENDED, AUTOBET IS WAITING FOR MARKET TO BECOME ACTIVE.... ****\n\n");
				
				if(valid_mail) {
					Mail.sendalert(home_team_name, away_team_name, MODE, email);
				} else if(email.equals("OPTIONAL")) {
				} else {
					System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
				}
				
				continue;
			}
			
	
} // WHILE - 2nd loop

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
		
}}
