/**
 * 
 */
package com.home.batch01;

import java.util.List;
import java.util.logging.Logger;

import com.home.model.User;

import jakarta.annotation.Resource;
import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;



/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@Dependent
public class UserWriter extends AbstractItemWriter {
	 private static final Logger LOGGER = Logger.getLogger(UserWriter.class.getName());


    @PersistenceContext(unitName = "AhmadPU")
    EntityManager entityManager;
    
    @Resource
    UserTransaction userTransaction;
    
//    @Override
//    @Transactional
//    public void writeItems(List list) {
//        for (User user : (List<User>) list) {
//            entityManager.persist(user);
//        }
//    }
    
    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void writeItems(List<Object> items) throws Exception {
        LOGGER.info("Starting writeItems method");
        try {
            for (Object item : items) {
                if (item instanceof User) {
                    User user = (User) item;
                    LOGGER.info("Attempting to persist user: " + user);
                    entityManager.persist(user);
                    LOGGER.info("User persisted successfully");
                } else {
                    LOGGER.warning("Skipping non-User item: " + item);
                }
            }
            
            LOGGER.info("Attempting to flush " + items.size() + " items to the database");
            entityManager.flush();
            LOGGER.info("Flush completed successfully");
        } catch (Exception e) {
            LOGGER.severe("Error while persisting users: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        LOGGER.info("Completed writeItems method");
    }
    
//    @Override
//    public void writeItems(List<Object> items) throws Exception {
//        LOGGER.info("Starting writeItems method");
//        try {
//            userTransaction.begin();
//            
//            for (Object item : items) {
//                if (item instanceof User) {
//                    User user = (User) item;
//                    LOGGER.info("Attempting to persist user: " + user);
//                    entityManager.persist(user);
//                    LOGGER.info("User persisted successfully");
//                } else {
//                    LOGGER.warning("Skipping non-User item: " + item);
//                }
//            }
//            
//            LOGGER.info("Attempting to flush " + items.size() + " items to the database");
//            entityManager.flush();
//            LOGGER.info("Flush completed successfully");
//            
//            userTransaction.commit();
//            LOGGER.info("Transaction committed successfully");
//        } catch (Exception e) {
//            LOGGER.severe("Error while persisting users: " + e.getMessage());
//            e.printStackTrace();
//            try {
//                userTransaction.rollback();
//                LOGGER.info("Transaction rolled back due to error");
//            } catch (Exception rollbackEx) {
//                LOGGER.severe("Error during transaction rollback: " + rollbackEx.getMessage());
//                rollbackEx.printStackTrace();
//            }
//            throw e;
//        }
//        LOGGER.info("Completed writeItems method");
//    }
//    
    
    
}