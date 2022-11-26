package com.AstroSports.CuspalTransitTracker;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

public class CuspalTransitTracker {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		List<String> planetFlow = new ArrayList<>();
		String currentPlanetStatus;
		String previousPlanetStatus = null;
		
		List<String> starFlow = new ArrayList<>();
		String currentStarStatus;
		String previousStarStatus = null;
		
		List<String> subFlow = new ArrayList<>();
		String currentSubStatus;
		String previousSubStatus = null;
		
		MyFrame frame1 = new MyFrame();
		
		System.out.println("**************** Planet, Star & Sub - Sport's Prediction Using KP Astrology Automated Report ****************\n\n");
		
		String name = frame1.name.getText();
		DateFormat sysDate = new SimpleDateFormat("d-M-yyyy");
		String sdate = sysDate.format(frame1.picker.getDate());
		String date = sdate.split("-")[0];
		String month = sdate.split("-")[1];
		String year = sdate.split("-")[2];
		String hour = frame1.hour.getSelectedItem().toString();
		String minute = frame1.minute.getSelectedItem().toString();
		String stadium = frame1.stadium.getSelectedItem().toString();
		String Till_Hour = frame1.till_hour.getSelectedItem().toString();
		String Till_Minute = frame1.till_minute.getSelectedItem().toString();
		String IST_hour = frame1.ist_hour.getSelectedItem().toString();
		String IST_minute = frame1.ist_minute.getSelectedItem().toString();
		
		int hours = Integer.parseInt(hour); int hourst = hours;
		int minutes = Integer.parseInt(minute); int minutest = minutes;
		
		int till_hour = Integer.parseInt(Till_Hour);
		int till_minute = Integer.parseInt(Till_Minute);
		int interval = 1;
		
		int IST_hours = Integer.parseInt(IST_hour); int IST_hourst = IST_hours;
		int IST_minutes = Integer.parseInt(IST_minute); int IST_minutest = IST_minutes;
		
		String sname = null;
		String latitude = null;
		String longitude = null;
		String timezone = null;
		
//-------------------------------------------------------------------------------------------------------------------
//												STADIUM DATABASE
//-------------------------------------------------------------------------------------------------------------------
		
		if(!stadium.equals("ADD NEW STADIUM")) {
			if(stadium.equals("UPDATE EXISTING TIMEZONE")) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
					String query = "UPDATE stadiums SET timezone = ? WHERE alias = ? OR name like ?";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, frame1.stimezone.getText());
					pst.setString(2, frame1.salias.getText());
					pst.setString(3, frame1.sname.getText());
					pst.executeUpdate();
					System.out.println("\nTimezone for Stadium "+frame1.sname.getText()+"("+frame1.salias.getText()+") Updated............");
					stadium = frame1.sname.getText();
					con.close();
				} catch (Exception e) {
					System.out.println("\nHuh huh  <(-_-)>  Something went wrong while updating timezone for stadium in database............\n");
					System.out.println(e.toString());
				}
			}
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
			String query = "select * from stadiums where name = ? OR alias = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, stadium);
			pst.setString(2, frame1.salias.getText());
			ResultSet set = pst.executeQuery();
			while (set.next()) {
				sname = set.getString(2);
				latitude = set.getString(3);
				longitude = set.getString(4);
				timezone = set.getString(5);
				System.out.println("\nYou have selected stadium : " + sname);
				System.out.println("Alias : " + set.getString(1));
				System.out.println("Latitude : " + latitude);
				System.out.println("Longitude : " + longitude);
				System.out.println("Timezone : " + timezone + "\n\n");
			} con.close();
		} catch (Exception e) {
			System.out.println(
					"\nHuh huh  <(-_-)>  Something went wrong while retrieving your stadium from database....\n");
			System.out.println(e);
		}

		} else {
			try {
				String stadium_alias = frame1.salias.getText();
				sname = frame1.sname.getText();
				latitude = frame1.slatitude.getText();
				longitude = frame1.slongitude.getText();
				timezone = frame1.stimezone.getText();
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
				String query = "insert into stadiums(alias,name,latitude,longitude,timezone) values(?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, stadium_alias);
				pst.setString(2, sname);
				pst.setString(3, latitude);
				pst.setString(4, longitude);
				pst.setString(5, timezone);
				pst.executeUpdate();
				System.out.println("\nStadium "+sname+" added to database.");
				con.close();
			} catch (Exception e) {
				System.out
						.println("\nHuh huh  <(-_-)> Something went wrong while adding your stadium to database....\n");
				System.out.println(e);
			}
		}
		
		HashBiMap<String, Integer> planets_id = HashBiMap.create();
		planets_id.put("Sun", 1);
		planets_id.put("Moon", 2);
		planets_id.put("Mars", 3);
		planets_id.put("Mercury", 4);
		planets_id.put("Jupiter", 5);
		planets_id.put("Venus", 6);
		planets_id.put("Saturn", 7);
		planets_id.put("Rahu", 8);
		planets_id.put("Ketu", 9);
		
		HashBiMap<Integer, String> retro = HashBiMap.create();
		retro.put(0, "");
		retro.put(1, "(R)");
		retro.put(2, "**");
		retro.put(3, "++");
		
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Local\\Google\\Chrome\\User Data");
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		
//		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver79.exe");
//		OperaOptions options = new OperaOptions();
//		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
//		WebDriver driver = new OperaDriver(options);
		
		System.out.println("\n\n****************************************** KP Report is being generated as per 6th & 12th CSL. This may take several minutes ******************************************");
		
		PrintStream o = new PrintStream(new File("C://Users//yadav//Desktop//MyKP//" + name + ".txt"));
		PrintStream console = System.out;
		System.setOut(o);
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Sports Contest Details :-\n");
		System.out.println("Name : " + name);
		System.out.println("Date : " + date + "-" + month + "-" + year);
		System.out.println("Time : " + hours + ":" + minutes + " till " + till_hour + ":" + till_minute);
		System.out.println("Place : " + stadium);
		System.out.println("Interval : " + interval + " minute\n");
		
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
		
		int P_ASC_M = 0;
		int P_ASC_T = 0;
		int P_DSC_M = 0;
		int P_DSC_T = 0;
		
		int ST_ASC_M = 0;
		int ST_ASC_T = 0;
		int ST_DSC_M = 0;
		int ST_DSC_T = 0;
		
		int SU_ASC_M = 0;
		int SU_ASC_T = 0;
		int SU_DSC_M = 0;
		int SU_DSC_T = 0;
		
		int P_N_M = 0;
		int ST_N_M = 0;
		int SU_N_M = 0;
		
		int ASCM = 0;
		int DSCM = 0;
		int NM = 0;
		
