package com.n00bc0der.code.AstroSportsReport;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

public class Moon_AstroSage {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		System.out.println("**************** KP Moon Event Flow Automated Report ****************\n\n");
		
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Enter Name : ");
		String name = sc.nextLine();
		System.out.print("Enter Date : ");
		String dates = sc.nextLine();
		System.out.print("Enter Month : ");
		String months = sc.nextLine();
		System.out.print("Enter Year : ");	
		String years = sc.nextLine();			if(years.equals("21") || years.equals("22")) { years = "20" + years; }
		System.out.print("Enter Hour : ");
		String hour = sc.nextLine();
		System.out.print("Enter Minute : ");
		String minute = sc.nextLine();
		System.out.print("Enter Stadium : ");
		String place = sc.nextLine();
		System.out.print("Enter till_hour : ");
		String Till_Hour = sc.nextLine();
		System.out.print("Enter till_minute : ");
		String Till_Minute = sc.nextLine();
		System.out.print("Enter Interval : ");
		String Interval = sc.nextLine();
		System.out.print("Enter IST_HOUR : ");
		String IST_hour = sc.nextLine();
		System.out.print("Enter IST_MINUTE : ");
		String IST_minute = sc.nextLine();
		
		sc.close();
		
//-------------------------------------------------------------------------------------------------------------------
//		STADIUM DATABASE
//-------------------------------------------------------------------------------------------------------------------

Multimap<String, String> Stadiums = ArrayListMultimap.create();

// Dubai Cricket Stadium
Stadiums.put("DCS", "25 02 N");	
Stadiums.put("DCS", "55 13 E");
Stadiums.put("DCS", "4");

// Sheikh Zayed Cricket Stadium
Stadiums.put("SZCS", "24 23 N");	
Stadiums.put("SZCS", "54 32 E");
Stadiums.put("SZCS", "4");

// Sharjah Cricket Stadium
Stadiums.put("SCS", "25 19 N");	
Stadiums.put("SCS", "55 25 E");
Stadiums.put("SCS", "4");

// Old Trafford
Stadiums.put("OTS", "53 27 N");	
Stadiums.put("OTS", "02 17 W");
Stadiums.put("OTS", "1");

// Stamford Bridge
Stadiums.put("SBS", "51 28 N");	
Stadiums.put("SBS", "00 11 E");
Stadiums.put("SBS", "1");

//Goodison Park
Stadiums.put("GPS", "53 26 N");	
Stadiums.put("GPS", "02 57 W");
Stadiums.put("GPS", "1");

// Elland Road
Stadiums.put("ERS", "53 46 N");	
Stadiums.put("ERS", "01 34 W");
Stadiums.put("ERS", "1");

// King Power Stadium
Stadiums.put("KPS", "52 37 N");	
Stadiums.put("KPS", "01 08 W");
Stadiums.put("KPS", "1");

// Vicarage Road
Stadiums.put("VRS", "51 38 N");	
Stadiums.put("VRS", "00 24 E");
Stadiums.put("VRS", "1");

// Brentford Community Stadium
Stadiums.put("BCS", "51 29 N");	
Stadiums.put("BCS", "00 17 E");
Stadiums.put("BCS", "1");

//Santiago Bernabeu
Stadiums.put("SBS", "40 27 N");	
Stadiums.put("SBS", "03 41 W");
Stadiums.put("SBS", "2");

String latitude = Iterables.get(Stadiums.get(place), 0);
String longitude = Iterables.get(Stadiums.get(place), 1);
String timezone = Iterables.get(Stadiums.get(place), 2);

String LongDeg = longitude.substring(0,2);
String LongMin = longitude.substring(3,5);
String LongEW = longitude.substring(6);

String LatDeg = latitude.substring(0,2);
String LatMin = latitude.substring(3,5);
String LatNS = latitude.substring(6);




//-------------------------------------------------------------------------------------------------------------------
		
//-------------------------------------------------------------------------------------------------------------------	
//		String name = "test";
//		String dates = "23";
//		String months = "9";
//		String years = "2021";
//		int hours = 18;
//		int minutes = 0;
//		String place = "Abu Dhabi";
//		int till_hour = 19;
//		int till_minute = 10;
//		int interval = 5;
//		int IST_hours = 19;
//		int IST_minutes = 30;
		
		
		int hours = Integer.parseInt(hour);
		int minutes = Integer.parseInt(minute);
		
		int till_hour = Integer.parseInt(Till_Hour);
		int till_minute = Integer.parseInt(Till_Minute);
		int interval = Integer.parseInt(Interval);
		
		int IST_hours = Integer.parseInt(IST_hour);
		int IST_minutes = Integer.parseInt(IST_minute);
		
		int tmp_hours = hours;
		int tmp_minutes = minutes;
		
