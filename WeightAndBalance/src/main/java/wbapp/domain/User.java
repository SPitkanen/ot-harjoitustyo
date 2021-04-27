
package wbapp.domain;

import java.sql.*;
import wbapp.dao.UserDataDao;

/**
 *
 * @author santeripitkanen
 */
public class User {
    private UserDataDao userdata;
    private Integer userId;
    private String userName;
    
    public User(UserDataDao UserData) {
        this.userdata = UserData; 
    }
    
    public boolean login(String name, String password) {
        try {
            ResultSet rs = this.userdata.getUserInfo(name, password);
            rs.next();
            int id = rs.getInt("id");
            if (id > 0) {
                this.userId = id;
                this.userName = rs.getString("name");
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    } 
    
    public boolean checkNameExist(String name) {
        try {
            ResultSet rs = this.userdata.checkName(name);
            rs.next();
            int id = rs.getInt("id");
            if (id > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    
    public boolean checkPasswordExist(String password) {
        try {
            ResultSet rs = this.userdata.checkName(password);
            rs.next();
            int id = rs.getInt("id");
            if (id > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    
    public boolean signup(String name, String password) {
        if (checkNameExist(name) || checkPasswordExist(password)) {
            return false;
        } else {
            try {
                ResultSet rs = this.userdata.newUser(name, password);
                rs.next();
                int id = rs.getInt("id");
                System.out.println(id);
                if (id > 0) {
                    return true;
                }
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }
    
    public Integer getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
}