for( ; minutes <=59 ; minutes = minutes + interval) {
			
			try {
		
//		driver.navigate().to("https://www.rahasyavedicastrology.com/rva-software/");
		driver.get("https://www.rahasyavedicastrology.com/rva-software/");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("m-name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), name);
		
		Select dates = new Select(driver.findElement(By.id("m-date")));
		dates.selectByValue(date);
		Select months = new Select(driver.findElement(By.id("m-month")));
		months.selectByValue(month);
		Select years = new Select(driver.findElement(By.id("m-year")));
		years.selectByValue(year);
		
		Select sel_hours = new Select(driver.findElement(By.id("m-hour")));
		sel_hours.selectByValue(Integer.toString(hours));
		Select minutes1 = new Select(driver.findElement(By.id("m-minute")));
		minutes1.selectByValue(Integer.toString(minutes));
		Select seconds = new Select(driver.findElement(By.id("m-seconds")));
		seconds.selectByValue("0");
		
		driver.findElement(By.id("m-advanced-geo-option")).click();
		
		driver.findElement(By.id("m-hr-lat")).sendKeys(Keys.chord(Keys.CONTROL, "a"), latitude);
		driver.findElement(By.id("m-hr-lon")).sendKeys(Keys.chord(Keys.CONTROL, "a"), longitude);
		driver.findElement(By.id("m-hr-tzone")).sendKeys(Keys.chord(Keys.CONTROL, "a"), timezone);
		
		driver.findElement(By.id("m-submit-hr-form")).click();
		
//		Thread.sleep(10000);
		
		Multimap<Integer, Integer> planets = ArrayListMultimap.create();
		
		for(int i=1; i<=9; i++) {
			
			String planet_name = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr["+i+"]/th"))).getText();
			
			// getting planet retro-1 or not-0 @ index 0
			if(planet_name.contains("(R)")) {
				planets.put(i, 1);
			} else {
				planets.put(i, 0);
			}
			
			for(int j=6; j<=7; j++) {
				
				String planet_prop = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]")).getText();
				
				// planet star lord @ index 1 & planet sub lord @ index 2
				planets.put(i, planets_id.get(planet_prop));
				
			}
			
		}
		
//------------------------------------------------------------------------------------------------------------------
		
		// getting planet self-2 / tenanted-0 / untenanted-3 @ index 3
		for(int i=1; i<=9; i++) {
			boolean ut = true;
			for(int j=1; j<=9; j++) {
			int planet_star = Iterables.get(planets.get(j), 1);
			if(planet_star == i && j == i) {
				planets.put(i, 2);
				ut = false;
				break;
			} else if(planet_star == i) {
				planets.put(i, 0);
				ut = false;
				break;
			}
		} if(ut) { planets.put(i, 3); }	
	}
	
//------------------------------------------------------------------------------------------------------------------
		
		String asc_csl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[6]/td[6]")).getText();
		String dsc_csl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[12]/td[6]")).getText();
//		String mc_csl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[10]/td[6]")).getText();
//		String ic_csl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[4]/td[6]")).getText();
//		String sixth_csl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[6]/td[6]")).getText();
//		String twelfth_csl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[12]/td[6]")).getText();
		
		int asc_csl_star = Iterables.get(planets.get(planets_id.get(asc_csl)), 1);
		int asc_csl_sub = Iterables.get(planets.get(planets_id.get(asc_csl)), 2);
		
		int dsc_csl_star = Iterables.get(planets.get(planets_id.get(dsc_csl)), 1);
		int dsc_csl_sub = Iterables.get(planets.get(planets_id.get(dsc_csl)), 2);
		
		String asc_csl_stars = planets_id.inverse().get(asc_csl_star);
		String asc_csl_subs = planets_id.inverse().get(asc_csl_sub);
		
		String dsc_csl_stars = planets_id.inverse().get(dsc_csl_star);
		String dsc_csl_subs = planets_id.inverse().get(dsc_csl_sub);
		
		int ASC_SUB = 0;
		int DSC_SUB = 0;
		int ASC_SUB_STAR = 0;
		int DSC_SUB_STAR = 0;
		int ASC_SUB_SUB = 0;
		int DSC_SUB_SUB = 0;
		String planet_status;
		String star_status;
		String sub_status;
		
		int ASC = 0;
		int DSC = 0;
		
		Multimap<Integer, String> house_view = ArrayListMultimap.create();
		for(int i=1; i<=12; i++) {
			for(int j=1; j<=4; j++) {
			String s = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[2]/div/div[1]/div/table/tbody/tr["+i+"]/td["+j+"]")).getText();
			if( (s.equals("Uranus")) || (s.equals("Neptune")) || (s.equals("Pluto")) ) {
			house_view.put(i, "");
			} else { house_view.put(i, s); }
		}
		}
		
//-------------------------------------------------------------------------------------------------------------------
// Calculations of 6th & 12th CSL
		
		for(int i=1; i<=12; i++) {
			
			if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl)))) {
				ASC_SUB = ASC_SUB + 4; }
			else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl)))) {
				ASC_SUB = ASC_SUB - 4; }
			else if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
					if(Iterables.get(house_view.get(i), 2).contains(asc_csl)) { ASC_SUB = ASC_SUB + 4; } 
					else if(Iterables.get(house_view.get(i), 3).contains(asc_csl)) { ASC_SUB = ASC_SUB + 2; }
			}
			else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
				if(Iterables.get(house_view.get(i), 2).contains(asc_csl)) { ASC_SUB = ASC_SUB - 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(asc_csl)) { ASC_SUB = ASC_SUB - 2; }
		}
			else {
				int cnt = 4;
				for(int j=0; j<=3; j++) {
				String s = Iterables.get(house_view.get(i), j);
				
				if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && (s.contains(asc_csl))) {
						ASC_SUB = ASC_SUB + cnt;
			} else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && (s.contains(asc_csl))) {
						ASC_SUB = ASC_SUB - cnt;
			}
				cnt = cnt - 1;
			}
			}
			
		}
		
		for(int i=1; i<=12; i++) {
			
			if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl))))) {
				DSC_SUB = DSC_SUB - 4; }
			else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl))))) {
				DSC_SUB = DSC_SUB + 4; }
			else if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
					if(Iterables.get(house_view.get(i), 2).contains(dsc_csl)) { DSC_SUB = DSC_SUB - 4; } 
					else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl)) { DSC_SUB = DSC_SUB - 2;}
			}
			else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if(Iterables.get(house_view.get(i), 2).contains(dsc_csl)) { DSC_SUB = DSC_SUB + 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl)) { DSC_SUB = DSC_SUB + 2;}
		}
			else {
				int cnt = 4;
				for(int j=0; j<=3; j++) {
				String s = Iterables.get(house_view.get(i), j);
				
				if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && (s.contains(dsc_csl))) {
						DSC_SUB = DSC_SUB - cnt;
			} else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && (s.contains(dsc_csl))) {
						DSC_SUB = DSC_SUB + cnt;
			}
				cnt = cnt - 1;
			}
			}
			
		}
		
		if(ASC_SUB == DSC_SUB) {
			planet_status = "(1) Planet Level : Neutral";
			currentPlanetStatus = "Neutral";
			P_N_M = P_N_M + 1;
		} else if(asc_csl.equals(dsc_csl)) {
			if(ASC_SUB > 0) {
				planet_status = "(1) Planet Level : ASC by " + ASC_SUB;
				currentPlanetStatus = "ASC";
				P_ASC_M = P_ASC_M + 1;
				P_ASC_T = P_ASC_T + ASC_SUB;
				ASC = ASC + ASC_SUB;
			} else {
				planet_status = "(1) Planet Level : DSC by "+ DSC_SUB;
				currentPlanetStatus = "DSC";
				P_DSC_M = P_DSC_M + 1;
				P_DSC_T = P_DSC_T + DSC_SUB;
				DSC = DSC + DSC_SUB;
			}
		} else if(ASC_SUB > DSC_SUB) {
			planet_status = "(1) Planet Level : ASC by " + (ASC_SUB - DSC_SUB);
			currentPlanetStatus = "ASC";
			P_ASC_M = P_ASC_M + 1;
			P_ASC_T = P_ASC_T + (ASC_SUB - DSC_SUB);
			ASC = ASC + (ASC_SUB - DSC_SUB);
		} else {
			planet_status = "(1) Planet Level : DSC by " + (DSC_SUB - ASC_SUB);
			currentPlanetStatus = "DSC";
			P_DSC_M = P_DSC_M + 1;
			P_DSC_T = P_DSC_T + (DSC_SUB - ASC_SUB);
			DSC = DSC + (DSC_SUB - ASC_SUB);
		}
		
