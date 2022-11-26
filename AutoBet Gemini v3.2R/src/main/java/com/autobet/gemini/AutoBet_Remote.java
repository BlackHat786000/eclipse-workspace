package com.autobet.gemini;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoBet_Remote {

	public static void main(String[] args) throws InterruptedException {
		
		MyFrame frame = new MyFrame();
		
		int monitor = 0;
		String home_team_name = null;
		String away_team_name = null; 
		String favs_team_name = null;
		String dogs_team_name = null;
		String punter_back_team = null;
		float punter_back_odds = 0;
		float punter_back_amount = 0;
		float favs_back_odds = 0;
		float favs_lay_odds = 0;
		
		String email = frame.EMAIL.getText();
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		boolean valid_mail = matcher.matches();
		
		String match_url = frame.match_url.getText();
		String punter_lay_team = frame.PLT.getText();	// FAVORITES TEAM i.e. the team you are betting against at
		float punter_lay_odds = Float.parseFloat(frame.PLO.getText());
		float punter_lay_amount = Float.parseFloat(frame.PLA.getText());
		String MODE = frame.MODE.getSelectedItem().toString();
		System.out.println("\nMODE : "+MODE);
		
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-latest.exe");
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
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		driver.get(match_url);
		driver.manage().window().maximize();
		
		// LOGIN
		System.out.println("\nLogging in....\n");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[1]/input"))).sendKeys(frame.USERNAME.getText());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[2]/input"))).sendKeys(String.valueOf(frame.PASSWORD.getPassword()));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/button"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/modal-container/div/div/div/div/div[2]/div[3]/button[1]"))).click();
		System.out.println("\nLOGGED IN....");
		Thread.sleep(5000);
		
		try {
			// GETTING HOME & AWAY TEAM NAMES
			home_team_name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/span[1]"))).getText();
			away_team_name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[1]/span[1]"))).getText(); 
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
				punter_back_amount = Float.parseFloat(frame.PBA.getText());
				monitor = 1;
				break;
			}
			
		try {
			
			// GETTING ALL ODDS & INIT BALANCE
			float home_back_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[4]/a"))).getText().split("\n")[0]);
			float home_lay_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a"))).getText().split("\n")[0]);
			float away_back_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[4]/a"))).getText().split("\n")[0]);
			float away_lay_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[5]/a"))).getText().split("\n")[0]);
			String initial_balance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong"))).getText();
			
			// LOGIC TO ASSIGN FAVS & DOGS ALONG WITH MODE
			if(MODE.equals("STANDARD LAY") || MODE.equals("REBOOK AS YOU WANT") || MODE.equals("REBOOK CUT LOSS") || MODE.equals("LAY THEN BACK AS YOU WANT") || MODE.equals("LAY THEN BACK CUT LOSS") || MODE.equals("ONE TIME LAY") || monitor == 1) {
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
			
			// DUMMY CLICK TO AVOID SESSION LOGOUT
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/span[1]"))).click();
			
			// MAIN CONDITION OF LAY BET
			if(favs_lay_odds * 100 <= punter_lay_odds * 100 && favs_team_name.equals(punter_lay_team) && !(home_back_odds == away_back_odds)) {
				
				System.out.println("\nFAVORITES "+favs_team_name+" LAYING ODDS ** "+favs_lay_odds+" ** ARE NOW LESS THAN OR EQUAL TO "+punter_lay_odds+"    [ TEAM TO LAY : "+punter_lay_team+" @ "+punter_lay_odds+" FOR Rs. "+punter_lay_amount+" ]");
				System.out.println("Now Attempting To Lay "+favs_team_name+" @ "+favs_lay_odds+" Or Below For Amount : "+punter_lay_amount+" Rs.");
					
					// PLACE BETS CODE FOR LAY
					if(favs_team_name.equals(home_team_name)) {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a"))).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_lay_odds));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[3]/input"))).sendKeys(Float.toString(punter_lay_amount));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Bets']"))).click();
					} else if(favs_team_name.equals(away_team_name)) {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[5]/a"))).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_lay_odds));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[3]/input"))).sendKeys(Float.toString(punter_lay_amount));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[4]/td/dl/dd[4]/button"))).click();
					}	
					
					// LOGIC TO PRINT FAILURE TOASTS OR CATCH SUCCESS BET
					try {
						
						String toast_message = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='to-message toastr-message ng-star-inserted']"))).getText();
						System.out.println("-> "+toast_message);
						wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@class='to-message toastr-message ng-star-inserted']"),toast_message));
						
					} catch(TimeoutException e) {
						
						System.out.println("\nBET PLACED - TENTATIVE");
						System.out.println("\nInitial Balance : "+initial_balance);
						String updated_balance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong"))).getText();
						System.out.println("Updated Balance : "+updated_balance);
					
						// LOGIC TO CONFIRM SUCCESSFUL BET
						if(!initial_balance.equals(updated_balance)) {
						
							System.out.println("\n**** YOUR BET HAS BEEN PLACED SUCCESSFULLY ****");
							monitor = monitor + 1;
							
							//EMAIL NOTIFICATION
							if(valid_mail) {
							Mail.sendmail("laying",home_team_name, away_team_name, favs_team_name, favs_lay_odds, punter_lay_amount, MODE, email);
							} else if(email.equals("OPTIONAL")) {
							} else {
								System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
							}
							
							if(MODE.equals("ONE TIME LAY")) {
								monitor = 2;
							}
							
							// LOGIC TO FIRE REBOOK
							if(monitor<2) {
								System.out.println("\n\n**** Firing Reverse Set Book Both Sides Mechanism Now.... ****");
								
								// TO TRACK DOGS TEAM NOW
								punter_lay_team = dogs_team_name;
								
								if(MODE.equals("REBOOK AS YOU WANT")) {
									punter_lay_odds = Float.parseFloat(frame.RLO.getText());;
									punter_lay_amount = Float.parseFloat(frame.RLA.getText());;
								} else if(MODE.equals("REBOOK CUT LOSS")) {
									punter_lay_odds = Float.parseFloat(frame.RLO.getText());
									punter_lay_amount = punter_lay_amount * (favs_lay_odds - 1);	// exposure balance
									if(punter_lay_amount < 100) {
										punter_lay_amount = 100;
									}
								} else if(MODE.equals("LAY THEN BACK AS YOU WANT")) {
									punter_back_team = favs_team_name;
									punter_back_odds = Float.parseFloat(frame.PBO.getText());
									punter_back_amount = Float.parseFloat(frame.PBA.getText());
									break;
								} else if(MODE.equals("LAY THEN BACK CUT LOSS")) {
									float exposure = punter_lay_amount * (favs_lay_odds - 1);	// exposure balance
									punter_back_team = favs_team_name;
									punter_back_odds = Float.parseFloat(frame.PBO.getText());
									punter_back_amount = exposure / (punter_back_odds - 1);
									if(punter_back_amount < 100) {
										punter_back_amount = 100;
									}
									break;
								}
								
								System.out.println("\nTARGET ----> Team To Lay Now --> "+punter_lay_team+" @ "+punter_lay_odds+" For Rs. "+punter_lay_amount+"\n\n");
								
							} else { 
								System.out.println("\n\n**** Congratulations !! Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+" ****");
								System.out.println("\nAUTOBET EXITING NOW....");
								driver.quit();
								Thread.sleep(2500);
							}
					
					  } else {
						System.out.println("\n\n**** TENTATIVE BET NOT CONFIRMED, AUTOBET WILL TRY AGAIN TO PLACE BET.... ****\n\n");
					  }
						
				   } // CATCH
		
					
				} else if(!(home_back_odds == away_back_odds)) {
					System.out.println("FAVORITES "+favs_team_name+" LAYING ODDS ** "+favs_lay_odds+" **, AUTOBET IS WAITING....    [ TEAM TO LAY : "+punter_lay_team+" @ "+punter_lay_odds+" FOR Rs. "+punter_lay_amount+" ]");
				} else if(home_back_odds == away_back_odds) {
					System.out.println("\n\nBOTH TEAMS ARE NOW AVAILABLE TO BACK @ "+home_back_odds+"    [ TEAM TO LAY : "+punter_lay_team+" @ "+punter_lay_odds+" FOR Rs. "+punter_lay_amount+" ]\n\n");
				} 
				
			
				} catch (ElementClickInterceptedException e) {
					System.out.println("\n**** MARKET IS SUSPENDED, AUTOBET IS WAITING FOR MARKET TO BECOME ACTIVE.... ****\n");
					continue;
				// CATCH & HANDLE ALL EXCEPTIONS
				} catch (WebDriverException e) {
					System.out.println("\n\n**** UNFORESEEN CONDITION, AUTOBET WILL HANDLE IT ****\n\n");
					
					//EMAIL ALERT
					if(valid_mail) {
						Mail.sendalert(home_team_name, away_team_name, MODE, email);
					} else if(email.equals("OPTIONAL")) {
					} else {
						System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
					}
					
					driver.navigate().to(match_url);
					try {
						System.out.println("Checking for logged in or logged out......");
//						Thread.sleep(5000);
						// Checking for 'My Account' element, if present then logged in otherwise logged out
						if (driver.findElement(By.id("accountPopup")).isDisplayed()) {
							System.out.println("\nYou are already LOGGED IN");
						}
					} catch (WebDriverException e1) {
						System.out.println("\nYou have been LOGGED OUT, trying to LOGIN now");
						// Login code
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[1]/input"))).sendKeys("aaagem2");
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[2]/input"))).sendKeys("Iaminsane@786");
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/button"))).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/modal-container/div/div/div/div/div[2]/div[3]/button[1]"))).click();
						System.out.println("\nLOGGED IN....");
						Thread.sleep(5000);
						continue;
						}
					
			} // CATCH
			
		} // WHILE - 1st loop
		
