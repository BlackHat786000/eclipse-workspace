package com.n00bC0der.code;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class AutoBetCasino {

	public static void main(String[] args) throws InterruptedException {

		int MONITOR = 1;
		float temp_Bal = 0;
		int retry = 0;

		String CASINO_URL = "https://fairexch9.com/#/home";
		String BET_AMOUNT = "100";
		int ROUND = 20;
		float TO_WIN = 300;

		String COUNTER_XPATH = "/html/body/div/ng-component/div[1]/div[3]/div[1]/section/dl/dt[1]/p";
		String BALANCE_XPATH = "/html/body/div/ng-component/div[1]/div[1]/div/div/ol/li[2]/span";

		String PLAYER_A_XPATH = "/html/body/div/ng-component/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[1]/td[2]";
		String FILL_AMOUNT_XPATH_FOR_A = "/html/body/div/ng-component/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[2]/td/dl/dd[3]/input";
		String PLACE_BETS_XPATH_FOR_A = "//button[text()='Place Bets']";

		String PLAYER_B_XPATH = "/html/body/div/ng-component/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[3]/td[2]";
		String FILL_AMOUNT_XPATH_FOR_B = "/html/body/div/ng-component/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[4]/td/dl/dd[3]/input";
		String PLACE_BETS_XPATH_FOR_B = "//tr[4]/td/dl/dd[4]/button";

		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver79.exe");
		OperaOptions options = new OperaOptions();
		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
		WebDriver driver;
		driver = new OperaDriver(options);

		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);

		driver.navigate().to(CASINO_URL);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-dashboard/div/div/div/ul/li[7]/a")))
				.click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
//		wait.until(ExpectedConditions
//				.elementToBeClickable(By.xpath("/html/body/div/ng-component/div[1]/div/div[2]/div[1]/div/div/img")))
//				.click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("dealerImage"))).click();
		driver.navigate().to("https://real-game.club/#/casino-event/asia");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]/div/div[2]/ul/li/a"))).click();

		System.out.println(
				"************Welcome To Punter's AutoBet - places random bets for you while you are busy************\n\n");
		Thread.sleep(5000);

		String STARTING_BALANCE = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BALANCE_XPATH))).getText()
				.replaceAll("[,]", "");
		float FLOAT_STARTING_BALANCE = Float.parseFloat(STARTING_BALANCE);
		float TARGET_BALANCE = FLOAT_STARTING_BALANCE + TO_WIN;

		while (MONITOR <= ROUND + 1) {
			try {
				String COUNTER = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(COUNTER_XPATH))).getText();
				if (COUNTER.equals("0")) {
					System.out.println("SUSPENDED || AutoBet is waiting for current round to finish");
					continue;
				} else {
					String current_balance = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(BALANCE_XPATH))).getText()
							.replaceAll("[,]", "");
					;
					float float_current_balance = Float.parseFloat(current_balance);
					if (temp_Bal != 0 && temp_Bal * 100 < float_current_balance * 100) {
						System.out.println("\n\n**********[ INFO ] : You have WON in previous round " + (MONITOR - 1)
								+ "**********");
					} else if (temp_Bal != 0 && temp_Bal * 100 > float_current_balance * 100) {
						System.out.println("\n\n**********[ INFO ] : You have LOST in previous round " + (MONITOR - 1)
								+ "**********");
					}

					if (TARGET_BALANCE * 100 <= float_current_balance * 100) {
						System.out.println("Target Balance : " + TARGET_BALANCE);
						System.out.println("Current Balance : " + float_current_balance);
						System.out.println("\n\n******AutoBet has achieved it's given Target Balance of "
								+ TARGET_BALANCE + "******");
						System.out.println("******AutoBet Exiting Now, Bye Bye******");
						System.out.println("\n[ INFO ] : Starting Balance : " + FLOAT_STARTING_BALANCE);
						System.out.println("[ INFO ] : Final Balance : " + float_current_balance);
						System.out.println("[ INFO ] : Retry Count : " + retry);
						Thread.sleep(1000000000);
					}

					if (MONITOR == ROUND + 1) {
						System.out.println("\n\n******AutoBet has completed it's given " + ROUND + " rounds******");
						System.out.println("\nGetting Final Balance......");
						System.out.println("\n[ INFO ] : Starting Balance : " + FLOAT_STARTING_BALANCE);
						System.out.println("[ INFO ] : Final Balance : " + float_current_balance);
						System.out.println("[ INFO ] : Retry Count : " + retry);
						System.out.println(
								"\n\n******************************************************AutoBet Exiting Now, Bye Bye******************************************************");
						Thread.sleep(1000000000);
					}

					int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);
					if (randomNum % 2 == 0) {
						System.out.println("\n\n********ROUND " + MONITOR + "********");
						System.out.println("\n****Attempting to place bet on Player A****");
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PLAYER_A_XPATH))).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILL_AMOUNT_XPATH_FOR_A)))
								.sendKeys(BET_AMOUNT);
						String INITIAL_BALANCE = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(BALANCE_XPATH))).getText();
						temp_Bal = float_current_balance;
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PLACE_BETS_XPATH_FOR_A))).click();
						Thread.sleep(5000);
						String UPDATED_BALANCE = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(BALANCE_XPATH))).getText();
						System.out.println("\nCurrent Balance : " + INITIAL_BALANCE);
						System.out.println("Exposure : " + BET_AMOUNT);
						System.out.println("Balance After Exposure : " + UPDATED_BALANCE);

						if (INITIAL_BALANCE.equals(UPDATED_BALANCE)) {
							System.out.println(
									"\nbalance still not updated, please check for your bet manually, AutoBet waiting for 10 seconds before resuming again..............................");
							Thread.sleep(10000);
						} else {
							System.out.println("\n******YOUR BET HAS BEEN PLACED SUCCESSFULLY******");
							MONITOR = MONITOR + 1;
						}

						String IMPLICIT_WAIT = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(COUNTER_XPATH))).getText();
						int INT_IMPLICIT_WAIT = Integer.parseInt(IMPLICIT_WAIT);
						System.out.println("\n\nWaiting for " + (INT_IMPLICIT_WAIT + 5)
								+ " seconds for current round to finish\n\n");
						Thread.sleep((INT_IMPLICIT_WAIT + 5) * 1000);

					} else {
						System.out.println("\n\n********ROUND " + MONITOR + "********");
						System.out.println("\n****Attempting to place bet on Player B****");
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PLAYER_B_XPATH))).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILL_AMOUNT_XPATH_FOR_B)))
								.sendKeys(BET_AMOUNT);
						String INITIAL_BALANCE = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(BALANCE_XPATH))).getText();
						temp_Bal = float_current_balance;
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PLACE_BETS_XPATH_FOR_B))).click();
						Thread.sleep(5000);
						String UPDATED_BALANCE = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(BALANCE_XPATH))).getText();
						System.out.println("\nCurrent Balance : " + INITIAL_BALANCE);
						System.out.println("Exposure : " + BET_AMOUNT);
						System.out.println("Balance After Exposure : " + UPDATED_BALANCE);

						if (INITIAL_BALANCE.equals(UPDATED_BALANCE)) {
							System.out.println(
									"\nbalance still not updated, please check for your bet manually, AutoBet waiting for 10 seconds before resuming again..............................");
							Thread.sleep(10000);
						} else {
							System.out.println("\n******YOUR BET HAS BEEN PLACED SUCCESSFULLY******");
							MONITOR = MONITOR + 1;
						}

						String IMPLICIT_WAIT = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(COUNTER_XPATH))).getText();
						int INT_IMPLICIT_WAIT = Integer.parseInt(IMPLICIT_WAIT);
						System.out.println("\n\nWaiting for " + (INT_IMPLICIT_WAIT + 5)
								+ " seconds for current round to finish\n\n");
						Thread.sleep((INT_IMPLICIT_WAIT + 5) * 1000);

					}
				}
			} catch (WebDriverException e) {
				retry = retry + 1;
				System.out.println(
						"\n\n\n\n****************************************EXCEPTION CAUGHT - REATTEMPTING EVERYTHING AGAIN****************************************\n\n\n\n");
				driver.close();
				driver.switchTo().window(tabs2.get(0));
				driver.navigate().refresh();
				try {
					System.out.println("Checking for logged in or logged out......");
					Thread.sleep(5000);
					// Checking for 'My Account' element, if present then logged in otherwise logged
					// out
					if (driver.findElement(By.id("accountPopup")).isDisplayed()) {
						System.out.println("\nYou are already LOGGED IN");
					}
				} catch (WebDriverException e1) {
					System.out.println("\nYou have been LOGGED OUT, trying to LOGIN now");
					// Clicking on Login button
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("/html/body/app-dashboard/div/app-header/ul/form/button")))
							.click();
					Thread.sleep(5000);
				}
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("/html/body/app-dashboard/div/div/div/ul/li[7]/a"))).click();
				Thread.sleep(5000);
				ArrayList<String> tabs3 = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs3.get(1));
//				wait.until(ExpectedConditions.elementToBeClickable(
//						By.xpath("/html/body/div/ng-component/div[1]/div/div[2]/div[1]/div/div/img"))).click();
				driver.navigate().to("https://real-game.club/#/casino-event/asia");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]/div/div[2]/ul/li/a"))).click();
				continue;
			}
		}

	}

}