//-------------------------------------------------------------------------------------------------------------------
// Calculations of 6th & 12th CSL Star Lord
		
for(int i=1; i<=12; i++) {
		
		if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)))) {
			ASC_SUB_STAR = ASC_SUB_STAR + 4; }
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)))) {
			ASC_SUB_STAR = ASC_SUB_STAR - 4; }
		else if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
				if(Iterables.get(house_view.get(i), 2).contains(asc_csl_stars)) { ASC_SUB_STAR = ASC_SUB_STAR + 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)) { ASC_SUB_STAR = ASC_SUB_STAR + 2; }
		}
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
			if(Iterables.get(house_view.get(i), 2).contains(asc_csl_stars)) { ASC_SUB_STAR = ASC_SUB_STAR - 4; } 
			else if(Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)) { ASC_SUB_STAR = ASC_SUB_STAR - 2; }
	}
		else {
			int cnt = 4;
			for(int j=0; j<=3; j++) {
			String s = Iterables.get(house_view.get(i), j);
			
			if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && (s.contains(asc_csl_stars))) {
					ASC_SUB_STAR = ASC_SUB_STAR + cnt;
		} else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && (s.contains(asc_csl_stars))) {
					ASC_SUB_STAR = ASC_SUB_STAR - cnt;
		}
			cnt = cnt - 1;
		}
		}
		
	}
	
