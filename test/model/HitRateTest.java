/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hassan
 */
public class HitRateTest {
    
    public HitRateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetHitMiss() {
        System.out.println("getHitMiss");
        HitRate instance = new HitRate();
        String expResult = "This Simulation yielded " + 0 + " hits & "
                + 0 + " misses";
        String result = instance.getHitMiss();
        assertEquals(expResult, result);
    }
    
}
