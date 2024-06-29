/**
 * 
 */
package service;

import java.util.List;

/**
 * @author Ahmad Alrefai
 */
public interface MenuModule<Typ> {
	
//	Categories addCategorie(Categories categories) throws Exception;
//	Categories editCategorie(Categories categories) throws Exception;
//	void deleteCategorie(Categories categorie) throws Exception;
//	List<Categories> findAllCategories() throws Exception;
//	Categories findCategory(Categories categories) throws Exception;
//	List<Categories> searchCategoryByName(String CategoryName) throws Exception;
//	
//	Products addProduct(Products products) throws Exception;
//	Products editProduct(Products products) throws Exception;
//	void deleteProduct(Products products) throws Exception;
//	List<Products> findAllProducts() throws Exception;
//	Products findProduct(Products products) throws Exception;
//	List<Products> searchProductByName(String ProductName) throws Exception;
	
	int addTyp(Typ products) throws Exception;
	int editTyp(Typ products) throws Exception;
	void deleteTyp(Typ products) throws Exception;
	List<Typ> findAllTyps() throws Exception;
	Typ findTyp(int id) throws Exception;
	List<Typ> searchTypByName(String ProductName) throws Exception;
		
	
}
