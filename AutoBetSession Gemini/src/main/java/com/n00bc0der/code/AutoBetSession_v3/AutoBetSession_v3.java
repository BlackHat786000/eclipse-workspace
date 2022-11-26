package com.n00bc0der.code.AutoBetSession_v3;

import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoBetSession_v3 {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://geminiexch.com/#/fullmarket/202202034610866/4";
		int yes_target;
		int not_target;
		String sid = "643735"; //
		String amount = "1000";
		String sname = "AS 20 Over Runs ADV";

		String back_input = "//tr[@class='fancy-quick-tr slip-back " + sid
				+ "']//input[@class='ng-untouched ng-pristine ng-valid']";
		String back_place = "//tr[@class='fancy-quick-tr slip-back " + sid
				+ "']//dl[@id='classWrap']/dd[@class='col-send']/button[@id='placeBet']";

		String lay_input = "//tr[@class='fancy-quick-tr slip-lay " + sid
				+ "']//input[@class='ng-untouched ng-pristine ng-valid']";
		String lay_place = "//tr[@class='fancy-quick-tr slip-lay " + sid
				+ "']//dl[@id='classWrap']/dd[@class='col-send']/button[@id='placeBet']";

		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver-latest.exe");
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

		WebDriverWait wait = new WebDriverWait(driver, 5);

//		JavascriptExecutor js = ((JavascriptExecutor) driver);

		driver.get(url);
		driver.manage().window().maximize();

		// LOGIN
		System.out.println("\nLogging in....\n");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[1]/input")))
				.sendKeys("Demogem");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/div[2]/input")))
				.sendKeys("Abcd1234");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/app-dashboard/div[1]/app-header/ul/form/button"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/modal-container/div/div/div/div/div[2]/div[3]/button[1]")))
				.click();
		System.out.println("\nLOGGED IN....");
		Thread.sleep(5000);

		System.out.println(
				"\n\n**************** WELCOME_TO_AUTOBET_SESSION_v3 - SET THE SESSION BOTH SIDES AS YOU WANT ****************\n\n");

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter YES : ");
		yes_target = Integer.parseInt(sc.nextLine());
		System.out.print("Enter NOT : ");
		not_target = Integer.parseInt(sc.nextLine());
		sc.close();

		String yes_achieved = "          [ Waiting.... ]";
		String not_achieved = "          [ Waiting.... ]";

		boolean Y = true;
		boolean N = true;

		while (Y || N) {

			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"/html/body/app-dashboard/div[2]/main/div/ng-component/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/span[1]")))
						.click();
				List<WebElement> listele = driver.findElements(By.id("fancy" + sid));
				String not = listele.get(0).getText();
				String yes = listele.get(1).getText();

				if (yes.contains("Ball Running") || not.contains("Ball Running")) {
					System.out.println("Ball Running");
				} else if (yes.equals("") || not.equals("")) {
					System.out.println("EMPTY_STRING");
				} else {
					int yes_runs = Integer.parseInt(yes.split("\n")[0]);
					int yes_odds = Integer.parseInt(yes.substring(yes.indexOf("\n") + 1));
					int not_runs = Integer.parseInt(not.split("\n")[0]);
					int not_odds = Integer.parseInt(not.substring(not.indexOf("\n") + 1));
					System.out.println(
							"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("****** " + sname + " ******\n");
					System.out.println("CURRENT_NOT  ::  " + not_runs + "  @  " + not_odds);
					System.out.println("CURRENT_YES  ::  " + yes_runs + "  @  " + yes_odds);
					System.out.println("TARGET_YES  ::  " + yes_target + " @ 100 " + yes_achieved);
					System.out.println("TARGET_NOT  ::  " + not_target + " @ 100 " + not_achieved);
					System.out.println(
							"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					if (yes_runs <= yes_target && Y && yes_odds == 100) {

						String initial_balance = wait
								.until(ExpectedConditions.elementToBeClickable(By
										.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong")))
								.getText();

						System.out.println(
								"\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("CURRENT_YES = " + yes_runs + " is now EQUAL to / LESS than TARGET_YES = "
								+ yes_target);
						System.out.println("NOW ATTEMPTING TO PLACE YES BET ON " + yes_runs + " RUNS");

						wait.until(ExpectedConditions.elementToBeClickable(By.linkText(yes))).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(back_input)))
								.sendKeys(Keys.chord(Keys.CONTROL, "a"), amount);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(back_place))).click();

						Thread.sleep(5000);

						String updated_balance = wait
								.until(ExpectedConditions.elementToBeClickable(By
										.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong")))
								.getText();

						if (initial_balance.equals(updated_balance)) {
							System.out.println(
									"\n[ balance still not updated, bet may not have been placed due to some reason, re-trying again ]");
							driver.navigate().refresh();
							Thread.sleep(5000);
						} else {
							System.out.println(
									"\n******************************************** Your YES bet has been placed successfully ********************************************");
							System.out.println(
									"\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

							yes_achieved = "--------> [ DONE @ " + yes_runs + " , odds - " + yes_odds + " ]";

							Y = false;

							driver.navigate().refresh();
							Thread.sleep(5000);

						}

					}

					if (not_runs >= not_target && N && not_odds == 100) {

//						js.executeScript("arguments[0].scrollIntoView();", listele.get(0));
//						Thread.sleep(2000);

						String initial_balance = wait
								.until(ExpectedConditions.elementToBeClickable(By
										.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong")))
								.getText();

						System.out.println(
								"\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("CURRENT_NOT = " + not_runs + " is now EQUAL to / MORE than TARGET_NOT = "
								+ not_target);
						System.out.println("NOW ATTEMPTING TO PLACE NOT BET ON " + not_runs + " RUNS");

						wait.until(ExpectedConditions.elementToBeClickable(By.linkText(not))).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lay_input)))
								.sendKeys(Keys.chord(Keys.CONTROL, "a"), amount);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lay_place))).click();

						Thread.sleep(5000);

						String updated_balance = wait
								.until(ExpectedConditions.elementToBeClickable(By
										.xpath("/html/body/app-dashboard/div[1]/app-header/ul/div[1]/span[1]/strong")))
								.getText();

						if (initial_balance.equals(updated_balance)) {
							System.out.println(
									"\n[ balance still not updated, bet may not have been placed due to some reason, re-trying again ]");
							driver.navigate().refresh();
							Thread.sleep(5000);
						} else {
							System.out.println(
									"\n******************************************** Your NOT bet has been placed successfully ********************************************");
							System.out.println(
									"\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

							not_achieved = "--------> [ DONE @ " + not_runs + " , odds - " + not_odds + " ]";

							N = false;

							driver.navigate().refresh();
							Thread.sleep(5000);

						}

					}

				}

			} catch (IndexOutOfBoundsException e) {
				System.out.println("\nSUSPENDED || YOUR SESSION IS NOT AVAILABLE NOW");
				driver.navigate().refresh();
				Thread.sleep(5000);
			} catch (WebDriverException e1) {
				System.out.println("\nTEMPORARILY SUSPENDED || YOUR SESSION IS NOT AVAILABLE TO BET RIGHT NOW");
				driver.navigate().refresh();
				Thread.sleep(5000);
			}

		}
		System.out.println("\n\nCongratulations !! Session book has been set both sides");
		System.out.println("\nAutoBetSession v3.0 is going to exit now, Bye Bye	:)");
		driver.quit();

	}
}