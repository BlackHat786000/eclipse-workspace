package com.n00bC0der.TestYourCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FunctionalInterface
interface fin {
	public abstract int learn_lambda(int i);
}
public class Main {
	
	static int i = 0;
	static int notify=0;
	
	static void changei() {
		System.out.println(++i);
	}

	public static void main(String[] args) {
		
		fin f = i -> i*i;
//		fin f = i -> { return i*i; };
		System.out.println(f.learn_lambda(5));
	
//	String s = "Bet";
//	
//	if(s.contains("Bet Place Successfully")) {
//		System.out.println("True");
//	} else {
//		System.out.println("False");
//	}

//	Main.changei();
//	Main.changei();
//	Main.changei();
	
//	try {
//		Email email = new Email("udit01061998@gmail.com", "bhajjiterminator");
//		email.setFrom("udit01061998@gmail.com", "AutoBet Notification - "+(++notify));
//		email.setSubject("India"+" vs "+"Pakistan");
//		email.setContent("<h1>This is to inform you that your laying bet has been successfully placed on "+"India"+" @ "+"1.50"+" for amount "+"1000"+" Rs.</h1><br><h4>MODE : "+"STANDARD"+"</h4>", "text/html");
//		email.addRecipient("yadavudit786@gmail.com");
//		email.send();
//	} catch (Exception e) {
//		System.out.println("Huh huh   <(-_-)>   Something got messed up in Email Sending Mechanism....");
//	}
		
//		while(true) {
//			System.out.println(++i);
//			if(i==2) {
//				if(i==2) {
//					if(i==2) {
//						if(i==2) {
//							if(i==2) {
//								if(i==2) {
//									if(i==2) {
//										break;
//									}
//								}
//							}
//						}
//					}
//				}
//				
//			}
//			
//		}
		
//		System.out.println("Hello World");
//		
//		try {
//			Email email = new Email("autobet786@gmail.com", "bhajjiterminator");
//			email.setFrom("autobet786@gmail.com", "AutoBet Alert - 1");
//			email.setSubject("INDIA"+" vs "+"PAKISTAN");
//			email.setContent("<h1>Alert Notification - This is to inform you that something got messed up in AUTOBET FLOW....</h1><br><h4>MODE : "+"STANDARD"+"</h4>", "text/html");
//			email.addRecipient("yadavudit786@gmail.com");
//			email.send();
//		} catch (Exception a) {
//			System.out.println("\nHuh huh   <(-_-)>   Something got messed up in Email Sending Mechanism....");
//			System.out.println("\n----------------------------------------------------------------------------------------------------------------\n");
//			a.printStackTrace();
//			System.out.println("\n----------------------------------------------------------------------------------------------------------------\n");
//		}
		
		String email = "yadavudit786@gmail.com";
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		System.out.println(email +" : "+ matcher.matches());
	
}
	
}
