package com.collection.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {

	public static void main(String[] args) {
		
		User user1 = new User("UDIT",1);
		User user2 = new User("ABHINAV",2);
		User user3 = new User("UMESH",3);
		User user4 = new User("SACHIN",4);
		
		List<User> list = new ArrayList<User>();
		
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
		
//		Collections.sort(list, new UserIdComparator());
		
		Collections.sort(list, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				
				return o1.getName().compareTo(o2.getName());
 			}
			
		});
		
		for(User u : list) {
			System.out.println(u);
		}

	}

}
