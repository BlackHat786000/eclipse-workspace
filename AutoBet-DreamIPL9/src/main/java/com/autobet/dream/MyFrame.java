package com.autobet.dream;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
	
	private static final long serialVersionUID = -1911349032233503000L;
	
	Container c;
	JLabel label0,label1,label2,label3,label4,label5,label6,label7,label8,label9,email,username,password;
	JTextField match_url,PLT,PLO,PLA,RLO,RLA,PBO,PBA,EMAIL,USERNAME;
	JPasswordField PASSWORD;
	JComboBox<String> MODE;
	JButton btn;
	
	MyFrame() {
		
		String mode[] = {"STANDARD LAY","AUTO LAY ANY","REBOOK CUT LOSS","REBOOK AS YOU WANT","LAY THEN BACK CUT LOSS","LAY THEN BACK AS YOU WANT","ONE TIME LAY","ONE TIME BACK"};
		
		setTitle("AutoBet v4.2 - DreamIPL9");
		setSize(470, 480);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(null);
		
		username = new JLabel("USERNAME");
		username.setBounds(10, 15, 100, 25);
		password = new JLabel("PASSWORD");
		password.setBounds(260, 15, 100, 25);
		label0 = new JLabel("ENTER YOUR PREFERENCES TO AUTOBET BOTH SIDES");
		label0.setBounds(10,15,360,40);
		label1 = new JLabel("Enter Match URL");
		label1.setBounds(10,50,100,20);
		label2 = new JLabel("Enter Favorites");
		label2.setBounds(10,80,100,20);
		label3 = new JLabel("Enter LAY Odds");
		label3.setBounds(10,110,100,20);
		label4 = new JLabel("Enter LAY Amount");
		label4.setBounds(10,140,110,20);
		label5 = new JLabel("MODE");
		label5.setBounds(10,200,100,20);
		label6 = new JLabel("REBOOK LAY ODDS");
		label6.setBounds(10,230,120,20);
		label7 = new JLabel("REBOOK LAY AMOUNT");
		label7.setBounds(10,260,140,20);
		label8 = new JLabel("PUNTER BACK ODDS");
		label8.setBounds(10,290,140,20);
		label9 = new JLabel("PUNTER BACK AMOUNT");
		label9.setBounds(10,320,150,20);
		email = new JLabel("Enter Email ID");
		email.setBounds(10,170,110,20);
		
		match_url = new JTextField();
		match_url.setBounds(160,50,280,20);
		PLT = new JTextField();
		PLT.setBounds(160,80,280,20);
		PLO = new JTextField();
		PLO.setBounds(160,110,280,20);
		PLO.setText("1.50");
		PLA = new JTextField();
		PLA.setBounds(160,140,280,20);
		PLA.setText("100");
		RLO = new JTextField();
		RLO.setBounds(160,230,280,20);
		RLO.setEditable(false);
		RLA = new JTextField();
		RLA.setBounds(160,260,280,20);
		RLA.setEditable(false);
		PBO = new JTextField();
		PBO.setBounds(160,290,280,20);
		PBO.setEditable(false);
		PBA = new JTextField();
		PBA.setBounds(160,320,280,20);
		PBA.setEditable(false);
		EMAIL = new JTextField();
		EMAIL.setBounds(160, 170, 280, 20);
		EMAIL.setText("OPTIONAL");
		USERNAME = new JTextField();
		USERNAME.setBounds(90, 15, 100, 25);
		USERNAME.setText("rahu9");
		PASSWORD = new JPasswordField();
		PASSWORD.setBounds(340, 15, 100, 25);
		PASSWORD.setText("Iaminsane@786");
		
		MODE = new JComboBox<String>(mode);
        MODE.setBounds(160,200,280,20);
        
        MODE.addActionListener(new ActionListener() {
            @SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent event) {
              
                // Get the source of the component, which is our combo box.
                JComboBox MODE = (JComboBox) event.getSource();
                
                Object selected = MODE.getSelectedItem();
                if(selected.toString().equals("STANDARD LAY") || selected.toString().equals("ONE TIME LAY")) {
                	RLO.setText("");
                	RLA.setText("");
                	PBO.setText("");
                	PBA.setText("");
                	PLO.setEditable(true);
                	PLA.setEditable(true);
                	PLT.setEditable(true);
	                RLO.setEditable(false);
	                RLA.setEditable(false);
	                PBO.setEditable(false);
	                PBA.setEditable(false);
                } else if(selected.toString().equals("REBOOK CUT LOSS")) {
                	RLO.setText("1.99");
                	RLA.setText("");
                	PBO.setText("");
                	PBA.setText("");
                	PLO.setEditable(true);
                	PLA.setEditable(true);
                	PLT.setEditable(true);
                	RLO.setEditable(true);
                	RLA.setEditable(false);
                	PBO.setEditable(false);
	                PBA.setEditable(false);
                } else if(selected.toString().equals("REBOOK AS YOU WANT")) {
                	RLO.setText("1.70");
                	RLA.setText(PLA.getText());
                	PBO.setText("");
                	PBA.setText("");
                	PLO.setEditable(true);
                	PLA.setEditable(true);
                	PLT.setEditable(true);
                	RLO.setEditable(true);
                    RLA.setEditable(true);
                    PBO.setEditable(false);
	                PBA.setEditable(false);
                } else if(selected.toString().equals("AUTO LAY ANY")) {
                	RLO.setText("");
                	RLA.setText("");
                	PBO.setText("");
                	PBA.setText("");
                	PLO.setEditable(true);
                	PLA.setEditable(true);
                	PLT.setEditable(false);
	                RLO.setEditable(false);
	                RLA.setEditable(false);
	                PBO.setEditable(false);
	                PBA.setEditable(false);
                } else if(selected.toString().equals("LAY THEN BACK AS YOU WANT")) {
                	RLO.setText("");
                	RLA.setText("");
                	PBO.setText("1.65");
                	PBA.setText("1000");
                	PLO.setEditable(true);
                	PLA.setEditable(true);
                	PLT.setEditable(true);
	                RLO.setEditable(false);
	                RLA.setEditable(false);
	                PBO.setEditable(true);
	                PBA.setEditable(true);
                } else if(selected.toString().equals("LAY THEN BACK CUT LOSS")) {
                	RLO.setText("");
                	RLA.setText("");
                	PBO.setText("1.65");
                	PBA.setText("");
                	PLO.setEditable(true);
                	PLA.setEditable(true);
                	PLT.setEditable(true);
	                RLO.setEditable(false);
	                RLA.setEditable(false);
	                PBO.setEditable(true);
	                PBA.setEditable(false);
                } else if(selected.toString().equals("ONE TIME BACK")) {
                	RLO.setText("");
                	RLA.setText("");
                	PBO.setText("");
                	PBA.setText("");
                	PLO.setEditable(false);
                	PLA.setEditable(false);
                	PLT.setEditable(true);
	                RLO.setEditable(false);
	                RLA.setEditable(false);
	                PBO.setEditable(true);
	                PBA.setEditable(true);
                }
            }
        });
        
		btn = new JButton("BOOK & ENJOY");
		btn.setBounds(160,370,150,40);
		
		c.add(username);
		c.add(password);
//		c.add(label0);
		c.add(label1);
		c.add(match_url);
		c.add(label2);
		c.add(PLT);
		c.add(label3);
		c.add(PLO);
		c.add(label4);
		c.add(PLA);
		c.add(label5);
		c.add(MODE);
		c.add(label6);
		c.add(RLO);
		c.add(label7);
		c.add(RLA);
		c.add(label8);
		c.add(PBO);
		c.add(label9);
		c.add(PBA);
		c.add(email);
		c.add(EMAIL);
		c.add(USERNAME);
		c.add(PASSWORD);
		
		c.add(btn);
		
		final MyFrame frame = this;
		btn.addActionListener(
	            new ActionListener(){
	                @Override
	                public void actionPerformed(ActionEvent ev){
	                    synchronized(frame){
	                        frame.notify();
	                    }
	                
	                    frame.setVisible(false);
	                    frame.dispose();
	                }
	            }
	        );
		
		setResizable(false);
		setVisible(true);
		
		synchronized(this){
            try{ 
                this.wait();
            }
            catch(InterruptedException ex){ }
        }
		
	}

}
