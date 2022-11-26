package com.core_java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StoreDataJDBC {

	public static void main(String[] args) {

		String latitude;
		String longitude;
		String timezone;

		boolean ex = false;

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Stadium : ");
		String stadium = sc.nextLine();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
			String query = "select * from stadiums where alias = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, stadium);
			ResultSet set = pst.executeQuery();
			while (set.next()) {
				latitude = set.getString(3);
				longitude = set.getString(4);
				timezone = set.getString(5);
//				System.out.println(latitude);
//				System.out.println(longitude);
//				System.out.println(timezone);
				ex = true;
				System.out.println("You have selected stadium : " + set.getString(2));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Something went wrong while retrieving your stadium from database....");
		}

		if (!ex) {
			try {
				System.out.println("Stadium doesn't exist in database....");
				System.out.println("Please enter this stadium in database to continue : \n");
				Scanner sc1 = new Scanner(System.in);
				System.out.print("Enter Stadium Alias : ");
				String stadium_alias = sc1.nextLine();
				System.out.println("Enter Stadium Name : ");
				String stadium_name = sc1.nextLine();
				System.out.print("Enter Latitude : ");
				latitude = sc1.nextLine();
				System.out.print("Enter Longitude : ");
				longitude = sc1.nextLine();
				System.out.print("Enter Timezone : ");
				timezone = sc1.nextLine();
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
				String query = "insert into stadiums(alias,name,latitude,longitude,timezone) values(?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, stadium_alias);
				pst.setString(2, stadium_name);
				pst.setString(3, latitude);
				pst.setString(4, longitude);
				pst.setString(5, timezone);
				pst.executeUpdate();
				System.out.println("Stadium added to database....");
				sc1.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Something went wrong while adding your stadium to database....");
			}
		}
		sc.close();

	}

}
