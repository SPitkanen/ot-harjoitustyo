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
    Results results;
    
    public WBCalculatorTest() {
        
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
        this.calc = new WBCalculator();
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
