package com.msdt.carrental.model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Ahmad Alrefai
 * 
 */
public interface GenericMapper <T>{

	T rowMapper(ResultSet resultSet) throws SQLException ;
}
