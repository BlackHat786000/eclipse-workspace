package com.n00bc0der.code.GamblerDharma;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
//import org.openqa.selenium.WebDriverException;
//import org.openqa.selenium.opera.OperaDriver;
//import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GamblerDharma {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Enter Name : ");
		String name = sc.nextLine();
		System.out.println("Enter Date : ");
		String date = sc.nextLine();
		System.out.println("Enter Month : ");
		String month = sc.nextLine();
		System.out.println("Enter Year : ");
		String year = sc.nextLine();
		System.out.println("Enter Hour : ");
		String hour = sc.nextLine();
		System.out.println("Enter Minute : ");
		String minute = sc.nextLine();
		System.out.println("Enter Country : ");
		String country = sc.nextLine();
		System.out.println("Enter City : ");
		String city = sc.nextLine();
		
		sc.close();
		
//-------------------------------------------------------------------------------------------------------------------
//									Enter Sports Contest Details Here
//-------------------------------------------------------------------------------------------------------------------
//		String name = "31st Kolkata vs Bangalore";
//		String date = "20"; // dont_put_zero_before_any_number
//		String month = "9"; // dont_put_zero_before_any_number
//		String year = "2021";
//		String hour = "18"; // 24_hour_format
//		String minute = "0"; // dont_put_zero_before_any_number (__don't put zero before zero__)
//		String country = "United Arab Emirates";
//		String city = "Abu Dhabi";
//-------------------------------------------------------------------------------------------------------------------
		PrintStream o = new PrintStream(new File("C://Users//yadav//Desktop//Gambler_Dharma//Backup//" + name + ".txt"));
		System.setOut(o);
		//PrintStream console = System.out;
		//System.setOut(console);
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Sports Contest Details :-");
		System.out.println("Name : " + name);
		System.out.println("Date : " + date + "-" + month + "-" + year);
		System.out.println("Time : " + hour + ":" + minute);
		System.out.println("Place : " + city + ", " + country + "\n");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------\n\n\n\n");
		
		JFrame frame = new JFrame();
		JProgressBar bar = new JProgressBar(0,100);
		bar.setValue(0);
		bar.setBounds(0,0,800,40);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli", Font.BOLD, 25));
		bar.setForeground(Color.green);
		bar.setBackground(Color.gray);
		frame.add(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 75);
		frame.setLayout(null);
		frame.setVisible(true);
