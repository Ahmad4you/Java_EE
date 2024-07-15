/**
 * 
 */
package com.home.jpa01;

import com.home.entity.Teacher;
import com.home.jpa01.dao.impl.TeacherDAOImpl;

/**
 * 
 * @author Ahmad Alrefai
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TeacherDAOImpl teacherDAOImpl = new TeacherDAOImpl();
//		Teacher teacher = new Teacher("Herr Simon", "Informatik");
//		teacherDAOImpl.create(teacher);
//		System.out.println(teacher.toString());
	
		
//		 boolean deleted = teacherDAOImpl.delete(Teacher.class, 3L);
//		    System.out.println("Delete operation result: " + deleted);

		Teacher updatedTeacher = new Teacher();
		updatedTeacher.setName("Diana");
		updatedTeacher.setFachbereich("Neues Fach");
		teacherDAOImpl.update(Teacher.class, 5L, updatedTeacher);
		
		    Teacher teacher2 = teacherDAOImpl.read(Teacher.class, 5L);
		    if (teacher2 == null) {
		        System.out.println("Teacher no longer exists in the database.");
		    } else {
		        System.out.println("Teacher still exists: " + teacher2);
		    }



	}

}
