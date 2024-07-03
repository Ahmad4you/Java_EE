package service;


import java.util.List;

import entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

/*
 * transaction-type="JTA"
 * JPA
 */

@Stateless
public class UserService {
	
	@PersistenceContext(unitName = "AhmadPU")
	private EntityManager entityManager;

	public UserService() {

	}

	public void insert(User user) {
		try {
			entityManager.persist(user);
			// detatched Object
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // Re-throw the exception to inform the caller
		}
	}

//	@Transactional
//	public void editCategory(User category) {
//		if (category.getName() == null || category.getName().isEmpty()) {
//			throw new IllegalArgumentException("Category name cannot be null or empty");
//		}
//
//		try {
//			User foundCategory = entityManager.find(User.class, category.getId());
//			if (foundCategory == null) {
//				throw new EntityNotFoundException("Category with ID " + category.getId() + " not found");
//			}
//
//			// Update the category
//			foundCategory.setName(category.getName());
//			foundCategory.setDescription(category.getDescription());
//
//			// In JPA, changes to managed entities are automatically persisted
//			// No need to call merge() explicitly
//		} catch (EntityNotFoundException e) {
//			// Log the exception
//			e.printStackTrace();
//			throw e; // Re-throw to inform the caller
//		} catch (Exception e) {
//			// Log the exception
//			e.printStackTrace();
//			throw new RuntimeException("Error updating category", e);
//		}
//	}

//	@Transactional
//	public void deleteCategory(int id) {
//		User category = entityManager.find(Category.class, id);
//		if (category != null) {
//			entityManager.remove(category);
//		} else {
//			throw new jakarta.persistence.EntityNotFoundException("Category with id " + id + " not found");
//		}
//
//	}
//
//	@Transactional
//	public Category findCategoryByID(int id) {
//		try {
//			// Using find() which is equivalent to Hibernate's get()
//			Category foundCategory = entityManager.find(Category.class, id);
//
//			// If you want behavior similar to Hibernate's load(), use getReference()
//			// Category foundCategory = entityManager.getReference(Category.class, id);
//
//			return foundCategory;
//		} catch (Exception e) {
//			// Log the exception
//			e.printStackTrace();
//			throw new RuntimeException("Error finding category with ID: " + id, e);
//		}
//	}

//	@Transactional
//	public List<Category> findAllCategoriesNative() {
//		String nativeQuery = "SELECT * FROM categories";
//
//		try {
//			Query query = (Query) entityManager.createNativeQuery(nativeQuery, Category.class);
//			List<Category> categories = query.getResultList();
//
//			return categories;
//		} catch (Exception e) {
//			// Log the exception
//			e.printStackTrace();
//			throw new RuntimeException("Error fetching all categories", e);
//		}
//	}

//	@Transactional
//	public List<Category> findAllCategoriesHQL() {
//		Session currentSession = getCurrentSession();
//		String hqlQuery = "FROM Categories";
//		List<Category> categoriesList = null;
//		// with HQL
//		try {
//			beginTransaktion();
//			TypedQuery<Category> query = currentSession.createQuery(hqlQuery, Category.class);
//			categoriesList = query.getResultList();
//
//			getDbSessionFactory().close();
//		} catch (HibernateException ex) {
//			doRollbackTransaktion();
//			throw ex;
//		}
//		return categoriesList;
//
//	}

	@Transactional
	public List<User> findAllUsersJPQL() {
		String jpqlQuery = "SELECT u FROM User u";
//		String jpqlQuery = "SELECT c FROM userslogin1 c";

		try {
			TypedQuery<User> query = entityManager.createQuery(jpqlQuery, User.class);
			List<User> list = query.getResultList();
			 System.out.println("Found " + list.size() + " users");
			return list;
		} catch (Exception e) {
			System.err.println("Error executing JPQL query: " + jpqlQuery);
			e.printStackTrace();
			throw new RuntimeException("Error fetching all Users using JPQL", e);
		}
	}

//	@Transactional
//	public List<Category> findAllCategoriesCriteria() {
//		try {
//			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//			CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
//			Root<Category> root = criteriaQuery.from(Category.class);
//			criteriaQuery.select(root);
//
//			return entityManager.createQuery(criteriaQuery).getResultList();
//		} catch (Exception e) {
//			// Log the exception
//			e.printStackTrace();
//			throw new RuntimeException("Error fetching all categories using Criteria API", e);
//		}
//	}

	/**
	 * 
	 * es wird die JPA Criteria API anstelle der Hibernate-spezifischen API
	 * verwendet.
	 * 
	 * @param id
	 * @return
	 */

	@Transactional
	public User findCategoryByIDCriteria(int id) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

			return entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (jakarta.persistence.NoResultException e) {
			// Log that no category was found with the given ID
			return null;
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			throw new RuntimeException("Error fetching category by ID using Criteria API", e);
		}
	}

	@Transactional
	public List<User> findByName(String name) {
		try {
			String jpql = "SELECT c FROM userslogin1 c WHERE c.name = :name";
			TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
			query.setParameter("name", name);
			return query.getResultList();
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			throw new RuntimeException("Error fetching Users by name", e);
		}
	}

	@SuppressWarnings("serial")
	public class CategoryNotFoundException extends RuntimeException {
		public CategoryNotFoundException(String message) {
			super(message);
		}
	}

}