//		int int_hour = Integer.parseInt(hour);
//		String hour2 = hour;
//		String meridian = "AM";
//		if(int_hour > 12) { hour2 = Integer.toString(int_hour % 12); meridian = "PM"; }
		
		HashBiMap<String, Integer> signs = HashBiMap.create();
		//signs.put("Ascendant", 0);
		signs.put("Aries", 1);
		signs.put("Taurus", 2);
		signs.put("Gemini", 3);
		signs.put("Cancer", 4);
		signs.put("Leo", 5);
		signs.put("Virgo", 6);
		signs.put("Libra", 7);
		signs.put("Scorpio", 8);
		signs.put("Saggitarius", 9);
		signs.put("Capricorn", 10);
		signs.put("Aquarius", 11);
		signs.put("Pisces", 12);
		
		HashBiMap<String, Integer> planets_id = HashBiMap.create();
		//HashMap<String, Integer> planets_id = new HashMap<String, Integer>();
		planets_id.put("Ascendant", 0);
		planets_id.put("Sun", 1);
		planets_id.put("Moon", 2);
		planets_id.put("Mars", 3);
		planets_id.put("Mercury", 4);
		planets_id.put("Jupiter", 5);
		planets_id.put("Venus", 6);
		planets_id.put("Saturn", 7);
		planets_id.put("Rahu", 8);
		planets_id.put("Ketu", 9);
		planets_id.put("Uranus", 10);
		planets_id.put("Neptune", 11);
		planets_id.put("Pluto", 12);
		planets_id.put("Upaketu", 13);
		
		HashMap<String, String> houses = new HashMap<String, String>();
		houses.put("Lagna Bhava", "1");
		houses.put("Dhana Bhava", "2");
		houses.put("Bratru Bhava", "3");
		houses.put("Matru Bhava", "4");
		houses.put("Putra Bhava", "5");
		houses.put("Shatru Bhava", "6");
		houses.put("Kalatra Bhava", "7");
		houses.put("Ayu Bhava", "8");
		houses.put("Bhagya Bhava", "9");
		houses.put("Rajya Bhava", "10");
		houses.put("Labha Bhava", "11");
		houses.put("Vyaya Bhava", "12");
		
		HashMap<Integer, String> lords = new HashMap<Integer, String>();
		lords.put(1, "Mars");
		lords.put(2, "Venus");
		lords.put(3, "Mercury");
		lords.put(4, "Moon");
		lords.put(5, "Sun");
		lords.put(6, "Mercury");
		lords.put(7, "Venus");
		lords.put(8, "Mars");
		lords.put(9, "Jupiter");
		lords.put(10, "Saturn");
		lords.put(11, "Saturn");
		lords.put(12, "Jupiter");
		
		HashMap<String, String> rasi = new HashMap<String, String>();
		rasi.put("Ari", "Aries");
		rasi.put("Tau", "Taurus");
		rasi.put("Gem", "Gemini");
		rasi.put("Can", "Cancer");
		rasi.put("Leo", "Leo");
		rasi.put("Vir", "Virgo");
		rasi.put("Lib", "Libra");
		rasi.put("Sco", "Scorpio");
		rasi.put("Sag", "Saggitarius");
		rasi.put("Cap", "Capricorn");
		rasi.put("Aqu", "Aquarius");
		rasi.put("Pis", "Pisces");
		
		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver.exe");
		//System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		OperaOptions options = new OperaOptions();
		//ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
		//options.addArguments("--headless");
		WebDriver driver = new OperaDriver(options);
		//WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(Exception.class);
		
		driver.navigate().to("https://www.onlinejyotish.com/free-astrology/kp/index.php"); //Thread.sleep(3000);
		driver.manage().window().maximize();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys(name);
		Select i_date = new Select(driver.findElement(By.id("day")));
		i_date.selectByValue(date);
		Select i_month = new Select(driver.findElement(By.id("month")));
		i_month.selectByValue(month);
		driver.findElement(By.id("year")).sendKeys(Keys.chord(Keys.CONTROL, "a"), year);
		Select i_hour = new Select(driver.findElement(By.id("hour")));
		i_hour.selectByValue(hour);
		Select i_minute = new Select(driver.findElement(By.id("minute")));
		i_minute.selectByValue(minute);
		Select i_country = new Select(driver.findElement(By.id("country")));
		i_country.selectByVisibleText(country);
		driver.findElement(By.id("city")).sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div[1]/form/div/div/table/tbody/tr[10]/td/div/a[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/form/div/div/table/tbody/tr[15]/td/input[3]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[6]/div[1]/h4/a"))).click();
		
		Multimap<String, String> cusps = ArrayListMultimap.create();
		for(int i=3; i<=14; i++) {
			for(int j=2; j<=3; j++) {
				String key = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[6]/div[2]/div/table[4]/tbody/tr["+i+"]/td[1]"))).getText();
				String values = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[6]/div[2]/div/table[4]/tbody/tr["+i+"]/td["+j+"]"))).getText();
				cusps.put(houses.get(key), values);
			}
		}
		
		Multimap<String, Integer> D1_Lords = ArrayListMultimap.create();
		
		String lagna_sign = (Iterables.get(cusps.get("1"), 0));
		HashBiMap<Integer, Integer> whole_sign = HashBiMap.create();
		int counter = 0;
		for(int i=1; i<=12; i++) {
		int sign = signs.get(lagna_sign) + counter;
		if(sign >= 13) { sign = sign % 12; }
		whole_sign.put(sign, i);
		D1_Lords.put(lords.get(sign), i);
		counter++;
		}
		
		Multimap<Integer, String> planets = ArrayListMultimap.create();
		for(int i=3; i<=11; i++) { String key = null;
			for(int j=2; j<=4; j++) {
				key = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[6]/div[2]/div/table[1]/tbody/tr["+i+"]/td[1]"))).getText();
				String values = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[6]/div[2]/div/table[1]/tbody/tr["+i+"]/td["+j+"]"))).getText();
				planets.put(planets_id.get(key), values);
				}
			String p_sign = Iterables.get(planets.get(planets_id.get(key)), 1);
			int p_sign_no = signs.get(p_sign);
			planets.put(planets_id.get(key), (whole_sign.get(p_sign_no)).toString());
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[1]/div[1]/h4/a"))).click();
		String latitude = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[1]/div[2]/div/table/tbody/tr[7]/td[2]"))).getText();
		String longitude = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[1]/div[2]/div/table/tbody/tr[8]/td[2]"))).getText();
		String timezone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[2]/div[1]/div[2]/div/table/tbody/tr[9]/td[2]"))).getText();
		
		bar.setValue(10);
		
		driver.navigate().to("https://www.rahasyavedicastrology.com/rva-software/");
		js.executeScript("window.scrollBy(0,500)");
		//Thread.sleep(500);
		
		driver.findElement(By.id("m-name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), name);
		
		Select dates = new Select(driver.findElement(By.id("m-date")));
		dates.selectByValue(date);
		Select months = new Select(driver.findElement(By.id("m-month")));
		months.selectByValue(month);
		Select years = new Select(driver.findElement(By.id("m-year")));
		years.selectByValue(year);
		
		Select hours = new Select(driver.findElement(By.id("m-hour")));
		hours.selectByValue(hour);
		Select minutes = new Select(driver.findElement(By.id("m-minute")));
		minutes.selectByValue(minute);
		Select seconds = new Select(driver.findElement(By.id("m-seconds")));
		seconds.selectByValue("0");
		
		driver.findElement(By.id("m-advanced-geo-option")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,100)");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("m-hr-lat"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), latitude);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div/div/section[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/form/div[7]/div/div/div[1]/input"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), latitude);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("m-hr-lon"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), longitude);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("m-hr-tzone"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), timezone);
		
		driver.findElement(By.id("m-submit-hr-form")).click();
		Thread.sleep(7000);
		
		String asc_csl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[1]/td[6]"))).getText();
		String dsc_csl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[7]/td[6]"))).getText();
		int ASC_SUB = 0;
		int DSC_SUB = 0;
		
		Multimap<Integer, String> house_view = ArrayListMultimap.create();
		for(int i=1; i<=12; i++) {
			for(int j=1; j<=4; j++) {
			String s = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[2]/div/div[1]/div/table/tbody/tr["+i+"]/td["+j+"]"))).getText();
			if( (s.equals("Uranus")) || (s.equals("Neptune")) || (s.equals("Pluto")) ) {
			house_view.put(i, "");
			} else { house_view.put(i, s); }
		}
		}
		
		//	Uranus (10) , Neptune (11) , Pluto (12), Upaketu (13) - D1
		String ur = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[10]/th"))).getText();
		if(ur.contains("(R)")) { planets.put(10, "(R)"); } else { planets.put(10, ""); }
		String ur_sign = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[10]/td[1]"))).getText();
		planets.put(10, rasi.get(ur_sign));
		String ur_degree = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[10]/td[2]"))).getText();
		if(ur_degree.charAt(1) == ':') { ur_degree = "0"+ur_degree; if(ur_degree.charAt(4) == ':') { ur_degree = ur_degree.substring(0, 3)+ "0" +ur_degree.substring(3); } }
		if(ur_degree.charAt(4) == ':') { ur_degree = ur_degree.substring(0, 3)+ "0" +ur_degree.substring(3); }
		planets.put(10, ur_degree);
		String ur_house = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[10]/td[3]"))).getText();
		planets.put(10, ur_house);
		
		String nep = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[11]/th"))).getText();
		if(nep.contains("(R)")) { planets.put(11, "(R)"); } else { planets.put(11, ""); }
		String nep_sign = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[11]/td[1]"))).getText();
		planets.put(11, rasi.get(nep_sign));
		String nep_degree = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[11]/td[2]"))).getText();
		if(nep_degree.charAt(1) == ':') { nep_degree = "0"+nep_degree; if(nep_degree.charAt(4) == ':') { nep_degree = nep_degree.substring(0, 3)+ "0" +nep_degree.substring(3); } }
		if(nep_degree.charAt(4) == ':') { nep_degree = nep_degree.substring(0, 3)+ "0" +nep_degree.substring(3); }
		planets.put(11, nep_degree);
		String nep_house = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[11]/td[3]"))).getText();
		planets.put(11, nep_house);
		
		String pluto = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[12]/th"))).getText();
		if(pluto.contains("(R)")) { planets.put(12, "(R)"); } else { planets.put(12, ""); }
		String pluto_sign = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[12]/td[1]"))).getText();
		planets.put(12, rasi.get(pluto_sign));
		String pluto_degree = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[12]/td[2]"))).getText();
		if(pluto_degree.charAt(1) == ':') { pluto_degree = "0"+pluto_degree; if(pluto_degree.charAt(4) == ':') { pluto_degree = pluto_degree.substring(0, 3)+ "0" +pluto_degree.substring(3); } }
		if(pluto_degree.charAt(4) == ':') { pluto_degree = pluto_degree.substring(0, 3)+ "0" +pluto_degree.substring(3); }
		planets.put(12, pluto_degree);
		String pluto_house = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr[12]/td[3]"))).getText();
		planets.put(12, pluto_house);
		
		planets.put(13, "");
		String sun_sign = Iterables.get(planets.get(1), 1);
		int sun_sign_no = signs.get(sun_sign) - 1;
		String sun_house_no = Iterables.get(planets.get(1), 3);
		int int_sun_house_no = Integer.parseInt(sun_house_no) - 1; 
		planets.put(13, signs.inverse().get(sun_sign_no));
		planets.put(13, Iterables.get(planets.get(1), 2));
		planets.put(13, Integer.toString(int_sun_house_no));
		
		bar.setValue(20);
		
