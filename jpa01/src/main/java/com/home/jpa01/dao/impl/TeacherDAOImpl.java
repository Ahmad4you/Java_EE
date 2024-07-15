/**
 * 
 */
package com.home.jpa01.dao.impl;

import com.home.entity.Teacher;
import com.home.jpa01.dao.DAO;

/**
 * 
 * @author Ahmad Alrefai
 */
public class TeacherDAOImpl extends GeneralDAO<Teacher> implements DAO<Teacher> {

	@Override
	public Teacher create(Teacher teacher) {
		
		return super.create(teacher);
	}

	@Override
	public Teacher read(Class<Teacher> classType, Long id) {
		return super.read(classType, id);
	}

	@Override
	public boolean delete(Class<Teacher> classType, Long id) {
		return super.delete(classType, id);
	}

	@Override
	public Teacher update(Class<Teacher> classType, Long id, Teacher updatedTeacher) {
	    return super.update(classType, id, updatedTeacher);
	}

	@Override
	protected void updateFields(Teacher existingTeacher, Teacher updatedTeacher) {
	    if (updatedTeacher.getName() != null) {
	        existingTeacher.setName(updatedTeacher.getName());
	    }
	    if (updatedTeacher.getFachbereich() != null) {
	        existingTeacher.setFachbereich(updatedTeacher.getFachbereich());
	    }
	    // Fügen hier weitere Felder hinzu
	}

	
	
	

}
