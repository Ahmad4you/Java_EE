/**
 * 
 */
package com.home.roles;

import jakarta.ejb.EJB;

/**
 * 
 * @author Ahmad Alrefai
 */
public class RoleExecutor {
    
	@EJB
    private AdminExecutor adminExecutor;
    
    @EJB
    private DatenschutzExecutor datenschutzExecutor;
    
    @EJB
    private OperatorExecutor operatorExecutor;
    
    public void runAsAdmin(Executable executable) throws Exception {
        adminExecutor.run(executable);
    }
    
    public void runAsDatenschutz(Executable executable) throws Exception {
        datenschutzExecutor.run(executable);
    }
    
    public void runAsOperator(Executable executable) throws Exception {
        operatorExecutor.run(executable);
    }
}