//----------------------------------------------------------------------------------------------------------------------		
		//planets.get(1), 0 -> Sun retro/combust
		//planets.get(1), 1 -> Sun sign
		//planets.get(1), 2 -> Sun degree
		//planets.get(1), 3 -> Sun house_no
		//System.out.println(Iterables.get(planets.get(1), 1));
		
		// cusps.get(12), 0 -> 12th cusp sign
		// cusps.get(12), 1 -> 12th cusp degree
		//System.out.println(Iterables.get(cusps.get("12"), 1));
//----------------------------------------------------------------------------------------------------------------------
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("(Module 1)					D1 Cuspal Strength : ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		for(int i=1; i<=13; i++) {
			
			String planet_house_no = Iterables.get(planets.get(i), 3);
			//System.out.println(planet_house_no);    
			//int int_planet_house_no = Integer.parseInt(planet_house_no);
			String planet_sign = Iterables.get(planets.get(i), 1);
			String cusp_sign = Iterables.get(cusps.get(planet_house_no), 0);
			
			String planet_DM = Iterables.get(planets.get(i), 2);
			int planet_D = Integer.parseInt(planet_DM.substring(0,2));
			int planet_M = Integer.parseInt(planet_DM.substring(3,5));
			int planet_minutes = planet_D * 60 + planet_M;
			
			String cusp_DM = Iterables.get(cusps.get(planet_house_no), 1);
			int cusp_D = Integer.parseInt(cusp_DM.substring(0,2));
			int cusp_M = Integer.parseInt(cusp_DM.substring(3,5));
			int cusp_minutes = cusp_D * 60 + cusp_M;
			
			int orb = cusp_minutes - planet_minutes;
			
			String planet_rc = Iterables.get(planets.get(i), 0);
			
			//&& ( planet_house_no == "1" || planet_house_no == "7" || planet_house_no == "10" || planet_house_no == "4" || planet_house_no == "6" || planet_house_no == "12" )
			if(cusp_sign.equals(planet_sign)) {
				if( ( Math.abs(orb) <= 150 ) && ( planet_house_no.equals("1") || planet_house_no.equals("7") || planet_house_no.equals("10") || planet_house_no.equals("4") || planet_house_no.equals("6") || planet_house_no.equals("12") ) ) {
				System.out.println("\n****************************************************************************************************************");	
				System.out.println("Lord of "+D1_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" ("+planet_sign+") is on "+planet_house_no+" cusp within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"'");
				System.out.println("****************************************************************************************************************\n");
			} else {
				System.out.println("Lord of "+D1_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" ("+planet_sign+") is on "+planet_house_no+" cusp but within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"' (out of 2°30' orb)");
			}
			}
			
			boolean show = true;
			for(int j=1; j<=12; j++) {
				
					String string_j = Integer.toString(j);
					String cuspal_sign = Iterables.get(cusps.get(string_j), 0);
					String cuspal_DM = Iterables.get(cusps.get(string_j), 1);
					int cuspal_D = Integer.parseInt(cuspal_DM.substring(0,2));
					int cuspal_M = Integer.parseInt(cuspal_DM.substring(3,5));
					int cuspal_minutes = cuspal_D * 60 + cuspal_M;
					
					int orb2 = cuspal_minutes - planet_minutes;
				    
				 if( (cuspal_sign.equals(planet_sign)) && !(string_j.equals(planet_house_no)) && (Math.abs(orb2) <= 150) && ( planet_house_no.equals("1") || planet_house_no.equals("7") || planet_house_no.equals("10") || planet_house_no.equals("4") || planet_house_no.equals("6") || planet_house_no.equals("12") ) ) {
					 if(show) {
							System.out.println("Stolen Cusps : ");
							System.out.println("============="); show = false; }
					 System.out.println("Lord of "+D1_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" ("+planet_sign+") is on "+j+" cusp but moved to "+planet_house_no+" house ");
				 }
			}
		}
		
		bar.setValue(30);
		
//---------------------------------------------------------------------------------------------------------------------
		System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("(Module 2)					D9 Cuspal Strength & D9 Combo : ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		driver.navigate().to("https://www.cosmicsquares.com/free-horoscope/navamsa-chart");
		
		//js.executeScript("window.scrollBy(0,1500)");
		
		Select n_year = new Select(driver.findElement(By.id("birthyear")));
		n_year.selectByValue(year);
		
		Select n_month = new Select(driver.findElement(By.id("birthmonth")));
		if(Integer.parseInt(month) < 10) {n_month.selectByValue("0"+month);} else {n_month.selectByValue(month);}
		
		Select n_date = new Select(driver.findElement(By.id("birthday")));
		if(Integer.parseInt(date) < 10) {n_date.selectByValue("0"+date);} else {n_date.selectByValue(date);}
		
		Select n_hour = new Select(driver.findElement(By.id("birthhour")));
		if(Integer.parseInt(hour) < 10) {n_hour.selectByValue("0"+hour);} else {n_hour.selectByValue(hour);}
		
		Select n_minute = new Select(driver.findElement(By.id("birthminute")));
		if(Integer.parseInt(minute) < 10) {n_minute.selectByValue("0"+minute);} else {n_minute.selectByValue(minute);}
		
		Select n_country = new Select(driver.findElement(By.id("birthcountry")));
		n_country.selectByVisibleText(country);
		
		driver.findElement(By.id("birthlocation")).sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
		Thread.sleep(5000);
		driver.findElement(By.id("birthlocation")).sendKeys(Keys.DOWN, Keys.RETURN);
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("form-submit-birth")).click();
		
		Thread.sleep(5000);
		
		// 0 -> degree , 1 -> sign
		Multimap<Integer, String> navamsa = ArrayListMultimap.create();
		for(int i=1; i<=10; i++) {
			for(int j=3; j<=4; j++) {
				String key = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[3]/div[2]/section/div[1]/div/div[4]/div[2]/div[2]/table/tbody/tr["+i+"]/td[1]/div/p")).getText();
				String values = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[3]/div[2]/section/div[1]/div/div[4]/div[2]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div/p")).getText();
				navamsa.put(planets_id.get(key.trim()), values);
			}
		} bar.setValue(40); Thread.sleep(1000);
		
//-------------------------------------------------------------------------------------------------------------------
		// Calculation of Navamsa for Uranus , Neptune, Pluto, Upaketu
		for(int i=10; i<=13; i++) {
			
			String planet_sign = Iterables.get(planets.get(i), 1);
			int planet_sign_no = signs.get(planet_sign);
			int nplanet_sign_no;
			
			String planet_DM = Iterables.get(planets.get(i), 2);
			int planet_D = Integer.parseInt(planet_DM.substring(0,2));
			int planet_M = Integer.parseInt(planet_DM.substring(3,5));
			int planet_minutes = planet_D * 60 + planet_M;
			int n = (int) Math.ceil(planet_minutes / 200.0) - 1;
			
		//	Movable Signs
		if(planet_sign.equals("Aries") || planet_sign.equals("Cancer") || planet_sign.equals("Libra") || planet_sign.equals("Capricorn")) {
			nplanet_sign_no = planet_sign_no + n;
			if(nplanet_sign_no > 12) { nplanet_sign_no = nplanet_sign_no % 12; }
		} // Fixed Signs
		else if(planet_sign.equals("Taurus") || planet_sign.equals("Leo") || planet_sign.equals("Scorpio") || planet_sign.equals("Aquarius")) {
			nplanet_sign_no = planet_sign_no + 8 + n;
			if(nplanet_sign_no > 12) { nplanet_sign_no = nplanet_sign_no % 12; }
		} else { // Dual Signs
			nplanet_sign_no = planet_sign_no + 4 + n;
			if(nplanet_sign_no > 12) { nplanet_sign_no = nplanet_sign_no % 12; }
		}
		
		int navamsa_minutes = (planet_minutes % 200) * 9;
		String navmsa_degree = (navamsa_minutes/60)+":"+(navamsa_minutes%60)+"~";
		//System.out.println(navmsa_degree);
		if(navmsa_degree.charAt(1) == ':') { navmsa_degree = "0"+navmsa_degree; if(navmsa_degree.charAt(4) == '~') { navmsa_degree = navmsa_degree.substring(0, 3)+ "0" +navmsa_degree.substring(3); } }
		if(navmsa_degree.charAt(4) == '~') { navmsa_degree = navmsa_degree.substring(0, 3)+ "0" +navmsa_degree.substring(3); }
		navmsa_degree = navmsa_degree.replace(":","° ");
		navmsa_degree = navmsa_degree.replace("~","'");
		navamsa.put(i, navmsa_degree);
		navamsa.put(i, signs.inverse().get(nplanet_sign_no));
		
		}
		
		bar.setValue(50); Thread.sleep(1000);
		
//-------------------------------------------------------------------------------------------------------------------
		
		Multimap<String, Integer> D9_Lords = ArrayListMultimap.create();
		
		//System.out.println(navamsa);
		String navamsa_lagna_sign = Iterables.get(navamsa.get(0), 1);
		HashBiMap<Integer, Integer> navamsa_whole_sign = HashBiMap.create();
		int n_counter = 0;
		for(int i=1; i<=12; i++) {
		int sign = signs.get(navamsa_lagna_sign) + n_counter;
		if(sign >= 13) { sign = sign % 12; }
		navamsa_whole_sign.put(i, sign);
		D9_Lords.put(lords.get(sign), i);
		n_counter++;
		}
		//System.out.println(navamsa_whole_sign);
		int navamsa_dsc_sign = navamsa_whole_sign.get(7);
		int navamsa_mc_sign = navamsa_whole_sign.get(10);
		int navamsa_ic_sign = navamsa_whole_sign.get(4);
		
		int navamsa_asc_D = Integer.parseInt(Iterables.get(navamsa.get(0), 0).substring(0,2));
		int navamsa_asc_M = Integer.parseInt(Iterables.get(navamsa.get(0), 0).substring(4,6));
		int navamsa_asc_minutes = navamsa_asc_D * 60 + navamsa_asc_M;
		//System.out.println(navamsa_asc_minutes);
		
		for(int i=1; i<=13; i++) {
			
		int nplanet_D = Integer.parseInt(Iterables.get(navamsa.get(i), 0).substring(0,2));
		int nplanet_M = Integer.parseInt(Iterables.get(navamsa.get(i), 0).substring(4,6));
		int nplanet_minutes = nplanet_D * 60 + nplanet_M;
		//System.out.println(nplanet_minutes);
		int orb = navamsa_asc_minutes - nplanet_minutes;
		
		String planet_rc = Iterables.get(planets.get(i), 0);
		
		String navamsa_planet_sign = Iterables.get(navamsa.get(i), 1);
		int navamsa_planet_sign_no = signs.get(navamsa_planet_sign); 
		if(navamsa_lagna_sign.equals(navamsa_planet_sign)) {
			if(Math.abs(orb) <= 150) {
				System.out.println("\n****************************************************************************************************************");
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" is in D9 Ascendant within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"'");
				System.out.println("****************************************************************************************************************\n");
			} else {
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" is in D9 Ascendant but within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"' (out of 2°30' orb)");
			}
		}
		if(navamsa_dsc_sign == navamsa_planet_sign_no) {
			if(Math.abs(orb) <= 150) {
				System.out.println("\n****************************************************************************************************************");
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" is in D9 Dscendant within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"'");
				System.out.println("****************************************************************************************************************\n");
			} else {
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" is in D9 Dscendant but within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"' (out of 2°30' orb)");
			}
		}
		if(navamsa_mc_sign == navamsa_planet_sign_no) {
			if(Math.abs(orb) <= 150) {
				System.out.println("\n****************************************************************************************************************");
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+ " is in D9 10th within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"'");
				System.out.println("****************************************************************************************************************\n");
			} else {
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" is in D9 10th but within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"' (out of 2°30' orb)");
			}
		}
		if(navamsa_ic_sign == navamsa_planet_sign_no) {
			if(Math.abs(orb) <= 150) {
				System.out.println("\n****************************************************************************************************************");
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" is in D9 4th within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"'");
				System.out.println("****************************************************************************************************************\n");
			} else {
				System.out.println("Lord of "+D9_Lords.get(planets_id.inverse().get(i))+" house "+planets_id.inverse().get(i)+planet_rc+" is in D9 4th but within orb of "+(orb/60)+"°"+Math.abs(orb%60)+"' (out of 2°30' orb)");
			}
		}
		
		} bar.setValue(60); Thread.sleep(1000);
		
