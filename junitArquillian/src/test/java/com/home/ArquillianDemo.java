/**
 * 
 */
package com.home;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.home.controller.UserManager;

import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 
 * @author Ahmad Alrefai
 */

@ExtendWith(ArquillianExtension.class)
public class ArquillianDemo {
	 @Inject
//	    private YourBean yourBean;
	 private UserManager yourBean;

	    @Deployment
	    public static JavaArchive createDeployment() {
	        return ShrinkWrap.create(JavaArchive.class)
	                .addClass(UserManager.class)
	                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
	    }

	    @Test
	    public void testYourBean() {
	        assertNotNull(yourBean);
	        // Weitere Testlogik hier
	    }
	}


