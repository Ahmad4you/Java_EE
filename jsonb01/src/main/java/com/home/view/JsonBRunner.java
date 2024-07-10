/**
 * 
 */
package com.home.view;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.home.entity.User;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;

/**
 * 
 * @author Ahmad Alrefai
 */
public class JsonBRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		cal.set(2024, Calendar.JANUARY, 1); // Beachte: Monate beginnen bei 0
		Date date = cal.getTime();

		User user = new User(2L, "Ahmad", "ahmad.alrefai@outlook.de", 1111111.01, date);

		try (Jsonb jb = JsonbBuilder.create()) {
			String jsonUser = jb.toJson(user);
			User u = jb.fromJson(jsonUser, User.class);

			jb.close();
			System.out.println("json: " + jsonUser);
			System.out.println("user: " + u);
		} catch (JsonbException e) {
			System.err.println("Fehler bei der JSON-Verarbeitung: " + e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
