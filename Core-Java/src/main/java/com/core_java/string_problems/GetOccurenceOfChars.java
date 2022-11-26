package com.core_java.string_problems;

import java.util.HashMap;

public class GetOccurenceOfChars 
{
    public static void main( String[] args )
    {
        System.out.println( "This program will print no. of occurance of each character in a given string\n" );
        String str = "ABBCCCDDDDEEEEEFFFFFF   GGGG%%###****";
        
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
       
        for(int i=0;i<=str.length()-1;i++) {
        	char key = str.charAt(i);
        	if(map.containsKey(key)) {
        		int value = map.get(key);
        		map.put(key, value+1);
        	} else {
        		map.put(key, 1);
        	}
        } System.out.println(map);
    }
}
