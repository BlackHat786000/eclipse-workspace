package com.autobet.sky1exchange;

import com.email.durgesh.Email;

public class Mail {

	static int notify = 0;
	static int alert = 0;

	public static void sendmail(String bet_type, String home_team_name, String away_team_name, String favs_team_name,
			float favs_odds, float punter_amount, String mode, String mail) {

		try {
			Email email = new Email("udit01061998@gmail.com", "bhajjiterminator");
			email.setFrom("autobet786@gmail.com", "AutoBet Notification - " + (++notify));
			email.setSubject(home_team_name + " vs " + away_team_name);
			email.setContent("<h1>This is to inform you that your " + bet_type + " bet has been successfully placed on "
					+ favs_team_name + " @ " + favs_odds + " for amount " + punter_amount + " Rs.</h1><br><h4>MODE : "
					+ mode + "</h4>", "text/html");
			email.addRecipient(mail);
			email.send();
		} catch (Exception e) {
			System.out.println("\nHuh huh   <(-_-)>   Something got messed up in Email Sending Mechanism....");
			System.out.println(
					"\n----------------------------------------------------------------------------------------------------------------\n");
			e.printStackTrace();
			System.out.println(
					"\n----------------------------------------------------------------------------------------------------------------\n");
		}

	}

	public static void sendalert(String home_team_name, String away_team_name, String mode, String mail) {
		if (alert < 6) {
			try {
				Email email = new Email("udit01061998@gmail.com", "bhajjiterminator");
				email.setFrom("autobet786@gmail.com", "AutoBet Alert " + (++alert));
				email.setSubject(home_team_name + " vs " + away_team_name);
				email.setContent(
						"<h1>Alert Notification - This is to inform you that something got messed up in AUTOBET FLOW....</h1><br><h4>MODE : "
								+ mode + "</h4>",
						"text/html");
				email.addRecipient(mail);
				email.send();
			} catch (Exception a) {
				System.out.println("\nHuh huh   <(-_-)>   Something got messed up in Email Sending Mechanism....");
				System.out.println(
						"\n----------------------------------------------------------------------------------------------------------------\n");
				a.printStackTrace();
				System.out.println(
						"\n----------------------------------------------------------------------------------------------------------------\n");
			}
		}
	}

}
