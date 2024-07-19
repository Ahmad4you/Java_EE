/**
 * 
 */
package com.home.batch01;

import java.util.logging.Logger;

import jakarta.batch.api.listener.JobListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@Dependent
public class UserJobListener implements JobListener {

    private static final Logger LOGGER = Logger.getLogger(UserJobListener.class.getName());

    @Override
    public void beforeJob() throws Exception {
        LOGGER.info("User Job is starting");
    }

    @Override
    public void afterJob() throws Exception {
        LOGGER.info("User Job has completed");
    }
}
