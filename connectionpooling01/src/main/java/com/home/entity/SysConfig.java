/**
 * 
 */
package com.home.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * einfache POJO (Plain Old Java Object)
 * 
 * @author Ahmad Alrefai
 */
@Entity
@Table(name = "sys_config")
public class SysConfig {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
    @Column(name = "variable")
    private String variable;

    @Column(name = "value")
    private String value;

    
    public SysConfig() {}

    public SysConfig(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

   
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}