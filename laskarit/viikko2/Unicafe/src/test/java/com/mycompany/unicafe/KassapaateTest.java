
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author santeripitkanen
 */
public class KassapaateTest {
    
    public KassapaateTest() {
    }
    
    Kassapaate kassa = new Kassapaate();
    
    @Before
    public void setUp() {
    }

    @Test
    public void luotuOikeinRaha() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void luotuOikeinMaukkaatAteriat() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void luotuOikeinEdullisisaAteriat() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void ostoEdullinenRahaKasvaa() {
        kassa.syoEdullisesti(1000);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void ostoMaukasRahaKasvaa() {
        kassa.syoMaukkaasti(1000);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void ostoEdullinenRahaOikein() {
        assertEquals(760, kassa.syoEdullisesti(1000));
    }
    
    @Test
    public void ostoMaukasRahaOikein() {
        assertEquals(600, kassa.syoMaukkaasti(1000));
    }
    
    @Test
    public void edullisetLounaatkasvaa() {
        kassa.syoEdullisesti(1000);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastiLounaatkasvaa() {
        kassa.syoMaukkaasti(1000);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenRahaEiRiitaRahaSailyy() {
        kassa.syoEdullisesti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasRahaEiRiitaRahaSailyy() {
        kassa.syoMaukkaasti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullinenRahaEiRiitaRahaPalautuu() {
        assertEquals(100, kassa.syoEdullisesti(100));
    }
    
    @Test
    public void maukasRahaEiRiitaRahaPalautuu() {
        assertEquals(100, kassa.syoMaukkaasti(100));
    }
    
    @Test
    public void edullinenRahaEiRiitaLounaatSailyy() {
        kassa.syoEdullisesti(100);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasRahaEiRiitaLounaatSailyy() {
        kassa.syoMaukkaasti(100);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiRahaRiittaaSummaVeloitetaan() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    @Test
    public void maukasKorttiRahaRiittaaSummaVeloitetaan() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void edullinenKorttiRahaRiittaa() {
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukasKorttiRahaRiittaa() {
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullinenkorttiLounaatKasvaa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastikorttiLounaatKasvaa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiRahaEiRiitaRahaEiMuutu() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void maukasKorttiRahaEiRiitaRahaEiMuutu() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void edullinenKorttiRahaEiRiitaTotuusarvo() {
        Maksukortti kortti = new Maksukortti(100);
        assertTrue(kassa.syoEdullisesti(kortti)==false);
    }
    
    @Test
    public void maukasKorttiRahaEiRiitaTotuusarvo() {
        Maksukortti kortti = new Maksukortti(100);
        assertTrue(kassa.syoMaukkaasti(kortti)==false);
    }
    
    @Test
    public void edullinenkorttiLounaatEiKasva() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastikorttiLounaatEiKasva() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLatausKortinSaldoMuuttuu() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(1100, kortti.saldo());
    }
    
    @Test
    public void kortinLatausKassanSaldoMuuttuu() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassa.kassassaRahaa());
    }
}
