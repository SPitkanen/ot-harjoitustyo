
package wbapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.time.*;

/**
 *
 * @author santeripitkanen
 */
public class AircraftDataDao {
    
    private Connection db;
    
    public AircraftDataDao(Connection db) {
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
        String query = "SELECT COUNT(acid) FROM acDataDepend WHERE acid = ?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, planeId);
        ResultSet rs = s.executeQuery();
        rs.next();
        int count = rs.getInt(1);
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
    
    public boolean saveData(double[][] data, ArrayList<String> items, int planeId, int userId, int count) {
        try {
            String date = Instant.now().toString();
            PreparedStatement s = db.prepareStatement("INSERT INTO results (acid, userid, item, arm, weight, moment, visible, date) VALUES (?, ?, ?, ?, ?, ?, 1, ?)");
            db.setAutoCommit(false);
            for (int i = 0; i < count; i++) {
                s.setInt(1, planeId);
                s.setInt(2, userId);
                s.setString(3, items.get(i));
                s.setDouble(4, data[i][0]);
                s.setDouble(5, data[i][1]);
                s.setDouble(6, data[i][2]);
                s.setString(7, date);
                s.addBatch();
            }
            int[] updated = s.executeBatch();
            db.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public ResultSet getResults(int userid) throws SQLException {
        String query = "SELECT DISTINCT a.register as register, r.date as date, r.acid as acid FROM aircraft a, results r WHERE r.acid=a.id AND r.userid=? AND visible=1";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, userid);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public int getResultCount(int userid) throws SQLException {
        String query = "SELECT COUNT(DISTINCT date) FROM results WHERE userid=?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, userid);
        ResultSet rs = s.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
    
    public ResultSet getLogData(String date, int userId) throws SQLException {
        String query = "SELECT item, arm, weight, moment FROM results WHERE date=? AND visible=1 AND userid=?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setString(1, date);
        s.setInt(2, userId);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public int getLogCount(String date, int userId) throws SQLException {
        String query = "SELECT COUNT(date) FROM results WHERE date=? AND visible=1 AND userid=?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setString(1, date);
        s.setInt(2, userId);
        ResultSet rs = s.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
}
