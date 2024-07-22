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
@RunAs(Roles.ADMIN)
public class AdminExecutor {
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