for(int i=1; i<=12; i++) {
		
		if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars))))) {
			DSC_SUB_STAR = DSC_SUB_STAR - 4; }
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars))))) {
			DSC_SUB_STAR = DSC_SUB_STAR + 4; }
		else if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if(Iterables.get(house_view.get(i), 2).contains(dsc_csl_stars)) { DSC_SUB_STAR = DSC_SUB_STAR - 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars)) { DSC_SUB_STAR = DSC_SUB_STAR - 2;}
		}
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
			if(Iterables.get(house_view.get(i), 2).contains(dsc_csl_stars)) { DSC_SUB_STAR = DSC_SUB_STAR + 4; } 
			else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars)) { DSC_SUB_STAR = DSC_SUB_STAR + 2;}
	}
		else {
			int cnt = 4;
			for(int j=0; j<=3; j++) {
			String s = Iterables.get(house_view.get(i), j);
			
			if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && (s.contains(dsc_csl_stars))) {
					DSC_SUB_STAR = DSC_SUB_STAR - cnt;
		} else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && (s.contains(dsc_csl_stars))) {
					DSC_SUB_STAR = DSC_SUB_STAR + cnt;
		}
			cnt = cnt - 1;
		}
		}
		
	}

if(ASC_SUB_STAR == DSC_SUB_STAR) {
	star_status = "(2) Star Level : Neutral";
	currentStarStatus = "Neutral";
	ST_N_M = ST_N_M + 1;
} else if(asc_csl_stars.equals(dsc_csl_stars)) {
	if(ASC_SUB_STAR > 0) {
		star_status = "(2) Star Level : ASC by " + ASC_SUB_STAR;
		currentStarStatus = "ASC";
		ST_ASC_M = ST_ASC_M + 1;
		ST_ASC_T = ST_ASC_T + ASC_SUB_STAR;
		ASC = ASC + (ASC_SUB_STAR * 2);
	} else {
		star_status = "(2) Star Level : DSC by "+ DSC_SUB_STAR;
		currentStarStatus = "DSC";
		ST_DSC_M = ST_DSC_M + 1;
		ST_DSC_T = ST_DSC_T + DSC_SUB_STAR;
		DSC = DSC + (DSC_SUB_STAR * 2);
	}
} else if(ASC_SUB_STAR > DSC_SUB_STAR) {
	star_status = "(2) Star Level : ASC by " + (ASC_SUB_STAR - DSC_SUB_STAR);
	currentStarStatus = "ASC";
	ST_ASC_M = ST_ASC_M + 1;
	ST_ASC_T = ST_ASC_T + (ASC_SUB_STAR - DSC_SUB_STAR);
	ASC = ASC + (ASC_SUB_STAR - DSC_SUB_STAR) * 2;
} else {
	star_status = "(2) Star Level : DSC by " + (DSC_SUB_STAR - ASC_SUB_STAR);
	currentStarStatus = "DSC";
	ST_DSC_M = ST_DSC_M + 1;
	ST_DSC_T = ST_DSC_T + (DSC_SUB_STAR - ASC_SUB_STAR);
	DSC = DSC + (DSC_SUB_STAR - ASC_SUB_STAR) * 2;
}
	
