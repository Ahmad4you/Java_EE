/**
 * 
 */
package com.home.many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.home.hibernate.db.DBGeneric;
import com.home.hibernateCon.entity.Course;
import com.home.hibernateCon.entity.Student;

/**
 * 
 * @author Ahmad Alrefai
 */
public class Many_To_Many_main {

	/**
	 * Many-to-Many-Beziehung
	 * Die Zwischentabelle student_course wird automatisch von Hibernate erstellt
	 * 
	 * Die @JoinTable in der Student-Klasse definiert die Zwischentabelle:
	 * name = "student_course": Dies ist der Name der Zwischentabelle.
	 * joinColumns = @JoinColumn(name = "student_id"): Dies definiert die Spalte, die auf die Student-Entität verweist.
	 * inverseJoinColumns = @JoinColumn(name = "course_id"): Dies definiert die Spalte, die auf die Course-Entität verweist.
	 * In der Course-Klasse wird mappedBy = "courses" verwendet, um anzuzeigen, dass Student die Beziehung besitzt.
	 * 
	 */
	public static void main(String[] args) {
		SessionFactory factory = DBGeneric.createSF();
	    Session ses = factory.openSession();
	    try {
	    	ses.beginTransaction();
	    	
	    	Student student1 = new Student();
	    	student1.setVorname("Jean");
	    	student1.setNachname("Luc");
	    	Course course1 = new Course();
	    	course1.setBezeichnung("Math");
	    	student1.getCourses().add(course1);
	    	course1.getStudents().add(student1);
	    	
	    	ses.save(student1);
	    	
	    	
	    	ses.getTransaction().commit();
	    } catch (ConstraintViolationException e) {
	        System.out.println("Ein Benutzer mit diesem Vor- und Nachnamen existiert bereits.");
	        // Benutzer auffordern, einen anderen Namen einzugeben
	    } catch (Exception e) {
	        if (ses.getTransaction().isActive()) {
	            ses.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (ses != null) {
	            ses.close();
	        }
	        DBGeneric.shutdown();
	    }
	
		

	}

}
