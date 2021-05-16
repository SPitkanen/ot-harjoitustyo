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
        Db conn = new Db();
        db = conn.createConnection();
        data = new AircraftDataDao(db);
        acData = new AircraftData(data, 1);
        
        results = new Results(data, 1); 
    }
    
    @Test
    public void correctResultsRegister() {
        String[][] result = results.getResults();
        assertEquals(result[0][0], "OH-NNN");
    }
    
    @Test
    public void correctResultsDateShort() {
        String[][] result = results.getResults();
        assertEquals(result[0][1], "2021-05-02");
    }
    
    @Test
    public void correctResultsDatelong() {
        String[][] result = results.getResults();
        assertEquals(result[0][2], "2021-05-02T13:43:44.810114Z");
    }
    
    @Test
    public void correctResultsAircraftId() {
        String[][] result = results.getResults();
        assertEquals(result[0][3], "1");
    }
    
    @Test
    public void correctLogDataItem() {
        String[][] data = results.getLogData("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(data[0][0], "BASIC WEIGHT");
    }
    
    @Test
    public void correctLogDataArm() {
        String[][] data = results.getLogData("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(data[0][1], "31.4");
    }
    
    @Test
    public void correctLogDataWeight() {
        String[][] data = results.getLogData("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(data[0][2], "1251.3");
    }
    
    @Test
    public void correctLogDataMoment() {
        String[][] data = results.getLogData("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(data[0][3], "392.9");
    }
    
    @Test
    public void correctLogCount() {
        results.getLogCount("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(2, 2);
    }
    
    @Test
    public void correctResultCount() {
        int count = results.resultCount(1);
        assertEquals(count, 1);
    }
    
    @Test
    public void getDataWorks() {
        String[][] items = results.getLogData("2021-05-02T13:43:44.810114Z", 1);
        assertEquals(items[1][0], "SEAT 1 & 2");
    }
    
    @Test
    public void shortenDate() {
        String newDate = results.date("2021-05-02T13:43:44.810114Z");
        assertEquals(newDate, "2021-05-02");
    }
}
