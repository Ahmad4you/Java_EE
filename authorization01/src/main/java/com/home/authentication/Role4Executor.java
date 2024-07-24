/**
 * 
 */
package com.home.authentication;

import jakarta.annotation.security.RunAs;
import jakarta.inject.Named;

/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@RunAs(Roles.ROLE4)
public class Role4Executor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
