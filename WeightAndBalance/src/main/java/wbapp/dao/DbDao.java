
package wbapp.dao;

import java.sql.*;

/**
 *
 * @author santeripitkanen
 */
public class DbDao {
    
    private Connection db;
    
    public DbDao () {
        
    }
    
    public Connection createConnection() {
        try {
            db = DriverManager.getConnection("jdbc:postgresql:weightandbalance");
        } catch (SQLException e) {
            System.out.println("Virhe tietokannassa");
        }
        return db;
    }
}
