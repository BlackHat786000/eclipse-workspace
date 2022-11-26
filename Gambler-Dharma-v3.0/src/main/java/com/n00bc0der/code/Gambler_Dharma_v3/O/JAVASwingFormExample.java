//package com.n00bc0der.code.Gambler_Dharma_v3.O;
//
//import java.awt.Container;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//
//class MyFrame extends JFrame implements ActionListener {
//	
//	Container c;
//	JLabel label1,label2;
//	JTextField user,password;
//	JButton btn;
//	
//	volatile boolean flag = true;
//
//	MyFrame() {
//		
//		setTitle("LoginForm");
//		
//		setSize(400, 300);
//		setLocation(100, 100);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		
//		c = getContentPane();
//		c.setLayout(null);
//		
//		label1 = new JLabel("username");
//		label2 = new JLabel("password");
//		
//		label1.setBounds(10,50,100,20);
//		label2.setBounds(10,100,100,20);
//		
//		user = new JTextField();
//		user.setBounds(120,50,120,20);
//		password = new JTextField();
//		password.setBounds(120,100,120,20);
//		
//		btn = new JButton("Login");
//		btn.setBounds(100,150,70,20);
//		
//		c.add(label1);
//		c.add(label2);
//		c.add(user);
//		c.add(password);
//		c.add(btn);
//		
//		btn.addActionListener(this);
//		
//		setVisible(true);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
////		System.out.println("username : "+user.getText());
////		System.out.println("password : "+password.getText());
//		flag = false;
//		this.dispose();
//		
//	}
//}
//
//public class JAVASwingFormExample {
//	public static void main(String[] args) {
//		MyFrame frame = new MyFrame();
//		while(frame.flag) {
//			
//		}
//		
//		System.out.println("username : "+frame.user.getText());
//		System.out.println("password : "+frame.password.getText());
//		for(int i=1;i<=100;i++) {
//			System.out.print("hi");
//		}
//	}
//}