package com.n00bc0der.code.Gambler_Dharma_v3.O;

//Java program to access the MAC address of the local host machine
import java.net.InetAddress;
import java.net.NetworkInterface;

public class MACAddress {

	public static void main(String[] args) throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		NetworkInterface iface = NetworkInterface.getByInetAddress(addr);
		byte[] mac = iface.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? i+99 : ""));
		}
		String uid = sb.reverse().toString();
		System.out.println(uid);

	}
}
