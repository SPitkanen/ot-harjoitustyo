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
import java.util.List;
import static org.junit.Assert.*;
import wbapp.domain.*;
import wbapp.dao.*;

/**
 *
 * @author santeripitkanen
 */
public class WBCalculatorTest {
    
    WBCalculator calc;
    Connection db;
    AircraftDataDao data;
    AircraftData acData;
    double[][] calculatedData;
    
    public WBCalculatorTest() {
        
    }
    
    @Before
    public void setUp() {
        Db conn = new Db();
        db = conn.createConnection();
        data = new AircraftDataDao(db);
        acData = new AircraftData(data, 1);
        this.calc = new WBCalculator();
        addWeights();
        this.calc.calculateData(acData.getData(), acData.getCount());
        calculatedData = acData.getData();
    }
    
    public void addWeights() {
        double[] list = new double[]{255.78, 28.66, 0, 0, 139.26, 0, 5, 0, 107.26, 0};
        for (int i = 0; i < 10; i++) {
            acData.addWeights(list[i], i + 1);
        }
    }
    
    @Test
    public void correctZeroFuelWeightArm() {
        double arm = calculatedData[4][0];
        assertEquals(arm, 33.33, 0.01);
    }
    
    @Test
    public void correctZeroFuelWeightWeight() {
        double weight = calculatedData[4][1];
        assertEquals(weight, 1530.0, 0.01);
    }
    
    @Test
    public void correctZeroFuelWeightMoment() {
        double moment = calculatedData[4][2];
        assertEquals(moment, 510.01, 0.01);
    }
    
    @Test
    public void correctRampWeightArm() {
        double arm = calculatedData[6][0];
        assertEquals(arm, 33.85, 0.01);
    }
    
    @Test
    public void correctRampWeightWeight() {
        double weight = calculatedData[6][1];
        assertEquals(weight, 1669.0, 0.01);
    }
    
    @Test
    public void correctRampWeightMoment() {
        double moment = calculatedData[6][2];
        assertEquals(moment, 565.02, 0.01);
    }
    
    @Test
    public void correctTakeOffWeightArm() {
        double arm = calculatedData[8][0];
        assertEquals(arm, 33.84, 0.01);
    }
    
    @Test
    public void correctTakeOffWeightWeight() {
        double weight = calculatedData[8][1];
        assertEquals(weight, 1664.0, 0.01);
    }
    
    @Test
    public void correctTakeOffWeightMoment() {
        double moment = calculatedData[8][2];
        assertEquals(moment, 563.04, 0.01);
    }
    
    @Test
    public void correctLandingWeightArm() {
        double arm = calculatedData[10][0];
        assertEquals(arm, 33.46, 0.01);
    }
    
    @Test
    public void correctLandingWeightWeight() {
        double weight = calculatedData[10][1];
        assertEquals(weight, 1556.0, 0.01);
    }
    
    @Test
    public void correctLandingWeightMoment() {
        double moment = calculatedData[10][2];
        assertEquals(moment, 520.67, 0.01);
    }
    
    @Test
    public void correctMoment() {
        double d = this.calc.calculateMoment(39.5, 139.26);
        assertEquals(d, 55.01, 0.01);
    }
    
    @Test
    public void correctArm() {
        double d = this.calc.calculateArm(1562.74, 527.55);
        assertEquals(d, 33.75, 0.01);
    }
}
