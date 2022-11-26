package com.n00bC0der.TestYourCode;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.email.durgesh.Email;

public class JavaMail {

	public static void main(String[] args) {
		try {
			Email email = new Email("autobet786@gmail.com", "bhajjiterminator");
			email.setFrom("astro-sports@gmail.com", "ASTRO-SPORTS");
			email.setSubject("Hello....for testing purpose");
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText("This is message body");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "C:\\Users\\yadav\\Desktop\\Gambler_Dharma\\test.txt";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("test.txt");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			email.setContent(multipart, "");
			// email.setContent("<h1>This is to inform you that this is just a testing
			// mail</h1>", "text/html");
			email.addRecipient("yadavudit786@gmail.com");
			email.send();
		} catch (Exception e1) {
			System.out.println("Huh huh  _(-_-)  Something got really messed up in Email Sending Mechanism....\n");
			System.out.println(e1);
		}

	}

}
