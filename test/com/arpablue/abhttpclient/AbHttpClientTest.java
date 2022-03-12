/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.URI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.net.http.*;
import java.net.http.HttpResponse.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClientTest {

    protected static Logger LOG = new Logger();

    public AbHttpClientTest() {
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

    protected static void printData(AbHttpClient instance){
        Map<String, String>  headers = instance.getResponseHeaders();
        String body = instance.getResponseBody();
        System.out.println("        ---- Status: "+instance.getStatusCode());
        System.out.println("                 --------------------Headers----------------------");
        for (Map.Entry<String,String> entry : headers.entrySet()){
            System.out.println("                          -  "+entry.getKey() + " =  "+entry.getValue() );
        }
        System.out.println("                 --------------------------------------------------");
        System.out.println("                 --------------------Body-----------------------");
        System.out.println("                        "+body);
        System.out.println("");
        
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_SimpleGet() {
//        String host = "https://reqres.in";
//        String endPoint = "/api/users";
        String host = "http://localhost";
        String endPoint = "index.php";
        System.out.println("---------test_AbHttpClient_SimpleGet");
        int expCode = 200;
        AbHttpClient instance = new AbHttpClient();
        instance.setLog( AbHttpClientTest.LOG );

        instance.setHost( host );

        String expResult = "/api/users";
        String result = instance.get(endPoint);


        printData(instance);


        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_GetWithParameters() {
//        String host = "https://reqres.in";
//        String endPoint = "/api/users";
        String host = "http://localhost";
        String endPoint = "index.php";
        System.out.println("---------test_AbHttpClient_SimpleGet");
        int expCode = 200;
        String expStr ="{\"method\":\"GET\",\"time\":\"2022-03-05 04:56:38\",\"parameters\":{\"name\":\"Alan\",\"age\":\"18\",\"username\":\"alan_0\",\"lastname\":\"Prove\"}}";
        AbHttpClient instance = new AbHttpClient();
        instance.setLog( AbHttpClientTest.LOG );

        instance.setHost( host );
        //Adding parameters
        instance.addParam("username", "alan_0");
        instance.addParam("name", "Alan");
        instance.addParam("lastname", "Prove");
        instance.addParam("age", "alan_0");

        String expResult = "/api/users";
        String result = instance.get(endPoint);

        printData(instance);
        
        
        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if(  headers  == null ){
            fail("The header of the response is NULL.");
        }
        if( headers.size() < 1){
            fail("No headers found in the response.");
        }
        
        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        if( result.equals(expStr)){
            System.out.println("cur: "+result);
            System.out.println("exp: "+expStr);
            fail("The body response are differents.");
        }
        
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_SimplePOST() {
        System.out.println("---------test_AbHttpClient_SimplePOST");
        int expCode = 201;
//        String host = "https://reqres.in";
//        String endPoint = "/api/users";
        String host = "http://localhost";
        String endPoint = "index.php";
        AbHttpClient instance = new AbHttpClient();
        instance.setLog(LOG);
        instance.setHost( host );
        String data = """
                      {
                          "name": "morpheus",
                          "job": "leader"
                      }
                      """;
        instance.setData( data );
        String result = instance.post(endPoint);

        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if(  headers  == null ){
            fail("The header of the response is NULL.");
        }
        if( headers.size() < 1){
            fail("No headers faound in the response.");
        }

        printData(instance);

        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
    @Test
    public void test_AbHttpClient_POST_Parameters() {
        System.out.println("---------test_AbHttpClient_SimplePOST");
        int expCode = 201;
//        String host = "https://reqres.in";
//        String endPoint = "/api/users";
        String host = "http://localhost";
        String endPoint = "index.php";
        AbHttpClient instance = new AbHttpClient();
        instance.setLog(LOG);
        instance.setHost( host );
        
        instance.addParam("name", "Jacob");
        instance.addParam("lastname", "Woods");
        instance.addParam("age", "23");
        instance.addParam("gender", "Male");
        
        String result = instance.post(endPoint);

        Map<String, String> headers = instance.getResponseHeaders();
        List<String> list = null;
        if(  headers  == null ){
            fail("The header of the response is NULL.");
        }
        if( headers.size() < 1){
            fail("No headers faound in the response.");
        }

        printData(instance);
        
        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        
    }

}
