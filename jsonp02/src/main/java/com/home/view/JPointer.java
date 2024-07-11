/**
 * 
 */
package com.home.view;

import java.io.IOException;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonPointer;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;

/**
 * 
 * @author Ahmad Alrefai
 */
public class JPointer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try (InputStream is = JPointer.class.getClassLoader().getResourceAsStream("user.json");
                JsonReader jr = Json.createReader(is)) {

            JsonStructure js = jr.read();
            JsonPointer jp = Json.createPointer("/user/profile");
            JsonValue jv = jp.getValue(js);
            System.out.println("profile: " + jv);

        } catch (IOException e) {
			e.getMessage();
		}

	}

}
