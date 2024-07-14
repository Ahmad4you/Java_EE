package com.home.hibernate.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.home.hibernateCon.entity.Teacher;
import com.home.hibernateCon.entity.TeacherAddresse;


/**
 * One To One - Bidirectional
	 * 
	 * Selber Primary Key der Tabellen
	 * 
	 * Navigieren in beide Richtungen moeglich
	 * -->
	 * Teacher Eigenschaft in TeacherAddresse 
	 * oder auch so geht dass, TeacherAddresse Eigenschaft in Teacher.
	 * 
	 * Verwendung mappedBy: der fuer das aktualisieren der DB zust�ndig ist
	 * 
	 * -----> Darauf achten: TeacherAddresse nutzt selben PK von gespeicherten Teacher
	 * 
 * @author Ahmad Alrefai
 */
public class OneToOneBiDB {

	protected SessionFactory sf;
	
	public void createSF() {
		final StandardServiceRegistry register = new StandardServiceRegistryBuilder().configure().build();
		
		try {
			sf = new MetadataSources(register).buildMetadata().buildSessionFactory();
			System.out.println("SF erstellet");
		} catch (Exception e) {
			 	System.out.println("SF konnte nicht erstellt werden.");
		}
	}
	
	public void cancelSF() {
		System.out.println("sF wurde geschlossen.");
		
		sf.close();
	}
		
	public void createTeacher() {
				
		Teacher teacher = new Teacher("Werner", "Sport");
		TeacherAddresse addresse = new TeacherAddresse("M�nchen", "Hauptstra�e");
		
		Session ses = sf.openSession();
		
		ses.beginTransaction();
		
		teacher.setTeacherAddresse(addresse);
		addresse.setTeacher(teacher);
		
		ses.save(teacher);
		
		
		
		ses.getTransaction().commit();
		ses.close();
	}
	


	
	
}
