/**
 * 
 */
package com.home.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.inject.Qualifier;

/**
 * 
 * @author Ahmad Alrefai
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultRegistry {
	
}
