/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MarcoMan
 */
public class database {

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // CONNECT YOUR DATABASE
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/makettdb", "root", "P@ssw0rd");
            return connect;
        } catch (ClassNotFoundException | SQLException e) {
            
            
        }

        return null;
    }

}
