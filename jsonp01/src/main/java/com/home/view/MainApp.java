/**
 * 
 */
package com.home.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.home.entity.Developer;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/**
 * 
 * @author Ahmad Alrefai
 */
public class MainApp {

	/**
	 * @param args
	 */
	 public static void main(String[] args) {
	        String pathDeveloperFile = "src/main/resources/developer.json";
	        
	        try (InputStream inputStream = new FileInputStream(pathDeveloperFile);
	             JsonReader jsonReader = Json.createReader(inputStream)) {
	            
	            JsonObject object = jsonReader.readObject();
	            
	            Developer developer = new Developer();
	            developer.setId(object.getInt("id"));
	            developer.setName(object.getString("name"));
	            developer.setRole(object.getString("role"));
	            
	            JsonArray arr = object.getJsonArray("language");
	            String[] languages = new String[arr.size()];
	            
	            for (int i = 0; i < arr.size(); i++) {
	                languages[i] = arr.getString(i);
	            }
	            developer.setLanguages(languages);
	            
	            System.out.println(developer.toString());
	            
	        } catch (IOException e) {
	            System.err.println("Error lesen JSON file: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}
