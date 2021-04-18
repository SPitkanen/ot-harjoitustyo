
package wbapp.dao;

import java.sql.*;

/**
 *
 * @author santeripitkanen
 */
public class UserDataDao {
    
    private Connection db;
    
    public UserDataDao(Connection db) {
        this.db = db;
    }
    
    public ResultSet getUserInfo(String name, String password) throws SQLException {
        String query = "SELECT id, name FROM users WHERE name = ? AND password = ?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setString(1, name);
        s.setString(2, password);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public ResultSet newUser(String name, String password) throws SQLException {
        String query = "INSERT INTO users (name, password, role, visible VALUES (?, ?, 0, 1) RETURNING id";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setString(1, name);
        s.setString(2, password);
        s.executeUpdate();
        ResultSet rs = s.getResultSet();
        return rs;
    }
    
    public ResultSet delUser(Integer id) throws SQLException {
        String query = "UPDATE users SET visible=0 WHERE id=? RETURNING id";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setInt(1, id);
        s.executeUpdate();
        ResultSet rs = s.getResultSet();
        return rs;
    }
    
    public ResultSet checkName(String name) throws SQLException {
        String query = "SELECT id FROM users WHERE name = ?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setString(1, name);
        ResultSet rs = s.executeQuery();
        return rs;
    }
    
    public ResultSet checkPassword(String password) throws SQLException {
        String query = "SELECT id FROM users WHERE password = ?";
        PreparedStatement s = this.db.prepareStatement(query);
        s.setString(1, password);
        ResultSet rs = s.executeQuery();
        return rs;
    }
}
