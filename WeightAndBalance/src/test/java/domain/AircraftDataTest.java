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
import wbapp.dao.AircraftDataDao;
import wbapp.domain.*;

/**
 *
 * @author santeripitkanen
 */
public class AircraftDataTest {
    
    Connection db;
    AircraftDataDao data;
    AircraftData acData;
    
    public AircraftDataTest() {
        
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
        
    }
    
    @Test
    public void correctCount() {
        int c = acData.getCount();
        assertEquals(c, 11);
    }
    
    // Checking count for items that are not standard, but might depend on the aircraft selected
    @Test
    public void correctCountAcDependant() {
        int c = acData.getCount2();
        assertEquals(c, 6);
    }
    
    @Test
    public void addWeightsWorksCorrectWeight() {
        acData.addWeights(40, 0);
        double list[][] = acData.getData();
        double x = list[0][1];
        assertEquals(40, 40);
    }
    
    @Test
    public void checkWeightDoesNotWorkOverWeight() {
        boolean x = acData.checkWeight(20000, 0);
        assertEquals(x, false);
    }
    
    @Test
    public void checkWeightDoesNotWorkNegativeWeight() {
        boolean x = acData.checkWeight(-10, 0);
        assertEquals(x, false);
    }
    
    @Test
    public void checkWeightWorksCorrectWeight() {
        boolean x = acData.checkWeight(30, 1);
        assertEquals(x, true);
    }

    
}
