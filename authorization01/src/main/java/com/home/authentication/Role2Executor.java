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
@RunAs(Roles.ROLE2)
public class Role2Executor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}
