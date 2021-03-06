package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikeinAlussa() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldonLatausToimii() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenKunRiittaa() {
        assertTrue(kortti.otaRahaa(5)==true);
    }
    
    @Test
    public void rahanOttaminenKunEiRiita() {
        assertTrue(kortti.otaRahaa(100)==false);
    }
}
