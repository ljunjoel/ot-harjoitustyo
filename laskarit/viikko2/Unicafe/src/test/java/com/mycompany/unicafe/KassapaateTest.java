/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    @Test
    public void kassanRahatOikeinAlussa() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void myydytOikeinAlussa() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kateisostoEdullisestiToimii() {
        paate.syoEdullisesti(500);
        assertTrue(paate.kassassaRahaa() == 100240);
    }
    @Test
    public void kateisostoMaukkaastiToimii() {
        paate.syoMaukkaasti(500);
        assertTrue(paate.kassassaRahaa() == 100400);
    }
    @Test
    public void vaihtorahatToimii() {
        assertEquals(10, paate.syoEdullisesti(250));
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    @Test
    public void kateisostotNostaaSaldoja() {
        paate.syoMaukkaasti(500);
        paate.syoEdullisesti(250);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
    }
    @Test
    public void kateisostoKieltaaOikein() {
        paate.syoEdullisesti(200);
        paate.syoMaukkaasti(300);
        assertEquals(200, paate.syoEdullisesti(200));
        assertEquals(300, paate.syoMaukkaasti(300));
        assertTrue(paate.maukkaitaLounaitaMyyty()== 0);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void korttiostoToimiiOikein() {
        assertTrue(paate.syoEdullisesti(kortti) == true);
        assertTrue(kortti.saldo() == 760);
        assertTrue(paate.syoMaukkaasti(kortti) == true);
        assertTrue(kortti.saldo() == 360);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void korttiostoKieltaaOikein() {
        kortti = new Maksukortti(200);
        assertEquals(false, paate.syoEdullisesti(kortti));
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertTrue(kortti.saldo() == 200);
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void kortinLataaminenToimiiOikein() {
        paate.lataaRahaaKortille(kortti, 1000);
        paate.lataaRahaaKortille(kortti, -100000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, paate.kassassaRahaa());
    }
}
