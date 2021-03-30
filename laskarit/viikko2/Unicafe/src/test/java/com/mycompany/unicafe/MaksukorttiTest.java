package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void saldoOikeinAlussa() {
        assertEquals(1000, kortti.saldo());
    }
    @Test
    public void saldonLataaminenToimii() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 11.0", kortti.toString());
    }
    @Test
    public void saldoVaheneeOikeinKunTarpeeksiSaldoa() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosSaldoEiRiita() {
        kortti.otaRahaa(11000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    @Test
    public void ottaminenPalauttaaTrueKunSaldoRiittää() {
        assertTrue(kortti.otaRahaa(500) == true);
    }
    @Test
    public void ottaminenPalauttaaFalseKunSaldoEiRiitä() {
        assertTrue(kortti.otaRahaa(11000) == false);
    }
}
