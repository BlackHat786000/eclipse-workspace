package com.ds.problems;

public class DeleteArrFromArr {
	
	public static void main(String[] args) {
		
		String[] del = {"vivek","suresh","kamesh","umesh"};
		
		String[] arr = {"abhinav","sachin","mahesh","virat","ramesh","suresh","dinesh","kamesh","tarun","umesh","vivek"};
		
		for(int i=0; i<del.length; i++) {
			for(int j=0; j<arr.length; j++) {
				if(del[i].equals(arr[j])) {
					if(j==arr.length-1) {
						arr[j]=null;
					}
					for(int d=j; d<arr.length-1;d++) {
						arr[d] = arr[d+1];
						arr[d+1]=null;
					}
					
				}
			}
		}
		
		for(int f=0;f<arr.length;f++) {
			System.out.println(arr[f]);
		}
		
	}

}
