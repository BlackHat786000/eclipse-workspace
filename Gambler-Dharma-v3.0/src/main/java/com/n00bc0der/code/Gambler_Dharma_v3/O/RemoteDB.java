package com.n00bc0der.code.Gambler_Dharma_v3.O;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RemoteDB {

	public static void main(String[] args) {
		
		System.out.println("************ Update Timezone Utility ************");
		
		Scanner sc= new Scanner(System.in);
		
		System.out.print("\nEnter Stadium Alias / Stadium Name : ");
		String stadium = sc.nextLine();
		
		System.out.print("\nEnter Updated Timezone : ");
		String timezone = sc.nextLine();
		
		sc.close();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6461715", "sql6461715", "a5E1HjtJhL");
			String query = "UPDATE Stadiums SET timezone = ? WHERE alias = ? or name like ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, timezone);
			pst.setString(2, stadium);
			pst.setString(3, stadium);
			pst.executeUpdate();
			System.out.println("\nTimezone Updated............");
			Thread.sleep(3000);
			con.close();
		} catch (Exception e) {
			System.out.println("\nHuh huh  _(-_-)  Something went wrong while updating timezone for stadium in database............\n");
			System.out.println(e.toString());
		}

	}

}
