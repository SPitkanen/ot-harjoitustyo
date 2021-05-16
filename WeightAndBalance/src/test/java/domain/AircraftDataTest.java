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
        Db conn = new Db();
        db = conn.createConnection();
        data = new AircraftDataDao(db);
        acData = new AircraftData(data, 1);
        
    }
    
    @Test
    public void correctItemListFirst() {
        String item = acData.getFullItemList().get(0);
        assertEquals(item, "BASIC WEIGHT");
    }
    
    @Test
    public void correctItemListlast() {
        String item = acData.getFullItemList().get(10);
        assertEquals(item, "LANDING WEIGHT");
    }
    
    @Test
    public void correctCount() {
        int c = acData.getCount();
        assertEquals(c, 11);
    }
    
    
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
        assertEquals(x, 40.0, 0.01);
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
    
    @Test
    public void correctChartXstart() {
        double[][] chart = acData.getChartData(1);
        assertEquals(chart[0][0], 30, 0.01);
    }
    
    @Test
    public void correctChartXstop() {
        double[][] chart = acData.getChartData(1);
        assertEquals(chart[0][1], 38, 0.01);
    }
    
    @Test
    public void correctChartYstart() {
        double[][] chart = acData.getChartData(1);
        assertEquals(chart[0][2], 1000, 0.01);
    }
    
    @Test
    public void correctChartYstop() {
        double[][] chart = acData.getChartData(1);
        assertEquals(chart[0][3], 1800, 0.01);
    }
    
    @Test
    public void correctChartXspacing() {
        double[][] chart = acData.getChartData(1);
        assertEquals(chart[0][4], 2, 0.01);
    }
    
    @Test
    public void correctChartYspacing() {
        double[][] chart = acData.getChartData(1);
        assertEquals(chart[0][5], 100, 0.01);
    }
    
    @Test
    public void correctEnvelopeDataX() {
        double[][] envelope = acData.getEnvelopeData(1);
        assertEquals(envelope[4][0], 36.5, 0.01);
    }
    
    @Test
    public void correctEnvelopeDataY() {
        double[][] envelope = acData.getEnvelopeData(1);
        assertEquals(envelope[4][1], 1000, 0.01);
    }

    
}
