/**
 * 
 */
package com.home.geschaeftsprozess;

/**
 * 	
 * Bestellung erstellt
 * Zahlung überprüft
 * Lagerbestand überprüft
 * Versand vorbereitet
 * Bestellung versandt
 * 
 * @author Ahmad Alrefai
 */
public enum OrderStatus {
    CREATED, PAYMENT_VERIFIED, INVENTORY_CHECKED, SHIPMENT_PREPARED, SHIPPED
}