//-----------------------------------------------------------------------------------------------------------------------
//													BACK LOGIC
//-----------------------------------------------------------------------------------------------------------------------
		
		System.out.println("\nTARGET ----> Team To Back Now --> "+punter_back_team+" @ "+punter_back_odds+" For Rs. "+ (int) punter_back_amount+"\n\n");
		
		while(monitor<2) {
			
			try {
				
				// GETTING ALL ODDS & INIT BALANCE
				float home_back_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[4]/a"))).getText().split("\n")[0]);
				float home_lay_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a"))).getText().split("\n")[0]);
				float away_back_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[4]/a"))).getText().split("\n")[0]);
				float away_lay_odds = Float.parseFloat(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[5]/a"))).getText().split("\n")[0]);
				String initial_balance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong"))).getText();
				
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
				
				// DUMMY CLICK TO AVOID SESSION LOGOUT
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/span[1]"))).click();
				
				// MAIN CONDITION OF BACK BET
				if(favs_back_odds * 100 >= punter_back_odds * 100 && favs_team_name.equals(punter_back_team) && !(home_back_odds == away_back_odds)) {
					
					System.out.println("\nFAVORITES "+favs_team_name+" BACKING ODDS ** "+favs_back_odds+" ** ARE NOW MORE THAN OR EQUAL TO "+punter_back_odds+"    [ TEAM TO BACK : "+punter_back_team+" @ "+punter_back_odds+" FOR Rs. "+ (int) punter_back_amount+" ]");
					System.out.println("Now Attempting To Back "+favs_team_name+" @ "+favs_back_odds+" Or Above For Amount : "+punter_back_amount+" Rs.");
						
						// PLACE BETS CODE FOR BACK
						if(favs_team_name.equals(home_team_name)) {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[4]/a"))).click();
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_back_odds));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[3]/input"))).sendKeys(Float.toString((int) punter_back_amount));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Bets']"))).click();
						} else if(favs_team_name.equals(away_team_name)) {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[4]/a"))).click();
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_back_odds));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[3]/input"))).sendKeys(Float.toString((int) punter_back_amount));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[4]/td/dl/dd[4]/button"))).click();
						}	
						
						// LOGIC TO PRINT FAILURE TOASTS OR CATCH SUCCESS BET
						try {
							
							String toast_message = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='to-message toastr-message ng-star-inserted']"))).getText();
							System.out.println("-> "+toast_message);
							wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@class='to-message toastr-message ng-star-inserted']"),toast_message));
							
						} catch(TimeoutException e) {
							
							System.out.println("\nBET PLACED - TENTATIVE");
							System.out.println("\nInitial Balance : "+initial_balance);
							String updated_balance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong"))).getText();
							System.out.println("Updated Balance : "+updated_balance);
						
							// LOGIC TO CONFIRM SUCCESSFUL BET
							if(!initial_balance.equals(updated_balance)) {
								
								System.out.println("\n**** YOUR BET HAS BEEN PLACED SUCCESSFULLY ****");
								monitor = monitor + 1;
								
								//EMAIL NOTIFICATION
								if(valid_mail) {
									Mail.sendmail("backing",home_team_name, away_team_name, favs_team_name, favs_back_odds, punter_back_amount, MODE, email);
								} else if(email.equals("OPTIONAL")) {
								} else {
									System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
								}
								
								System.out.println("\n\n**** Congratulations !! Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+" ****");
								System.out.println("\nAUTOBET EXITING NOW....");
								driver.quit();
								Thread.sleep(2500);
								
						  } else {
							System.out.println("\n\n**** TENTATIVE BET NOT CONFIRMED, AUTOBET WILL TRY AGAIN TO PLACE BET.... ****\n\n");
						  }
							
					   } // CATCH
			
						
					} else if(favs_team_name.equals(punter_back_team) && !(home_back_odds == away_back_odds)) {
						System.out.println("FAVORITES "+favs_team_name+" BACKING ODDS ** "+favs_back_odds+" **, AUTOBET IS WAITING....    [ TEAM TO BACK : "+punter_back_team+" @ "+punter_back_odds+" FOR Rs. "+ (int) punter_back_amount+" ]");
					} else if(home_back_odds == away_back_odds) {
						System.out.println("\n\nBOTH TEAMS ARE NOW AVAILABLE TO BACK @ "+home_back_odds+"    [ TEAM TO BACK : "+punter_back_team+" @ "+punter_back_odds+" FOR Rs. "+ (int) punter_back_amount+" ]\n\n");
						
					// TABLES TURNED LOGIC
					} else if(!favs_team_name.equals(punter_back_team) && favs_lay_odds * 100 <= 1.99 * 100) {
						
						System.out.println("\n\n**** TABLES HAVE BEEN TURNED. FAVORITES NOW = "+favs_team_name+" | FAVORITES LAYING ODDS = "+favs_lay_odds+" ****\n\n");
						System.out.println("\nIMMEDIATELY GOING TO LAY FAVORITES "+favs_team_name+" @ ODDS "+favs_lay_odds+" FOR RS. "+punter_back_amount);
						
						// PLACE BETS CODE FOR LAY - Tables Turned
						if(favs_team_name.equals(home_team_name)) {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a"))).click();
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_lay_odds));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/dl/dd[3]/input"))).sendKeys(Float.toString((int) punter_back_amount));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Bets']"))).click();
						} else if(favs_team_name.equals(away_team_name)) {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td[5]/a"))).click();
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[2]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), Float.toString(favs_lay_odds));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/dl/dd[3]/input"))).sendKeys(Float.toString((int) punter_back_amount));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[4]/td/dl/dd[4]/button"))).click();
						}
						
						// LOGIC TO PRINT FAILURE TOASTS OR CATCH SUCCESS BET
						try {
							
							String toast_message = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='to-message toastr-message ng-star-inserted']"))).getText();
							System.out.println("-> "+toast_message);
							wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@class='to-message toastr-message ng-star-inserted']"),toast_message));
							
						} catch(TimeoutException e) {
							
							System.out.println("\nBET PLACED - TENTATIVE");
							System.out.println("\nInitial Balance : "+initial_balance);
							String updated_balance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong"))).getText();
							System.out.println("Updated Balance : "+updated_balance);
						
							// LOGIC TO CONFIRM SUCCESSFUL BET
							if(!initial_balance.equals(updated_balance)) {
							
								System.out.println("\n**** YOUR BET HAS BEEN PLACED SUCCESSFULLY ****");
								monitor = monitor + 1;
								
								//EMAIL NOTIFICATION
								if(valid_mail) {
									Mail.sendmail("laying",home_team_name, away_team_name, favs_team_name, favs_lay_odds, punter_back_amount, MODE, email);
								} else if(email.equals("OPTIONAL")) {
								} else {
									System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
								}
						
								System.out.println("\n\n**** Congratulations !! Your book has been set both sides successfully in "+home_team_name+" vs "+away_team_name+" ****");
								System.out.println("\nAUTOBET EXITING NOW....");
								driver.quit();
								Thread.sleep(2500);
						
							} else {
								System.out.println("\n\n**** TENTATIVE BET NOT CONFIRMED, AUTOBET WILL TRY AGAIN TO PLACE BET.... ****\n\n");
							}
							
						} // CATCH
						
					} // END OF ELSE IF - Tables Turned
					
					
					} catch (ElementClickInterceptedException e) {
						System.out.println("\n**** MARKET IS SUSPENDED, AUTOBET IS WAITING FOR MARKET TO BECOME ACTIVE.... ****\n");
						continue;
					// CATCH & HANDLE ALL EXCEPTIONS
					} catch (WebDriverException e) {
						System.out.println("\n\n**** UNFORESEEN CONDITION, AUTOBET WILL HANDLE IT ****\n\n");
						
						//EMAIL ALERT
						if(valid_mail) {
							Mail.sendalert(home_team_name, away_team_name, MODE, email);
						} else if(email.equals("OPTIONAL")) {
						} else {
							System.out.println("\n\n**** INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ****\n\n");
						}
						
						try {
							System.out.println("Checking for logged in or logged out......");
//							Thread.sleep(5000);
							// Checking for 'My Account' element, if present then logged in otherwise logged out
							if (driver.findElement(By.id("accountPopup")).isDisplayed()) {
								System.out.println("\nYou are already LOGGED IN");
							}
						} catch (WebDriverException e1) {
							System.out.println("\nYou have been LOGGED OUT, trying to LOGIN now");
							// Login code
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[1]/input"))).sendKeys(frame.USERNAME.getText());
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[2]/input"))).sendKeys(String.valueOf(frame.PASSWORD.getPassword()));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/button"))).click();
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/modal-container/div/div/div/div/div[2]/div[3]/button[1]"))).click();
							System.out.println("\nLOGGED IN....");
							Thread.sleep(5000);
							continue;
							}
						
				} // CATCH
				
			} // WHILE - 2nd loop
		
//-----------------------------------------------------------------------------------------------------------------------
			
	} // MAIN

} // CLASS
