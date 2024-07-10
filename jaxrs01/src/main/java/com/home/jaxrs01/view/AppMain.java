/**
 * 
 */
package com.home.jaxrs01.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.home.jaxrs01.model.Student;

/**
 * 
 * @author Ahmad Alrefai
 */
public class AppMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Javascript Object Notation
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("key", "value");
//		jsonObject.put("name", "Ahmad");
//		jsonObject.put("Alter", 30);
//		
//		System.out.println(jsonObject);
		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("key", "value");
//		map.put("name", "Ahmad");
//		map.put("Alter", "30");
//		JSONObject jsonObject = new JSONObject(map);
//		
//		System.out.println(jsonObject);
//		System.out.println("Ausgabe aus dem JSON Object: " + jsonObject.get("name"));
//		System.out.println("Ausgabe aus dem JSON Object: " + jsonObject.get("Alter"));
		
//		Student ahmadStudent = new Student("Ahmad", 33);
//		JSONObject jsonObject = new JSONObject(ahmadStudent);
//		
//		JSONArray array= new JSONArray();
//		array.put(0);
//		array.put(jsonObject);
//		
//		System.out.println(array.opt(0));
//		System.out.println(jsonObject);
		
		
		
		List<String> vornamenList = new ArrayList<String>(Arrays.asList("Ahmad", "Diana", "Lisa")); // collection
		JSONArray array= new JSONArray(vornamenList); // collection
		System.out.println(array);
	}

	
	
	
	
	
	
	
}
