/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClientDAOTest {
    
    public AbHttpClientDAOTest() {
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

    /**
     * Test of setFormat method, of class AbHttpClientDAO.
     */
    @Test
    public void test_AbHttpClientDAO_SetFormat() {
        System.out.println("-----------------test_AbHttpClientDAO_SetFormat");
        String target = "https://www.google.com";
        String exp = "https://www.google.com";
        String current = AbHttpClientDAO.setFormat(target);
        if( !exp.equals( current )){
            System.out.println("Exp: " + exp);
            System.out.println("Cur: " + current);
            fail("The expected  ["+exp+"] and current ["+current+"] are differents.");
        }
        
    }
    /**
     * Test of setFormat method, of class AbHttpClientDAO.
     */
    @Test
    public void test_AbHttpClientDAO_SetFormat_spcaeAtStart() {
        System.out.println("-----------------test_AbHttpClientDAO_SetFormat_spcaeAtStart");
        String target = "            https://www.google.com";
        String exp = "https://www.google.com";
        String current = AbHttpClientDAO.setFormat(target);
        if( !exp.equals( current )){
            System.out.println("Exp: " + exp);
            System.out.println("Cur: " + current);
            fail("The expected  ["+exp+"] and current ["+current+"] are differents.");
        }
    }
    /**
     * Test of setFormat method, of class AbHttpClientDAO.
     */
    @Test
    public void test_AbHttpClientDAO_SetFormat_spaceAtEnd() {
        System.out.println("-----------------test_AbHttpClientDAO_SetFormat_spaceAtEnd");
        String target = "https://www.google.com         ";
        String exp = "https://www.google.com";
        String current = AbHttpClientDAO.setFormat(target);
        if( !exp.equals( current )){
            System.out.println("Exp: " + exp);
            System.out.println("Cur: " + current);
            fail("The expected  ["+exp+"] and current ["+current+"] are differents.");
        }
    }
    /**
     * Test of setFormat method, of class AbHttpClientDAO.
     */
   @Test
    public void test_AbHttpClientDAO_SetFormat_around() {
        System.out.println("-----------------test_AbHttpClientDAO_SetFormat_around");
        String target = "     https://www.google.com      ";
        String exp = "https://www.google.com";
        String current = AbHttpClientDAO.setFormat(target);
        if( !exp.equals( current )){
            System.out.println("Exp: " + exp);
            System.out.println("Cur: " + current);
            fail("The expected  ["+exp+"] and current ["+current+"] are differents.");
        }
    }
    /**
     * Test of setFormat method, of class AbHttpClientDAO.
     */
   @Test
    public void test_AbHttpClientDAO_SetFormat_withSlash() {
        System.out.println("-----------------test_AbHttpClientDAO_SetFormat_withSlash");
        String target = "     https://www.google.com///      ";
        String exp = "https://www.google.com";
        String current = AbHttpClientDAO.setFormat(target);
        if( !exp.equals( current )){
            System.out.println("Exp: " + exp);
            System.out.println("Cur: " + current);
            fail("The expected  ["+exp+"] and current ["+current+"] are differents.");
        }
    }
    /**
     * Test of setFormat method, of class AbHttpClientDAO.
     */
   @Test
    public void test_AbHttpClientDAO_SetFormat_withStarSlash() {
        System.out.println("-----------------test_AbHttpClientDAO_SetFormat_withStarSlash");
        String target = "    //https://www.google.com      ";
        String exp = "https://www.google.com";
        String current = AbHttpClientDAO.setFormat(target);
        if( !exp.equals( current )){
            System.out.println("Exp: " + exp);
            System.out.println("Cur: " + current);
            fail("The expected  ["+exp+"] and current ["+current+"] are differents.");
        }
    }
    
}
