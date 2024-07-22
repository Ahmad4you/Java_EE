/**
 * 
 */
package com.home.beans;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.SelectEvent;

import com.home.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * 
 * @author Ahmad Alrefai
 */

@Named
@ViewScoped
public class UserManagementBean implements Serializable{
    
    private static final long serialVersionUID = -2630706860799319444L;

	/*
	 * Die @EJB-Annotation ist nicht serialisierbar. 
	 * das Feld als transient markieren und einen Getter mit der @EJB-Annotation verwenden
	 */
//    private transient UserBean userBean;
    @Inject
    private UserBean userBean;
    
    private List<User> users;
    private User selectedUser;
    private User newUser = new User();
    
    @PostConstruct
    public void init() {
        users = userBean.get();
        selectedUser = new User();
    }
    
    public void addUser() {
        try {
            userBean.add(newUser);
            users = userBean.get();
            newUser = new User();
            loadUsers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User added successfully"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error adding user", e.getMessage()));
        }
    }
    
    public void updateUser() {
        try {
        	if (selectedUser != null) {
        		
            userBean.update(selectedUser);
            users = userBean.get();
            loadUsers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User updated successfully"));
        	}
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating user", e.getMessage()));
        }
    }
    
    public void deleteUser() {
        try {
        	if (selectedUser != null) {
        		
            userBean.remove(selectedUser);
            users = userBean.get();
            loadUsers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User deleted successfully"));
            selectedUser = null;
        	}
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error deleting user", e.getMessage()));
        }
    }

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public void selectUser(User user) {
        this.selectedUser = user;
    }

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	
	private void loadUsers() {
		users = userBean.get();
    }
	
	public void onUserSelect(SelectEvent<User> event) {
	    this.selectedUser = event.getObject();
	}


//	public UserBean getUserBean() {
//		return userBean;
//	}
//	@EJB
//	public void setUserBean(UserBean userBean) {
//		this.userBean = userBean;
//	}
    
    
    
    
}
