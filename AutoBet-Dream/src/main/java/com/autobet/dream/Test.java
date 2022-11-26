package com.autobet.dream;

public class Test {

	public static void main(String[] args) {
		
//		int punter_lay_amount = 100;
//		
//		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver.exe");
//		OperaOptions options = new OperaOptions();
//		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
//		WebDriver driver = new OperaDriver(options);
//		
//		driver.get("https://dreamipl9.in/Cricket/22/4/25948/31064652");
//		
//		String home_team_name = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_team01"))).getText();
//		String away_team_name = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_team02"))).getText(); 
//		
//		System.out.println("****Welcome To Punter's AutoBet - set book both sides at desired odds****");
//		System.out.println("\nMonitoring "+home_team_name+" vs "+away_team_name+"\n");
//		
//		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("STRate_K1"))).click();
//		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("txt_price"))).sendKeys(Integer.toString(punter_lay_amount));
//		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("btnbetnow"))).click();
		
//		String str = "PAK 10 Over Runs\n87\n100\n89\n100"+"\n-";
//		System.out.println(str);
		
//		System.out.println(str.split("\n")[0]);
//		System.out.println(str.split("\n")[1]);
//		System.out.println(str.split("\n")[2]);
//		System.out.println(str.split("\n")[3]);
//		System.out.println(str.split("\n")[4]);
		
		String s = "R Sharma run(IND vs NZ)adv";
		// r_sharma_runind_vs_nzadv
		System.out.println(s.toLowerCase().replaceAll("\\s", "_").replaceAll("[()]", ""));
		
}}