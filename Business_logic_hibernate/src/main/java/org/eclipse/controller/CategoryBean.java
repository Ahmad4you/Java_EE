/**
 * 
 */
package org.eclipse.controller;

import java.util.List;

import org.eclipse.db.entity.Category;
import org.eclipse.service.CategoryService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author Ahmad Alrefai
 */

@Named
//@RequestScoped
//@SessionScoped
@ApplicationScoped
public class CategoryBean {

	@Inject
	private CategoryService categoriesService;
	
	private List<Category> users;
	private Category currentCategory;

	public Category getCurrentCategory() { // to show currentCategory in facelet
		return currentCategory;
	}

	public void setCurrentCategory(Category currentCategory) {
		this.currentCategory = currentCategory;
	}

	public CategoryBean() {
//		clearCategory();
//		reload();
	}
	
	public void clearCategory() {
		currentCategory = new Category();  // wenn Bean @RequestScoped
	}

	public List<Category> getUsers() {
		if (users == null) {
			try {
				users = categoriesService.findAllCategoriesCriteria();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	public void init() {
		System.out.println("init()....");
	}
	
	private void reload(){
		users = categoriesService.findAllCategoriesCriteria();
	}

	public String creatForm() {
		// some prozesse
		// some Anweisungen
		clearCategory();	
		
		return "CategoryForm";
	}
	
	public String save() {
		if(currentCategory.getId() == null) {
			
			categoriesService.insertCategory(currentCategory);
		} else {
			categoriesService.editCategory(currentCategory);
		}
		reload();
		return "index"; // to return to home page index.xhtml
	}
	
	public String edit(Integer id) {
//		Category findCategory = new Category();
//		findCategory.setId(id);
		currentCategory = categoriesService.findCategoryByIDCriteria(id);
		
		return "CategoryForm";
		
	}
	
	public void delete(Integer id) {
		categoriesService.deleteCategory(id);
		reload();
	}

}
