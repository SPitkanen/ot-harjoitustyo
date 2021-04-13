
package wbapp.dao;

import java.sql.*;

/**
 *
 * @author santeripitkanen
 */
public class acDataDao {
    
    private Connection db;
    
    public acDataDao() {
        createConnection();
    }
    
    public ResultSet getAcData(int planeId) throws SQLException {
        String query = "SELECT item, arm, weight, moment, maxweight FROM acData WHERE acid = ? ORDER BY id ASC";
        PreparedStatement s = db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public ResultSet getAcDataDepend(int planeId) throws SQLException {
        String query = "SELECT item, arm, weight, moment, maxweight, section FROM acDataDepend WHERE acid = ? ORDER BY id ASC";
        PreparedStatement s = db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public int getCount(int planeId) throws SQLException {
        String query = "SELECT COUNT(acid) FROM acData WHERE acid = ?";
        PreparedStatement s = db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
    
    public int getCountDepend(int planeId) throws SQLException { 
        String query2 = "SELECT COUNT(acid) FROM acDataDepend WHERE acid = ?";
        PreparedStatement s2 = db.prepareStatement(query2);
        s2.setInt(1, planeId);
        ResultSet rs2 = s2.executeQuery();
        rs2.next();
        int count = rs2.getInt(1);
        return count;
    }
    
    public void createConnection() {
        try {
            db = DriverManager.getConnection("jdbc:postgresql:weightandbalance");
        } catch (SQLException e) {
            System.out.println("Virhe tietokannassa: " + e);
        }
        
        
    }
}
