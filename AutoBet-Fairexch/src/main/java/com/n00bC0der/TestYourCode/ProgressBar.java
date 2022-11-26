package com.n00bC0der.TestYourCode;

import java.awt.*;
import javax.swing.*;

public class ProgressBar {
	
	JFrame frame = new JFrame();
	JProgressBar bar = new JProgressBar(0,100);
	
	ProgressBar() throws InterruptedException {
		
		bar.setValue(0);
		bar.setBounds(0,0,420,50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli", Font.BOLD, 25));
		bar.setForeground(Color.green);
		bar.setBackground(Color.red);
		
		frame.add(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
		
		fill();
	}
	
	public void fill() throws InterruptedException {
		
		for(int i = 3 ; i <= 21 ; i = i + 3) {
			
			bar.setValue(i);
			Thread.sleep(1000);
		}
		bar.setString("Done! :)");
	}

}
