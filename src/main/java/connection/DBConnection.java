/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aviwembolekwa
 */
public class DBConnection {
    
  public static Connection derbyConnection() throws SQLException{
        String url = "jdbc:derby://localhost:1527/EventRegistration";
        String user = "Administrator";
        String password = "Password";
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }
}
