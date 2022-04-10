/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import static com.arpablue.abhttpclient.AbHttpClient_RequestDAO.END_POINT;
import static com.arpablue.abhttpclient.AbHttpClient_RequestDAO.HOST;
import static com.arpablue.abhttpclient.AbHttpClient_RequestDAO.printData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClient_GET_Test extends AbHttpClient_RequestDAO {

    public AbHttpClient_GET_Test() {
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
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_SimpleGETt() {
        System.out.println("---------test_AbHttpClient_SimpleGETt");
        int expCode = 200;
        AbHttpClient instance = new AbHttpClient();
        instance.setLog(AbHttpClient_POST_Test.LOG);

        instance.setHost(HOST);

        String expResult = "/api/users";
        String result = instance.get(END_POINT);

//        printData(instance);

        int code = instance.getStatusCode();
        if (code != expCode) {
            fail("The status code is [" + code + "] when should be [" + expCode + "]");
        }

    }

    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_GET_WithParameters() {
        System.out.println("---------test_AbHttpClient_SimpleGet");
        int expCode = 200;
        String method = "\"method\":\"GET\"";
        String expStr = "\"parameters\":{\"name\":\"Alan\",\"age\":\"20\",\"username\":\"alan_0\",\"lastname\":\"Prove\"}";
        AbHttpClient instance = new AbHttpClient();
        instance.setLog(AbHttpClient_POST_Test.LOG);

        instance.setHost(HOST);
        //Adding parameters
        instance.addParam("username", "alan_0");
        instance.addParam("name", "Alan");
        instance.addParam("lastname", "Prove");
        instance.addParam("age", "20");

        String expResult = "/api/users";
        // executind the request.
        String result = instance.get(END_POINT);

        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if (headers == null) {
            fail("The header of the response is NULL.");
        }
        if (headers.size() < 1) {
            fail("No headers found in the response.");
        }

        int code = instance.getStatusCode();
        if (code != expCode) {
            fail("The status code is [" + code + "] when should be [" + expCode + "]");
        }
        if( !result.contains( method) ){
            System.out.println("Response: " + result);
            System.out.println("Expresion to search: " + method);
            fail("the request body not contains the following text: "+method);
        }
        if( !result.contains( expStr ) ){
            System.out.println("Response: " + result);
            System.out.println("Expresion to search: " + expStr);
            fail("The body response not contains the expected result.");
        }

    }

    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_GET_Cookies() {
        System.out.println("---------test_AbHttpClient_GET_Cookies");
        int expCode = 200;
        AbHttpClient instance = new AbHttpClient();
        instance.setLog(AbHttpClient_POST_Test.LOG);

        instance.setHost(HOST);
        //Cookies
        Map<String, String> cookiesA = new HashMap<String, String>();

        cookiesA.put("TheHero", "Spiderman");
        cookiesA.put("Girlfriend", "Mary Jane");

        for (Map.Entry<String, String> entry : cookiesA.entrySet()) {
            instance.addCookie(entry.getKey(), entry.getValue());
        }

        String expResult = "/api/users";

        //GET request
        String result = instance.get(END_POINT);

        //printData(instance);
        // headers validation

        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if (headers == null) {
            fail("The header of the response is NULL.");
        }
        if (headers.size() < 1) {
            fail("No headers found in the response.");
        }
        // Code validation
        int code = instance.getStatusCode();
        if (code != expCode) {
            fail("The status code is [" + code + "] when should be [" + expCode + "]");
        }
        
        StringBuilder err = new StringBuilder();
        boolean flag = false;
        boolean success = true;
        /// Cookies validation
        Map<String, String> cookiesB = instance.getCookies();
        for (Map.Entry<String, String> entryA : cookiesA.entrySet()) {
            flag = false;
            for (Map.Entry<String, String> entryB : cookiesB.entrySet()) {
                if( entryA.getKey().equalsIgnoreCase(entryB.getKey())){
                    flag = (entryA.getValue().equalsIgnoreCase(entryB.getValue()) );
                }
            }
            if( !flag ){
                success = false;
                err.append("Wrong value for ");
                err.append(entryA.getKey());
                err.append(" cookie .\n");
            }
        }
        if( !success ){
            System.out.println("Problems for some cookies.");
            System.out.println(err.toString());
            fail("Some cookies not exist or their values are not the same.");
        }
        
    }

}
