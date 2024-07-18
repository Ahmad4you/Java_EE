/**
 * 
 */
package com.home.service.impl;

import java.util.List;

import com.home.model.Zugangsdaten;
import com.home.repository.ZugangsdatenService;
import com.home.service.GeneralService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * 
 * @author Ahmad Alrefai
 */

@ApplicationScoped
public class ZugangsdatenServiceImpl implements ZugangsdatenService {
	@PersistenceContext(unitName = "AhmadPU")
	private EntityManager em;

	@Inject
	private GeneralService<Zugangsdaten> generalService;

	@PostConstruct
	public void init() {
		generalService.setEntityClass(Zugangsdaten.class);
	}

	@Override
	public void create(Zugangsdaten zugangsdaten) {
		
		if (zugangsdaten.getCurrentPassword() == null || zugangsdaten.getEmail() == null
				|| zugangsdaten.getCurrentPassword().trim().isEmpty() || zugangsdaten.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("Passwort und E-Mail dürfen nicht null sein");
		}

		generalService.create(zugangsdaten);
	}

	@Override
	public Zugangsdaten findById(Long id) {
		// TODO Auto-generated method stub
		return generalService.findById(id);
	}

	@Override
	public Zugangsdaten update(Long id, Zugangsdaten updatedEntity) {

		if (updatedEntity.getEmail() == null || updatedEntity.getEmail().trim().isEmpty()
				|| updatedEntity.getCurrentPassword() == null || updatedEntity.getCurrentPassword().trim().isEmpty()) {
			throw new IllegalArgumentException("E-mail und Passwort dürfen nicht leer sein.");
		}

		Zugangsdaten existingEntity = generalService.findById(id);

		if (existingEntity == null) {
			throw new EntityNotFoundException("Zugangsdaten mit ID " + id + " nicht gefunden");
		}
		updateFields(existingEntity, updatedEntity);
		return generalService.update(id, existingEntity);
	}

	private void updateFields(Zugangsdaten existingEntity, Zugangsdaten updatedEntity) {
		if (updatedEntity.getCurrentPassword() != null && !updatedEntity.getCurrentPassword().trim().isEmpty()) {
			existingEntity.setCurrentPassword(updatedEntity.getCurrentPassword());
		}
		if (updatedEntity.getEmail() != null && !updatedEntity.getEmail().trim().isEmpty()) {
			existingEntity.setEmail(updatedEntity.getEmail());
		}
		if (updatedEntity.getPhoneNumber() != null && !updatedEntity.getPhoneNumber().trim().isEmpty()) {
			existingEntity.setPhoneNumber(updatedEntity.getPhoneNumber());
		}

//		 existingEntity.setLastChanged(LocalDateTime.now());
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return generalService.delete(id);
	}

	@Override
	public List<Zugangsdaten> findAll() {
		// TODO Auto-generated method stub
		return generalService.findAll();
	}

	@Override
	public Zugangsdaten findByField(String fieldName, Object fieldValue) {
		// TODO Auto-generated method stub
		return generalService.findByField(fieldName, fieldValue);
	}

	@Override
	public boolean exists(String email, String passwort) {
		TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(u) FROM Zugangsdaten u WHERE u.email = :email AND u.current_password = :current_password",
				Long.class);
		query.setParameter("email", email);
		query.setParameter("current_password", passwort);
		return query.getSingleResult() > 0;
	}

}
