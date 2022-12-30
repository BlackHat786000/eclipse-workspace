package com.collection.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ComparableExample {

	public static void main(String[] args) {
		
		User u1 = new User("udit",1);
		User u2 = new User("yadav",2);
		User u3 = new User("abhinav", 7);
		
		List<User> listuser = new ArrayList<User>();
		
		listuser.add(u1);
		listuser.add(u2); // u2.compareTo(u1)
		listuser.add(u3);
		
		Collections.sort(listuser);
		
//		for(User u : listuser) {
//			System.out.println(u);
//		}
		
		Iterator<User> itr = listuser.iterator();
		
		while(itr.hasNext()) {
			Object objuser = itr.next();
			System.out.println(objuser);
		}
		
		

	}

}