//-------------------------------------------------------------------------------------------------------------------
		
		System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("(Module 3)					The Sublord Technique : ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		
		//System.out.println(house_view);
		for(int i=1; i<=12; i++) {
			
			if((i==1 || i==2 || i==3 || i==6 || i==10 || i==11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl)))) {
				ASC_SUB = ASC_SUB + 4; }
			else if((i==4 || i==5 || i==7 || i==8 || i==9 || i==12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl)))) {
				ASC_SUB = ASC_SUB - 4; }
			else if((i==1 || i==2 || i==3 || i==6 || i==10 || i==11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
					if(Iterables.get(house_view.get(i), 2).contains(asc_csl)) { ASC_SUB = ASC_SUB + 4; } 
					else if(Iterables.get(house_view.get(i), 3).contains(asc_csl)) { ASC_SUB = ASC_SUB + 2; }
			}
			else if((i==4 || i==5 || i==7 || i==8 || i==9 || i==12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
				if(Iterables.get(house_view.get(i), 2).contains(asc_csl)) { ASC_SUB = ASC_SUB - 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(asc_csl)) { ASC_SUB = ASC_SUB - 2; }
		}
			else {
				int cnt = 4;
				for(int j=0; j<=3; j++) {
				String s = Iterables.get(house_view.get(i), j);
				
				if((i==1 || i==2 || i==3 || i==6 || i==10 || i==11) && (s.contains(asc_csl))) {
						ASC_SUB = ASC_SUB + cnt;
			} else if((i==4 || i==5 || i==7 || i==8 || i==9 || i==12) && (s.contains(asc_csl))) {
						ASC_SUB = ASC_SUB - cnt;
			}
				cnt = cnt - 1;
			}
			}
			
		}
		
		System.out.println("1st CSL : "+asc_csl+" = "+ASC_SUB);
		
		for(int i=1; i<=12; i++) {
			
			if((i==1 || i==2 || i==3 || i==6 || i==10 || i==11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl))))) {
				DSC_SUB = DSC_SUB - 4; }
			else if((i==4 || i==5 || i==7 || i==8 || i==9 || i==12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl))))) {
				DSC_SUB = DSC_SUB + 4; }
			else if((i==1 || i==2 || i==3 || i==6 || i==10 || i==11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
					if(Iterables.get(house_view.get(i), 2).contains(dsc_csl)) { DSC_SUB = DSC_SUB - 4; } 
					else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl)) { DSC_SUB = DSC_SUB - 2;}
			}
			else if((i==4 || i==5 || i==7 || i==8 || i==9 || i==12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if(Iterables.get(house_view.get(i), 2).contains(dsc_csl)) { DSC_SUB = DSC_SUB + 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl)) { DSC_SUB = DSC_SUB + 2;}
		}
			else {
				int cnt = 4;
				for(int j=0; j<=3; j++) {
				String s = Iterables.get(house_view.get(i), j);
				
				if((i==1 || i==2 || i==3 || i==6 || i==10 || i==11) && (s.contains(dsc_csl))) {
						DSC_SUB = DSC_SUB - cnt;
			} else if((i==4 || i==5 || i==7 || i==8 || i==9 || i==12) && (s.contains(dsc_csl))) {
						DSC_SUB = DSC_SUB + cnt;
			}
				cnt = cnt - 1;
			}
			}
			
		}
	System.out.println("7th CSL : "+dsc_csl+" = "+DSC_SUB); bar.setValue(70); Thread.sleep(1000);
	
