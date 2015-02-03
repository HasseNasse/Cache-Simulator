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
public class CacheTest {
    
    public CacheTest() {
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
    public void testFindinstruction() throws InvalidInstructionException {
        System.out.println("findinstruction");
        String instruction = "load";
        String adress = "10100110011001100110011001010100";
        Cache instance = new Cache(32,4,2);
        boolean expResult = false;
        Object[] result = instance.findinstruction(instruction, adress);
        assertEquals(expResult, result[1]);
        
    }

    @Test
    public void testgetHitMissAndInstrCount() {
        System.out.println("getHitMiss");
        Cache instance = new Cache(32,4,2);
        String expResult = "This Simulation yielded " + 0 + " hits & "
                + 0 + " misses";
        Object[] result = instance.getHitMissAndInstrCount();
        assertEquals(expResult, result[0]);
        

    }
}