//-------------------------------------------------------------------------------------------------------------------
// Calculations of 6th & 12th CSL Sub Lord
	
for(int i=1; i<=12; i++) {
		
		if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)))) {
			ASC_SUB_SUB = ASC_SUB_SUB + 4; }
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")) && (Iterables.get(house_view.get(i), 2).equals("")) && (Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)))) {
			ASC_SUB_SUB = ASC_SUB_SUB - 4; }
		else if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
				if(Iterables.get(house_view.get(i), 2).contains(asc_csl_subs)) { ASC_SUB_SUB = ASC_SUB_SUB + 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)) { ASC_SUB_SUB = ASC_SUB_SUB + 2; }
		}
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0).equals("")) && (Iterables.get(house_view.get(i), 1).equals("")))) {
			if(Iterables.get(house_view.get(i), 2).contains(asc_csl_subs)) { ASC_SUB_SUB = ASC_SUB_SUB - 4; } 
			else if(Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)) { ASC_SUB_SUB = ASC_SUB_SUB - 2; }
	}
		else {
			int cnt = 4;
			for(int j=0; j<=3; j++) {
			String s = Iterables.get(house_view.get(i), j);
			
			if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && (s.contains(asc_csl_subs))) {
					ASC_SUB_SUB = ASC_SUB_SUB + cnt;
		} else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && (s.contains(asc_csl_subs))) {
					ASC_SUB_SUB = ASC_SUB_SUB - cnt;
		}
			cnt = cnt - 1;
		}
		}
		
	}
	
	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("AT "+hours+":"+minutes+" ("+IST_hours+":"+IST_minutes+" IST) :-\n");
	System.out.println("6th CSL : "+asc_csl+retro.get(Iterables.get(planets.get(planets_id.get(asc_csl)), 0))+retro.get(Iterables.get(planets.get(planets_id.get(asc_csl)), 3))+" = "+ASC_SUB);
	System.out.println(asc_csl+" Star Lord : "+asc_csl_stars+retro.get(Iterables.get(planets.get(planets_id.get(asc_csl_stars)), 0))+" = "+ASC_SUB_STAR);
	System.out.println(asc_csl+" Sub Lord : "+asc_csl_subs+retro.get(Iterables.get(planets.get(planets_id.get(asc_csl_subs)), 0))+" = "+ASC_SUB_SUB);
	