//-------------------------------------------------------------------------------------------------------------------

	System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("(Module 4)					Victory House Technique : ");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
				
			float ASC_VHQ = 0;
			float DSC_VHQ = 0;
			
			String Mars = Iterables.get(planets.get(3), 3);
						if(Mars.equals("1") || Mars.equals("3") || Mars.equals("6") || Mars.equals("10") || Mars.equals("11")) {
							ASC_VHQ = (float) (ASC_VHQ + 3);
						} else if(Mars.equals("7") || Mars.equals("9") || Mars.equals("12") || Mars.equals("4") || Mars.equals("5")) {
							DSC_VHQ = (float) (DSC_VHQ + 3);
						}
						
			String Saturn = Iterables.get(planets.get(7), 3);
			if(Saturn.equals("1") || Saturn.equals("3") || Saturn.equals("6") || Saturn.equals("10") || Saturn.equals("11")) {
				ASC_VHQ = (float) (ASC_VHQ + 2.5);
			} else if(Saturn.equals("7") || Saturn.equals("9") || Saturn.equals("12") || Saturn.equals("4") || Saturn.equals("5")) {
				DSC_VHQ = (float) (DSC_VHQ + 2.5);
			}
			
			String Sun = Iterables.get(planets.get(1), 3);
			if(Sun.equals("1") || Sun.equals("3") || Sun.equals("6") || Sun.equals("10") || Sun.equals("11")) {
				ASC_VHQ = (float) (ASC_VHQ + 2.5);
			} else if(Sun.equals("7") || Sun.equals("9") || Sun.equals("12") || Sun.equals("4") || Sun.equals("5")) {
				DSC_VHQ = (float) (DSC_VHQ + 2.5);
			}
			
			String Rahu = Iterables.get(planets.get(8), 3);
			if(Rahu.equals("1") || Rahu.equals("3") || Rahu.equals("6") || Rahu.equals("10") || Rahu.equals("11")) {
				ASC_VHQ = (float) (ASC_VHQ + 2);
			} else if(Rahu.equals("7") || Rahu.equals("9") || Rahu.equals("12") || Rahu.equals("4") || Rahu.equals("5")) {
				DSC_VHQ = (float) (DSC_VHQ + 2);
			}
			
			System.out.println("Ascendant (ASC) : " + ASC_VHQ);
			System.out.println("Dscendant (DSC) : " + DSC_VHQ);
			bar.setValue(80); Thread.sleep(1000);
			
