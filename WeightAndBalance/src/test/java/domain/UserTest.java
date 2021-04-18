package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import wbapp.dao.DbDao;
import wbapp.dao.UserDataDao;
import wbapp.domain.User;

/**
 *
 * @author santeripitkanen
 */
public class UserTest {
    
    Connection db;
    User user;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() {
        try {
            db = DriverManager.getConnection("jdbc:postgresql:wbtest");
        } catch (SQLException e) {
            System.out.println("Virhe tietokannassa");
        }
        
        UserDataDao usrData = new UserDataDao(db);
        user = new User(usrData);
    }

    @Test
    public void loginWorkingExistingUser() {
        boolean b = user.login("user", "user");
        assertEquals(true, true);
    }
    
    @Test
    public void loginFailsNonExistingUsername() {
        boolean b = user.login("sdmvbsd", "user");
        assertEquals(false, false);
    }
    
    @Test
    public void loginFailsNonExistingPassword() {
        boolean b = user.login("user", "kshfghs");
        assertEquals(false, false);
    }
    
    @Test
    public void loginFailsNonExisting() {
        boolean b = user.login("skvjbs", "kshfghs");
        assertEquals(false, false);
    }
    
    @Test
    public void checkNameExistsTrue() throws SQLException {
        boolean b = user.checkNameExist("user");
        assertEquals(true, true);
    }
    
    @Test
    public void checkNameExistsFalse() throws SQLException {
        boolean b = user.checkNameExist("ksjdbvjk");
        assertEquals(false, false);
    }
    
    @Test
    public void checkPasswordExistsTrue() throws SQLException {
        boolean b = user.checkNameExist("user");
        assertEquals(true, true);
    }
    
    @Test
    public void checkPasswordExistsFalse() throws SQLException {
        boolean b = user.checkNameExist("ksjdbvjk");
        assertEquals(false, false);
    }
}