for(int i=1; i<=12; i++) {
		
		if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs))))) {
			DSC_SUB_SUB = DSC_SUB_SUB - 4; }
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "") && (Iterables.get(house_view.get(i), 2) == "" && (Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs))))) {
			DSC_SUB_SUB = DSC_SUB_SUB + 4; }
		else if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if(Iterables.get(house_view.get(i), 2).contains(dsc_csl_subs)) { DSC_SUB_SUB = DSC_SUB_SUB - 4; } 
				else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs)) { DSC_SUB_SUB = DSC_SUB_SUB - 2;}
		}
		else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
			if(Iterables.get(house_view.get(i), 2).contains(dsc_csl_subs)) { DSC_SUB_SUB = DSC_SUB_SUB + 4; } 
			else if(Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs)) { DSC_SUB_SUB = DSC_SUB_SUB + 2;}
	}
		else {
			int cnt = 4;
			for(int j=0; j<=3; j++) {
			String s = Iterables.get(house_view.get(i), j);
			
			if((i == 1 || i == 2 || i == 3 || i == 6 || i == 10 || i == 11) && (s.contains(dsc_csl_subs))) {
					DSC_SUB_SUB = DSC_SUB_SUB - cnt;
		} else if((i == 7 || i == 8 || i == 9 || i == 4 || i == 5 || i == 12) && (s.contains(dsc_csl_subs))) {
					DSC_SUB_SUB = DSC_SUB_SUB + cnt;
		}
			cnt = cnt - 1;
		}
		}
		
	}

