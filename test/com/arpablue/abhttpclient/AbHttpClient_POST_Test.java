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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClient_POST_Test extends AbHttpClient_RequestDAO{


    public AbHttpClient_POST_Test() {}

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
    public void test_AbHttpClient_SimplePOST() {
        System.out.println("---------test_AbHttpClient_SimplePOST");
        int expCode = 201;
        AbHttpClient instance = new AbHttpClient();
        instance.setLog(LOG);
        instance.setHost(HOST );
        String data = """
                      {
                          "name": "morpheus",
                          "job": "leader"
                      }
                      """;
        instance.setData( data );
        
        // Sending the post
        String result = instance.post(END_POINT);
        

        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if(  headers  == null ){
            fail("The header of the response is NULL.");
        }
    
        if( headers.size() < 1){
            fail("No headers faound in the response.");
        }

        //printData(instance);

        int code = instance.getStatusCode();
        if( code != expCode ){ 
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_POST_addCookies() {
        System.out.println("---------test_AbHttpClient_POST_addCookies");
        int expCode = 201;
        String expStr = "\"cookiesRequest\":{\"hero\":\"Spiderman\",\"girlfriend\":\"Mary Jane\"}";
        AbHttpClient instance = new AbHttpClient();
        instance.setLog(LOG);
        instance.setHost(HOST );
       
        // Adding cookies
        instance.addCookie("hero", "Spiderman");
        instance.addCookie("girlfriend", "Mary Jane");
        
        // Sending the post
        String result = instance.post(END_POINT);
        

        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if(  headers  == null ){
            fail("The header of the response is NULL.");
        }
        if( headers.size() < 1){
            fail("No headers faound in the response.");
        }

        //printData(instance);

        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        if( !result.contains(expStr) ){
            System.out.println("Response: "+result);
            System.out.println("Cookies to search: "+expStr);
            fail("the response doesn't have the following cookies: "+expStr);
        }
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_POST_Parameters() {
        System.out.println("---------test_AbHttpClient_SimplePOST");
        int expCode = 201;
        AbHttpClient instance = new AbHttpClient();
        String expStr = "\"parameters\":{\"gender\":\"Male\",\"name\":\"Jacob\",\"age\":\"24\",\"lastname\":\"Woods\"}";
        instance.setLog(LOG);
        instance.setHost(HOST );
        
        instance.addParam("name", "Jacob");
        instance.addParam("lastname", "Woods");
        instance.addParam("age", "24");
        instance.addParam("gender", "Male");
        //---------------------------------------------
        
        //---------------------------------------------
        String result = instance.post(END_POINT );

        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if(  headers  == null ){
            fail("The header of the response is NULL.");
        }
        if( headers.size() < 1){
            fail("No headers faound in the response.");
        }
        
        //printData(instance);
        
        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        if( !result.contains(expStr) ){
            System.out.println("Response: "+result);
            System.out.println("Cookies to search: "+expStr);
            fail("the response doesn't have the following cookies: "+expStr);
        }
    }

}
