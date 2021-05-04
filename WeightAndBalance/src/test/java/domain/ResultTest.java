/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wbapp.dao.*;
import wbapp.domain.*;

/**
 *
 * @author santeripitkanen
 */
public class ResultTest {
    
    Connection db;
    AircraftDataDao data;
    AircraftData acData;
    Results results;
    
    public ResultTest() {
        
    }
    
    
    @Before
    public void setUp() {
        try {
            db = DriverManager.getConnection("jdbc:postgresql:wbtest");
        } catch (SQLException e) {
            System.out.println("Virhe tietokannassa");
        }
        data = new AircraftDataDao(db);
        acData = new AircraftData(data, 1);
        
        results = new Results(data, 1); 
    }
    
    @Test
    public void correctLogCount() {
        results.getLogCount("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(2, 2);
    }
    
    @Test
    public void correctResultCount() {
        results.resultCount(1);
    }
    
    @Test
    public void getDataWorks() {
        String[][] items = results.getLogData("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(items[1][0], "SEAT 1 & 2");
    }
}