if(ASC_SUB_SUB == DSC_SUB_SUB) {
	sub_status = "(3) Sub Level : Neutral";
	currentSubStatus = "Neutral";
	SU_N_M = SU_N_M + 1;
} else if(asc_csl_subs.equals(dsc_csl_subs)) {
	if(ASC_SUB_SUB > 0) {
		sub_status = "(3) Sub Level : ASC by " + ASC_SUB_SUB;
		currentSubStatus = "ASC";
		SU_ASC_M = SU_ASC_M + 1;
		SU_ASC_T = SU_ASC_T + ASC_SUB_SUB;
		ASC = ASC + (ASC_SUB_SUB * 3);
	} else {
		sub_status = "(3) Sub Level : DSC by "+ DSC_SUB_SUB;
		currentSubStatus = "DSC";
		SU_DSC_M = SU_DSC_M + 1;
		SU_DSC_T = SU_DSC_T + DSC_SUB_SUB;
		DSC = DSC + (DSC_SUB_SUB * 3);
	}
} else if(ASC_SUB_SUB > DSC_SUB_SUB) {
	sub_status = "(3) Sub Level : ASC by " + (ASC_SUB_SUB - DSC_SUB_SUB);
	currentSubStatus = "ASC";
	SU_ASC_M = SU_ASC_M + 1;
	SU_ASC_T = SU_ASC_T + (ASC_SUB_SUB - DSC_SUB_SUB);
	ASC = ASC + (ASC_SUB_SUB - DSC_SUB_SUB) * 3;
} else {
	sub_status = "(3) Sub Level : DSC by " + (DSC_SUB_SUB - ASC_SUB_SUB);
	currentSubStatus = "DSC";
	SU_DSC_M = SU_DSC_M + 1;
	SU_DSC_T = SU_DSC_T + (DSC_SUB_SUB - ASC_SUB_SUB);
	DSC = DSC + (DSC_SUB_SUB - ASC_SUB_SUB) * 3;
}

	System.out.println("\n12th CSL : "+dsc_csl+retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl)), 0))+retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl)), 3))+" = "+DSC_SUB);
	System.out.println(dsc_csl+" Star Lord : "+dsc_csl_stars+retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl_stars)), 0))+" = "+DSC_SUB_STAR);
	System.out.println(dsc_csl+" Sub Lord : "+dsc_csl_subs+retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl_subs)), 0))+" = "+DSC_SUB_SUB+"\n");
	
	System.out.println(planet_status);
	if (currentPlanetStatus != previousPlanetStatus) {
		String result = hours+":"+minutes+" ("+IST_hours+":"+IST_minutes+")"+" - "+currentPlanetStatus;
		previousPlanetStatus = currentPlanetStatus;
		planetFlow.add(result);
	}
	System.out.println(star_status);
	if (currentStarStatus != previousStarStatus) {
		String result = hours+":"+minutes+" ("+IST_hours+":"+IST_minutes+")"+" - "+currentStarStatus;
		previousStarStatus = currentStarStatus;
		starFlow.add(result);
	}
	System.out.println(sub_status);
	if (currentSubStatus != previousSubStatus) {
		String result = hours+":"+minutes+" ("+IST_hours+":"+IST_minutes+")"+" - "+currentSubStatus;
		previousSubStatus = currentSubStatus;
		subFlow.add(result);
	}
	
	System.out.println("\nASC : "+ASC+"  ,  DSC : "+DSC+"\n");
	if(ASC == DSC) {
		System.out.println("****** BALANCED ******");
		NM = NM + 1;
	} else if(ASC > DSC) {
		System.out.println("****** ASC by "+(ASC - DSC)+" ******");
		ASCM = ASCM + 1;
	} else {
		System.out.println("****** DSC by "+(DSC - ASC)+" ******");
		DSCM = DSCM + 1;
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
		System.out.println("\n\n\n\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("OVERALL REPORT ANALYSIS FROM " + hourst + ":" + minutest + " till " + till_hour + ":" + till_minute+"  ("+IST_hourst+":"+IST_minutest+" till "+IST_hours+":"+(IST_minutes-1)+" IST)\n");
//		System.out.println("Total minutes & value favoring ASC (Planet Level) : "+P_ASC_M+" minutes and "+P_ASC_T);
//		System.out.println("Total minutes & value favoring DSC (Planet Level) : "+P_DSC_M+" minutes and "+P_DSC_T);
//		System.out.println("Total Neutral minutes (Planet Level) : "+P_N_M+" minutes\n");
//		if(P_ASC_M == P_DSC_M) {
//			System.out.println("**************************************************** Minutes are balanced at Planet Level ************************************************************************************");
//		} else if(P_ASC_M > P_DSC_M) {
//			System.out.println("**************************************************** Minutes are in favor of ASC at Planet Level by "+(P_ASC_M - P_DSC_M)+" ************************************************************************************");
//		} else {
//			System.out.println("**************************************************** Minutes are in favor of DSC at Planet Level by "+(P_DSC_M - P_ASC_M)+" ************************************************************************************");
//		}
//		if(P_ASC_T == P_DSC_T) {
//			System.out.println("**************************************************** Total value is balanced at Planet Level ************************************************************************************");
//		} else if(P_ASC_T > P_DSC_T) {
//			System.out.println("**************************************************** Total value is in favor of ASC at Planet Level by "+(P_ASC_T - P_DSC_T)+" ************************************************************************************");
//			ASCF = ASCF + (P_ASC_T - P_DSC_T);
//		} else {
//			System.out.println("**************************************************** Total value is in favor of DSC at Planet Level by "+(P_DSC_T - P_ASC_T)+" ************************************************************************************");
//			DSCF = DSCF + (P_DSC_T - P_ASC_T);
//		}
//		System.out.println("\n\nTotal minutes & value favoring ASC (Star Level) : "+ST_ASC_M+" minutes and "+ST_ASC_T);
//		System.out.println("Total minutes & value favoring DSC (Star Level) : "+ST_DSC_M+" minutes and "+ST_DSC_T);
//		System.out.println("Total Neutral minutes (Star Level) : "+ST_N_M+" minutes\n");
//		if(ST_ASC_M == ST_DSC_M) {
//			System.out.println("**************************************************** Minutes are balanced at Star Level ************************************************************************************");
//		} else if(ST_ASC_M > ST_DSC_M) {
//			System.out.println("**************************************************** Minutes are in favor of ASC at Star Level by "+(ST_ASC_M - ST_DSC_M)+" ************************************************************************************");
//		} else {
//			System.out.println("**************************************************** Minutes are in favor of DSC at Star Level by "+(ST_DSC_M - ST_ASC_M)+" ************************************************************************************");
//		}
//		if(ST_ASC_T == ST_DSC_T) {
//			System.out.println("**************************************************** Total value is balanced at Star Level ************************************************************************************");
//		} else if(ST_ASC_T > ST_DSC_T) {
//			System.out.println("**************************************************** Total value is in favor of ASC at Star Level by "+(ST_ASC_T - ST_DSC_T)+" ************************************************************************************");
//			ASCF = ASCF + (ST_ASC_T - ST_DSC_T) * 2;
//		} else {
//			System.out.println("**************************************************** Total value is in favor of DSC at Star Level by "+(ST_DSC_T - ST_ASC_T)+" ************************************************************************************");
//			DSCF = DSCF + (ST_DSC_T - ST_ASC_T) * 2;
//		}
//		System.out.println("\n\nTotal minutes & value favoring ASC (Sub Level) : "+SU_ASC_M+" minutes and "+SU_ASC_T);
//		System.out.println("Total minutes & value favoring DSC (Sub Level) : "+SU_DSC_M+" minutes and "+SU_DSC_T);
//		System.out.println("Total Neutral minutes (Sub Level) : "+SU_N_M+" minutes\n");
//		if(SU_ASC_M == SU_DSC_M) {
//			System.out.println("**************************************************** Minutes are balanced at Sub Level ************************************************************************************");
//		} else if(SU_ASC_M > SU_DSC_M) {
//			System.out.println("**************************************************** Minutes are in favor of ASC at Sub Level by "+(SU_ASC_M - SU_DSC_M)+" ************************************************************************************");
//		} else {
//			System.out.println("**************************************************** Minutes are in favor of DSC at Sub Level by "+(SU_DSC_M - SU_ASC_M)+" ************************************************************************************");
//		}
//		if(SU_ASC_T == SU_DSC_T) {
//			System.out.println("**************************************************** Total value is balanced at Sub Level ************************************************************************************");
//		} else if(SU_ASC_T > SU_DSC_T) {
//			System.out.println("**************************************************** Total value is in favor of ASC at Sub Level by "+(SU_ASC_T - SU_DSC_T)+" ************************************************************************************");
//			ASCF = ASCF + (SU_ASC_T - SU_DSC_T) * 3;
//		} else {
//			System.out.println("**************************************************** Total value is in favor of DSC at Sub Level by "+(SU_DSC_T - SU_ASC_T)+" ************************************************************************************");
//			DSCF = DSCF + (SU_DSC_T - SU_ASC_T) * 3;
//		}
//		
//		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		
//		System.out.println("(ADVANCED MODULE) : Planet * 1 + Star * 2 + Sub * 3\n");
//		System.out.println("ASC_MINUTES : "+ASCM+"  ,  DSC_MINUTES : "+DSCM+"  ,  NEUTRAL_MINUTES : "+NM+"\n");
//		if(ASCM == DSCM) {
//			System.out.println("************************************************************ MINUTES BALANCED ************************************************************************************");
//		} else if(ASCM > DSCM) {
//			System.out.println("************************************************************ ASC_MINUTES by "+(ASCM - DSCM)+" ************************************************************************************");
//		} else {
//			System.out.println("************************************************************ DSC_MINUTES by "+(DSCM - ASCM)+" ************************************************************************************");
//		}
//		
//		System.out.println("\nASC_VALUE : "+ASCF+"  ,  DSC_VALUE : "+DSCF+"\n");
//		if(ASCF == DSCF) {
//			System.out.println("************************************************************ VALUE BALANCED ************************************************************************************");
//		} else if(ASCF > DSCF) {
//			System.out.println("************************************************************ ASC_VALUE by "+(ASCF - DSCF)+" ************************************************************************************");
//		} else {
//			System.out.println("************************************************************ DSC_VALUE by "+(DSCF - ASCF)+" ************************************************************************************");
//		}
		System.out.println("Planet Level :");
		for (String planet : planetFlow) {
			System.out.println(planet);
		}
		System.out.println("\nStar Level :");
		for (String star : starFlow) {
			System.out.println(star);
		}
		System.out.println("\nSub Level :");
		for (String sub : subFlow) {
			System.out.println(sub);
		}
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
				minutes = minutes - 1;
				continue;
			}
			
		}
	
//-------------------------------------------------------------------------------------------------------------------
//bar.setString("Generating PDF....");
// Code to generate PDF
//Document document = new Document(PageSize.A4);
//File f = new File("C://Users//yadav//Desktop//KP_AstroSportsReports//PDF//" + name + ".pdf");
//try
//{
//	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
//	document.open();
//	document.add(new Paragraph("KP Report as per Moon Muhurata"));
//   //Text file Data
//   try (BufferedReader br = new BufferedReader(new FileReader("C://Users//yadav//Desktop//KP_AstroSportsReports//" + name + ".txt"))) {
//          
//          String sCurrentLine;
//
//          while ((sCurrentLine = br.readLine()) != null) {
//              document.add(new Paragraph(sCurrentLine));
//          }
//
//          } catch (IOException exception) {
//          exception.printStackTrace();
//      }
//   
//   document.close();
//   writer.close();
//   
//} catch (DocumentException exception)
//{
//   exception.printStackTrace();
//} catch (FileNotFoundException exception)
//{
//   exception.printStackTrace();
//} 

//-------------------------------------------------------------------------------------------------------------------
bar.setString("Done! :)"); driver.quit();
frame.setVisible(false);
frame.dispose();
	
}}
