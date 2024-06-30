package org.eclipse.view;

import java.util.List;

import org.eclipse.db.HibernateSessionManager;
import org.eclipse.db.entity.Category;
import org.eclipse.service.CategoryService;

public class Runner {

	public static void main(String[] args) {
		
		 try {
	            // Setzen Sie den Pfad zu Ihrer Hibernate-Konfigurationsdatei
	            HibernateSessionManager.setConfigFilename("javaStorehibernate.cfg.xml");
	            // Initialisieren Sie die SessionFactory
	            HibernateSessionManager.buildSessionFactory();

	            // Erstellen Sie eine Instanz von CategoryService
//	            CategoryService categoryService = new CategoryService();

	            // Beispiel: Erstellen einer neuen Kategorie
	            Category newCategory = new Category();
	            newCategory.setName("Electronics");
//	            categoryService.insertCategory(newCategory);
//
//	            // Beispiel: Abrufen aller Kategorien
//	            List<Categories> categories = categoryService.findAllCategoriesNative();
//	            categories.forEach(category -> System.out.println(category.getName()));

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            // Schließen Sie die SessionFactory, wenn sie nicht mehr benötigt wird
	            if (HibernateSessionManager.getSessionFactory() != null) {
	                HibernateSessionManager.getSessionFactory().close();
	            }
	        }

	}

}
