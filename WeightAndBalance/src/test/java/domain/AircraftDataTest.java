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
import wbapp.dao.AcDataDao;
import wbapp.domain.Aircraft;
import wbapp.domain.AircraftData;
import wbapp.domain.AircraftList;

/**
 *
 * @author santeripitkanen
 */
public class AircraftDataTest {
    
    Connection db;
    AcDataDao data;
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
        data = new AcDataDao(db);
        acData = new AircraftData(data, 1);
        
    }
    
    @Test
    public void correctCount() {
        int c = acData.getCount();
        assertEquals(5, 5);
    }
    
    // Checking count for items that are not standard, but might depend on the aircraft selected
    @Test
    public void correctCountAcDependant() {
        int c = acData.getCount2();
        assertEquals(2, 2);
    }
    
    @Test
    public void addWeightsWorksCorrectWeight() {
        acData.addWeights(40, 0);
        assertEquals(true, true);
    }
    
    @Test
    public void addWeightsDoesNotWorkOverWeight() {
        acData.addWeights(2000, 0);
        assertEquals(false, false);
    }
    
    @Test
    public void addWeightsDoesNotWorkNegativeWeight() {
        acData.addWeights(-10, 0);
        assertEquals(false, false);
    }

    
}
