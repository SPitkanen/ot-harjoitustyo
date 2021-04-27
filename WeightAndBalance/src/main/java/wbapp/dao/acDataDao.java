
package wbapp.dao;

import java.sql.*;

/**
 *
 * @author santeripitkanen
 */
public class AcDataDao {
    
    private Connection db;
    
    public AcDataDao(Connection db) {
        this.db = db;
    }
    
    public ResultSet getAcData(int planeId) throws SQLException {
        String query = "SELECT item, arm, weight, moment, maxweight FROM acData WHERE acid = ? ORDER BY id ASC";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public ResultSet getAcDataDepend(int planeId) throws SQLException {
        String query = "SELECT item, arm, weight, moment, maxweight, section, operation FROM acDataDepend WHERE acid = ? ORDER BY id ASC";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public int getCount(int planeId) throws SQLException {
        String query = "SELECT COUNT(acid) FROM acData WHERE acid = ?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
    
    public int getCountDepend(int planeId) throws SQLException { 
        String query2 = "SELECT COUNT(acid) FROM acDataDepend WHERE acid = ?";
        PreparedStatement s2 = this.db.prepareStatement(query2);
        s2.setInt(1, planeId);
        ResultSet rs2 = s2.executeQuery();
        rs2.next();
        int count = rs2.getInt(1);
        return count;
    }
    
    public ResultSet getAircrafts() throws SQLException {
        String query = "SELECT id, type, register FROM aircraft ORDER BY register";
        PreparedStatement s = this.db.prepareStatement(query);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public ResultSet getChart(int planeId) throws SQLException {
        String query = "SELECT xstart, xstop, ystart, ystop, xspacing, yspacing FROM wbtemplate WHERE planeid=?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public ResultSet getEnvelope(int planeId) throws SQLException {
        String query = "SELECT x, y FROM wbenvelope WHERE planeid=?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public ResultSet getEnvelopeCount(int planeId) throws SQLException {
        String query = "SELECT COUNT(id) AS count FROM wbenvelope WHERE planeid=?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
}