//-------------------------------------------------------------------------------------------------------------------
			
			System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("(Module 5)					SKY / PKY : ");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			
			String Venus = Iterables.get(planets.get(6), 3);
			String Jupiter = Iterables.get(planets.get(5), 3);
			String Mercury = Iterables.get(planets.get(4), 3);
			String Ketu = Iterables.get(planets.get(9), 3);
			
			if( ( Venus.equals("2") || Jupiter.equals("2") || Mercury.equals("2") ) && ( Venus.equals("12") || Jupiter.equals("12") || Mercury.equals("12") ) ) {
				System.out.println("Shubha Kartari Yoga forming on Ascendant (1st House)");
			}
			
			if( ( Venus.equals("6") || Jupiter.equals("6") || Mercury.equals("6") ) && ( Venus.equals("8") || Jupiter.equals("8") || Mercury.equals("8") ) ) {
				System.out.println("Shubha Kartari Yoga forming on Dscendant (7th House)");
			}
			
			if( ( Saturn.equals("2") || Mars.equals("2") || Sun.equals("2") || Ketu.equals("2") ) && ( Saturn.equals("12") || Mars.equals("12") || Sun.equals("12") || Ketu.equals("12") ) ) {
				System.out.println("Paap Kartari Yoga forming on Ascendant (1st House)");
			}
			
			if( ( Saturn.equals("6") || Mars.equals("6") || Sun.equals("6") || Ketu.equals("6") ) && ( Saturn.equals("8") || Mars.equals("8") || Sun.equals("8") || Ketu.equals("8") ) ) {
				System.out.println("Paap Kartari Yoga forming on Dscendant (7th House)");
			} bar.setValue(90); Thread.sleep(1000);
			
