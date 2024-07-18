/**
 * 
 */
package com.home.controller;

import com.home.model.Zugangsdaten;
import com.home.repository.ZugangsdatenService;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * 
 * @author Ahmad Alrefai
 */

@Named
@SessionScoped
public class ZugangsdatenManager implements Serializable {

	private static final long serialVersionUID = -7156471325186465263L;

	@Inject
	private ZugangsdatenService zugangsdatenService;

	private Zugangsdaten zugangsdaten = new Zugangsdaten();
	private List<Zugangsdaten> zugangsdatenList;

	public void create() {
		if (zugangsdaten.getCurrentPassword() == null || zugangsdaten.getEmail() == null
			|| zugangsdaten.getCurrentPassword().trim().isEmpty() || zugangsdaten.getEmail().trim().isEmpty()) {
	            addErrorMessage("Vor- und Nachname dürfen nicht leer sein.");
	            return;
			
		}
		
		if (zugangsdatenService.exists(zugangsdaten.getEmail(), zugangsdaten.getCurrentPassword())){
			addErrorMessage("Ein Benutzer mit diesem Zugangsdaten existiert bereits.");
		} else {
			Zugangsdaten nEWzugangsdaten = new Zugangsdaten();
			nEWzugangsdaten.setEmail(zugangsdaten.getEmail());
			nEWzugangsdaten.setCurrentPassword(zugangsdaten.getCurrentPassword());
			nEWzugangsdaten.setPhoneNumber(zugangsdaten.getPhoneNumber());
			
			zugangsdatenService.create(nEWzugangsdaten);
			addInfoMessage("Zugangsdaten erfolgreich erstellt.");
			zugangsdaten = new Zugangsdaten();
			resetForm();
			load();
		}
		
	}

	public void update() {
		if (zugangsdatenService.exists(zugangsdaten.getEmail(), zugangsdaten.getCurrentPassword())
				&& !Objects.equals(zugangsdatenService.findById(zugangsdaten.getId()).getEmail(), zugangsdaten.getEmail())
				&& !Objects.equals(zugangsdatenService.findById(zugangsdaten.getId()).getCurrentPassword(), zugangsdaten.getCurrentPassword())) {
			addErrorMessage("Ein Benutzer mit diesem Namen existiert bereits.");
		} else {
			zugangsdatenService.update(zugangsdaten.getId(), zugangsdaten);
			addInfoMessage("Zugangsdaten erfolgreich erstellt.");
			zugangsdaten = new Zugangsdaten();
			resetForm();
			load();
		}
	}

	public void delete(Long id) {
		zugangsdatenService.delete(id);
		load();
	}

	public void load() {
		zugangsdatenList = zugangsdatenService.findAll();
	}

	public Zugangsdaten getZugangsdaten() {
		return zugangsdaten;
	}

	public void setZugangsdaten(Zugangsdaten zugangsdaten) {
		this.zugangsdaten = zugangsdaten;
	}

	public List<Zugangsdaten> getZugangsdatenList() {
		return zugangsdatenList;
	}

	public void setZugangsdatenList(List<Zugangsdaten> zugangsdatenList) {
		this.zugangsdatenList = zugangsdatenList;
	}

	private void resetForm() {
		zugangsdaten = new Zugangsdaten();
	}

	 private void addErrorMessage(String message) {
	        getFacesContext().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
	    }

	    private void addInfoMessage(String message) {
	        getFacesContext().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
	    }
	    
	    /*
	     * für Arquillian Integrationstest werden benötigt
	     * 
	     * 
	     * 
	     */
	    public void setZugangsdatenService(ZugangsdatenService zugangsdatenService) {
			this.zugangsdatenService = zugangsdatenService;
		}
	    private FacesContext facesContext;

	    protected FacesContext getFacesContext() {
	        return (facesContext != null) ? facesContext : FacesContext.getCurrentInstance();
	    }

	    // Nur für Tests
	    public void setFacesContext(FacesContext facesContext) {
	        this.facesContext = facesContext;
	    }

	    
}
