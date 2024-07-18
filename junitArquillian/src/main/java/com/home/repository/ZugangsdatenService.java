/**
 * 
 */
package com.home.repository;

import java.util.List;

import com.home.model.Zugangsdaten;

/**
 * 
 * @author Ahmad Alrefai
 */
public interface ZugangsdatenService extends GenericService<Zugangsdaten>{
	void create(Zugangsdaten zugangsdaten);
    public Zugangsdaten findById(Long id);
	public Zugangsdaten update(Long id, Zugangsdaten user);
	public boolean delete(Long id);
	List<Zugangsdaten> findAll();

	Zugangsdaten findByField(String fieldName, Object fieldValue);
	boolean exists(String email, String passwort);
}