//-------------------------------------------------------------------------------------------------------------------
			
			System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("(Module 6)					Nakshatra Tara (Fixed Star) : ");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			
			for(int i=1; i<=12; i++) {
				String cusp_sign = Iterables.get(cusps.get(Integer.toString(i)), 0);
				String cusp_degree = Iterables.get(cusps.get(Integer.toString(i)), 1);
				
				int cusp_D = Integer.parseInt(cusp_degree.substring(0,2));
				int cusp_M = Integer.parseInt(cusp_degree.substring(3,5));
				int cusp_minutes = cusp_D * 60 + cusp_M;
				
				int regulus_orb = 360 - cusp_minutes;
				int pollux_orb = 1770 - cusp_minutes;
				int zuben_orb = 1260 - cusp_minutes;
				int bettelguese_orb = 300 - cusp_minutes;
				int spica_orb = 1795 - cusp_minutes;
				int denebola_orb = 1665 - cusp_minutes;
				int algol_orb = 120 - cusp_minutes;
				int antares_orb = 960 - cusp_minutes;
				int purva_orb = 1050 - cusp_minutes;
				int krittika_orb = 360 - cusp_minutes;
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Leo") && Math.abs(regulus_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Regulus (6° Leo) within orb of "+(regulus_orb/60)+"°"+Math.abs(regulus_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Gemini") && Math.abs(pollux_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Pollux (29° 30' Gemini) within orb of "+(pollux_orb/60)+"°"+Math.abs(pollux_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Libra") && Math.abs(zuben_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Zuben Elgenubi (21° Libra) within orb of "+(zuben_orb/60)+"°"+Math.abs(zuben_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Gemini") && Math.abs(bettelguese_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Bettelguese (5° Gemini) within orb of "+(bettelguese_orb/60)+"°"+Math.abs(bettelguese_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Virgo") && Math.abs(spica_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Spica (29° 55' Virgo) within orb of "+(spica_orb/60)+"°"+Math.abs(spica_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Leo") && Math.abs(denebola_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Denebola (27° 45' Leo) within orb of "+(denebola_orb/60)+"°"+Math.abs(denebola_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Taurus") && Math.abs(algol_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Algol (2° Taurus) within orb of "+(algol_orb/60)+"°"+Math.abs(algol_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Scorpio") && Math.abs(antares_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Antares (16° Scorpio) within orb of "+(antares_orb/60)+"°"+Math.abs(antares_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Leo") && Math.abs(purva_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Purva Phalguni (17° 30' Leo) within orb of "+(purva_orb/60)+"°"+Math.abs(purva_orb%60)+"'");
				}
				
				if( ( i==1 || i==7 || i==10 || i==4 || i==6 || i==12 ) && ( cusp_sign.equals("Taurus") && Math.abs(krittika_orb) <= 120 ) ) {
					System.out.println(i+" cusp is on The Krittika (6° Taurus) within orb of "+(krittika_orb/60)+"°"+Math.abs(krittika_orb%60)+"'");
				}
				
			} bar.setValue(100); driver.quit(); 
			bar.setString("Done! :)");
			
//-------------------------------------------------------------------------------------------------------------------
			Document document = new Document(PageSize.A4);
			File f = new File("C://Users//yadav//Desktop//Gambler_Dharma//PDF//" + name + ".pdf");
			try
            {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
				document.open();
				document.add(new Paragraph("Gambler's Dharma - Prediction Using Vedic Astrology"));
               //Text file Data
               try (BufferedReader br = new BufferedReader(new FileReader("C://Users//yadav//Desktop//Gambler_Dharma//" + name + ".txt"))) {
                      
                      String sCurrentLine;

                      while ((sCurrentLine = br.readLine()) != null) {
                          document.add(new Paragraph(sCurrentLine));
                      }

                      } catch (IOException exception) {
                      exception.printStackTrace();
                  }
               
               document.close();
               writer.close();
               
            } catch (DocumentException exception)
            {
               exception.printStackTrace();
            } catch (FileNotFoundException exception)
            {
               exception.printStackTrace();
            }
	}}
