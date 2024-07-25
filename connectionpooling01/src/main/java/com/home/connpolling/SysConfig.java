/**
 * 
 */
package com.home.connpolling;

/**
 * 
 * @author Ahmad Alrefai
 */
public class SysConfig {

    private final String variable;
    private final String value;

    public SysConfig(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public String getValue() {
        return value;
    }

}