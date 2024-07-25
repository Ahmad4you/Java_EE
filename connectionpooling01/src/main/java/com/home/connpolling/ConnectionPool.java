/**
 * 
 */
package com.home.connpolling;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Klasse stellt eine Methode bereit, um eine Datenbankverbindung aus einem JNDI-Ressourcenpool zu erhalten.
 * Wiederverwendung von Verbindungen spart Zeit.
 * Ressourcenmanagement: Begrenzt die Anzahl der gleichzeitigen Datenbankverbindungen.
 * 
 * @author Ahmad Alrefai
 */
public class ConnectionPool {

    public static Connection getConnection() throws SQLException, NamingException {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jdbc/MysqlPool");

        return ds.getConnection();
    }

}