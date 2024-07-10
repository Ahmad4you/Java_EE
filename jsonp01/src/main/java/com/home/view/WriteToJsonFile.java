/**
 * 
 */
package com.home.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.home.entity.Developer;

/**
 * 
 * @author Ahmad Alrefai
 */
public class WriteToJsonFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String pathDeveloperFile = "C:\\dev\\developer.json";

		Developer developer = new Developer();
		developer.setId(1223);
		developer.setName("Ahmad Meister");
		developer.setRole("Developer");
		developer.setLanguages(new String[] { "Java", "CSS", "HTML", "JavaScript" });

		JSONArray jsonArray = new JSONArray();
		for (String language : developer.getLanguages()) {
			jsonArray.put(language);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", developer.getId()).put("name", developer.getName()).put("role", developer.getRole())
				.put("languages", developer.getLanguages());
		System.out.println(jsonObject);

		try (Writer writer = new FileWriter(pathDeveloperFile)) {
			writer.write(jsonObject.toString());
			writer.close();
		} catch (IOException e) {
			 System.err.println("Error schreiben JSON file: " + e.getMessage());
		}

	}

}
