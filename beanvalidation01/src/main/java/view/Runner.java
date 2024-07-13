package view;


import static db.HibernateDBManager.*;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import entity.Benutzer;
import service.BenutzerService;


public class Runner {

	public static void main(String[] args) {
		
		/*
		 * Container Weld SE verwenden. Dies würde es ermöglichen, CDI-Beans in einer SE-Umgebung zu verwenden.
		 */
		Weld weld = null;
		try {
			weld = new Weld();
			WeldContainer container = weld.initialize();

			BenutzerService bs = container.select(BenutzerService.class).get();
			
//			BenutzerService bs = new BenutzerService();
//			String configfile = "javaStorehibernate.cfg.xml";
//			
//			setDbConfigFilename(configfile);
//			buildSessionFactory();
			
			Benutzer benutzer = new Benutzer();
			benutzer.setName("junit test4");
			benutzer.setEmail("test44@example.com");
			benutzer.setPassword("passwordert4");

			bs.createUser(benutzer);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (weld != null) {
				weld.shutdown();
			}
		}

	}

}
