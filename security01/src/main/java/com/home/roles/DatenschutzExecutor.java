/**
 * 
 */
package com.home.roles;

import jakarta.annotation.security.RunAs;
import jakarta.ejb.Stateless;

/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
@RunAs(Roles.DATENSCHUTZ)
public class DatenschutzExecutor {
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}