		int tmp_IST_hours = IST_hours;
		int tmp_IST_minutes = IST_minutes;
//-------------------------------------------------------------------------------------------------------------------
		
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Local\\Google\\Chrome\\User Data");
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		
		System.out.println("\n\n****************************************** KP Report is being generated as per Moon Muhurata. This may take several minutes ******************************************");
		
		PrintStream o = new PrintStream(new File("C://Users//yadav//Desktop//KP_Astro_Sports//" + name + ".txt"));
		PrintStream console = System.out;
		System.setOut(o);
		
//		@SuppressWarnings("deprecation")
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
//				.pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Sports Contest Details :-");
		System.out.println("Name : " + name);
		System.out.println("Date : " + dates + "-" + months + "-" + years);
		System.out.println("Time : " + hours + ":" + minutes + " till " + till_hour + ":" + till_minute);
		System.out.println("Place : " + place);
		System.out.println("Interval : " + interval + " minutes\n");
		
		JFrame frame = new JFrame();
		JProgressBar bar = new JProgressBar(0,100);
		bar.setValue(0);
		bar.setBounds(0,0,420,50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli", Font.BOLD, 25));
		bar.setForeground(Color.green);
		bar.setBackground(Color.gray);
		frame.add(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setAlwaysOnTop( true );
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
//-------------------------------------------------------------------------------------------------------------------		
		for( ; minutes <=59 ; minutes = minutes + interval) {
			
			try {
		driver.navigate().to("https://www.astrosage.com/freekphorary/instantchart.asp"); 
		driver.manage().window().maximize();
		
		driver.findElement(By.id("Name")).sendKeys(name);
		driver.findElement(By.id("Day")).sendKeys(dates);
		driver.findElement(By.id("Month")).sendKeys(months);
		driver.findElement(By.id("Year")).sendKeys(years);
		driver.findElement(By.id("Hrs")).sendKeys(Integer.toString(hours));
		driver.findElement(By.id("Min")).sendKeys(Integer.toString(minutes));
		driver.findElement(By.id("Sec")).sendKeys("0");
		driver.findElement(By.id("place")).sendKeys(place);
		//Thread.sleep(5000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("place"))).sendKeys(Keys.DOWN, Keys.RETURN);

		driver.findElement(By.id("aTag")).click();
		
		driver.findElement(By.id("LongDeg")).sendKeys(LongDeg);
		driver.findElement(By.id("LongMin")).sendKeys(LongMin);
		Select longew = new Select(driver.findElement(By.id("LongEW")));
		longew.selectByValue(LongEW);
		
		driver.findElement(By.id("LatDeg")).sendKeys(LatDeg);
		driver.findElement(By.id("LatMin")).sendKeys(LatMin);
		Select latns = new Select(driver.findElement(By.id("LatNS")));
		latns.selectByValue(LatNS);
		
		driver.findElement(By.id("timeZone")).sendKeys(timezone);
		
		driver.findElement(By.id("submit")).click();
		//Thread.sleep(5000);
		
		tmp_minutes = tmp_minutes + interval;
		if(tmp_minutes >= 60) {
			tmp_minutes = ((tmp_minutes + interval) - 60) - interval;
			tmp_hours = tmp_hours + 1;
		}
		
		tmp_IST_minutes = tmp_IST_minutes + interval;
		if(tmp_IST_minutes >= 60) {
			tmp_IST_minutes = ((tmp_IST_minutes + interval) - 60) - interval;
			tmp_IST_hours = tmp_IST_hours + 1;
		}
		
		driver.navigate().to("https://ascloud.astrosage.com/cloud/KPPlanetCusp.asp");
		String moon = driver.findElement(By.xpath("/html/body/div[2]/div/section/div/div[5]/div[1]/div[1]/div[2]/div/div/table/tbody/tr[2]")).getText();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Moon Muhurata @ "+hours+":"+minutes+" - "+tmp_hours+":"+tmp_minutes+" ("+IST_hours+":"+IST_minutes+" - "+tmp_IST_hours+":"+tmp_IST_minutes+" IST) :- \n");
		System.out.println("Planets	Degree	RAS-NAK-SUB-SS	R\n");
		System.out.println(moon);
		
		driver.navigate().to("https://ascloud.astrosage.com/cloud/nakshatra-nadi.asp");
		//String planet_signi = driver.findElement(By.xpath("/html/body/div[2]/div/section/div/div[5]/div[1]/div[1]/div[2]/div/div/table")).getText();
		System.out.println("\n\nKP Planet Significatior @ "+hours+":"+minutes+" - "+tmp_hours+":"+tmp_minutes+" ("+IST_hours+":"+IST_minutes+" - "+tmp_IST_hours+":"+tmp_IST_minutes+" IST) :- \n");
		System.out.println("Planet"+"                    "+"Star Lord"+"                    "+"Total upto star"+"                    "+"Sub Lord"+"                    "+"Total upto sub\n");
		for(int i=1; i<=9; i++) {
			
			int sum1 = 0;
			int sum2 = 0;
			int sum3 = 0;
			
			for(int j=1; j<=3; j++) {
				
				String planet_signification = driver.findElement(By.xpath("/html/body/div[2]/div/section/div/div[5]/div[1]/div[1]/div[2]/div/div/table/tbody/tr["+i+"]/td["+j+"]")).getText();
				String planet_signi = planet_signification;
				
				if(j == 1) {
					planet_signi = planet_signi.replaceAll("[^\\d]", " ");
					planet_signi = planet_signi.trim();
					planet_signi = planet_signi.replaceAll(" +", " ");
					String[] numbers=planet_signi.split(" ");
					
					for(int s=0; s<numbers.length; s++){
						int tmp = Integer.parseInt(numbers[s]);
						if(tmp == 1 || tmp == 2 || tmp == 3 || tmp == 6 || tmp == 10 || tmp == 11) {
						sum1 = sum1 + 1; } else { sum1 = sum1 - 1; }
					}
					System.out.print(planet_signification+" = "+sum1);
					System.out.print("                    ");
				} else if (j == 2) {
					planet_signi = planet_signi.replaceAll("[^\\d]", " ");
					planet_signi = planet_signi.trim();
					planet_signi = planet_signi.replaceAll(" +", " ");
					String[] numbers=planet_signi.split(" ");
					
					for(int s=0; s<numbers.length; s++){
						int tmp = Integer.parseInt(numbers[s]);
						if(tmp == 1 || tmp == 2 || tmp == 3 || tmp == 6 || tmp == 10 || tmp == 11) {
							sum2 = sum2 + 1; } else { sum2 = sum2 - 1; }
					}
					sum2 = sum2 * 2;
					System.out.print(planet_signification+" = "+sum2);
					System.out.print("                    "+"Total : "+(sum1+sum2)+"                    ");
				} else if (j == 3) {
					planet_signi = planet_signi.replaceAll("[^\\d]", " ");
					planet_signi = planet_signi.trim();
					planet_signi = planet_signi.replaceAll(" +", " ");
					String[] numbers=planet_signi.split(" ");
					
					for(int s=0; s<numbers.length; s++){
						int tmp = Integer.parseInt(numbers[s]);
						if(tmp == 1 || tmp == 2 || tmp == 3 || tmp == 6 || tmp == 10 || tmp == 11) {
							sum3 = sum3 + 1; } else { sum3 = sum3 - 1; }
					}
					sum3 = sum3 * 3;
					System.out.print(planet_signification+" = "+sum3);
					System.out.print("                    "+"Total : "+(sum1+sum2+sum3));
				}
				
			}
			
			System.out.println("\n");
		}
		
		if(IST_minutes + interval >= 60) {
			IST_hours = IST_hours + 1;
			IST_minutes = ((IST_minutes + interval) - 60) - interval; 
		}
		IST_minutes = IST_minutes + interval;
		
		int hours2 = hours;
		int minutes2 = minutes;
		
		if(minutes + interval >= 60) {
			hours = hours + 1;
			minutes = ((minutes + interval) - 60) - interval; 
		}
		
		if(hours2 == till_hour && minutes2 >= till_minute) {
			System.setOut(console);
			System.out.println("\n\n\n\n\n\n\n\n******************************************** Your KP Report has been generated successfully. Thank You for your patience *********************************************");
			minutes = 1000;
		}
		increment_bar = increment_bar + i_bar;
		bar.setValue((int) increment_bar);
			} catch (WebDriverException e) {
				System.setOut(console);
				System.out.println("\n\n\n\n\n\n\n\n************************************************************* Exception Caught - Reattempting Everything Again *************************************************************");
				System.setOut(o);
				minutes = minutes - interval;
				continue;
			}
		} 
		
//-------------------------------------------------------------------------------------------------------------------
//		bar.setString("Generating PDF....");
		// Code to generate PDF
//		Document document = new Document(PageSize.A4);
//		File f = new File("C://Users//yadav//Desktop//KP_AstroSportsReports//PDF//" + name + ".pdf");
//		try
//        {
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
//			document.open();
//			document.add(new Paragraph("KP Report as per Moon Muhurata"));
//           //Text file Data
//           try (BufferedReader br = new BufferedReader(new FileReader("C://Users//yadav//Desktop//KP_AstroSportsReports//" + name + ".txt"))) {
//                  
//                  String sCurrentLine;
//
//                  while ((sCurrentLine = br.readLine()) != null) {
//                      document.add(new Paragraph(sCurrentLine));
//                  }
//
//                  } catch (IOException exception) {
//                  exception.printStackTrace();
//              }
//           
//           document.close();
//           writer.close();
//           
//        } catch (DocumentException exception)
//        {
//           exception.printStackTrace();
//        } catch (FileNotFoundException exception)
//        {
//           exception.printStackTrace();
//        } 
		
//-------------------------------------------------------------------------------------------------------------------
		bar.setString("Done! :)"); driver.quit();
		frame.setVisible(false);
		frame.dispose();
		
	}}
