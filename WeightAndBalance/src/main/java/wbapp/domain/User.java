
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
    
    /**
    * Method for user login. method checks for correct name and password
    * 
    * @param name username
    * @param password user password
    * 
    * @return true if name and password exists, else false
    *
    */
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
    
    /**
    * Method checks if username is already taken.
    * 
    * @param name username to be checked
    * 
    * @return true if name exists, else false
    *
    */
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
    
    /**
    * Method checks if password is already taken.
    * 
    * @param password password to be checked
    * 
    * @return true if password exists, else false
    *
    */
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
    
    /**
    * Method checks if name and/or password is already reserved.
    * If this is is the case, false is returned. Else method newUser is called from user dao and new user is created
    * 
    * @param name username to be checked
    * @param password password to be checked
    * 
    * @return true if new user is created, else false
    *
    */
    public boolean signup(String name, String password) {
        if (checkNameExist(name) || checkPasswordExist(password)) {
            return false;
        } else {
            try {
                ResultSet rs = this.userdata.newUser(name, password);
                rs.next();
                int id = rs.getInt("id");
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
