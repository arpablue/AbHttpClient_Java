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
public class AbHttpClientTest {

    protected static Logger LOG = new Logger();

    public AbHttpClientTest() {
        java.net.CookieManager cm = new java.net.CookieManager();
        java.net.CookieHandler.setDefault(cm);
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
//        String HOST = "https://reqres.in";
//        String END_POINT = "/api/users";
        public static String HOST = "http://localhost";
        public static String END_POINT = "index.php";

    protected static void printData(AbHttpClient instance){
        Map<String, String>  headers = instance.getResponseHeaders();
        String body = instance.getResponseBody();
        System.out.println("**************************************************");
        System.out.println("        ---- Status: "+instance.getStatusCode());
        System.out.println("                 --------------------Headers----------------------");
        for (Map.Entry<String,String> entry : headers.entrySet()){
            System.out.println("                          -  "+entry.getKey() + " =  "+entry.getValue() );
        }
        System.out.println("                 --------------------------------------------------");
        System.out.println("                 --------------------Body-----------------------");
        System.out.println("                        "+body);
        printData( "Cookies",instance.getCookies());
        System.out.println("");
        
    }
    protected static void printData(String title, Map<String, String> target){
        if( title == null){
            title = "No title";
        }
        System.out.println("------------------ "+title+" ------------------");
        if( target == null ){
            System.out.println("The list is null");
        }else if ( target.size() < 1){
            System.out.println("No data to show.");
        }else{
            for( Map.Entry<String, String> entry : target.entrySet() ){
                System.out.println("---- "+entry.getKey()+" = "+entry.getValue());
            }
        }
        System.out.println("--------------------------------------------------");
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
//    @Test
    public void test_AbHttpClient_SimpleGet() {
        System.out.println("---------test_AbHttpClient_SimpleGet");
        int expCode = 200;
        AbHttpClient instance = new AbHttpClient();
        instance.setLog( AbHttpClientTest.LOG );

        instance.setHost(HOST );

        String expResult = "/api/users";
        String result = instance.get(END_POINT);


        printData(instance);


        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
        
    }
    /**
     * Test of get method, of class AbHttpClient.
     */
//    @Test
    public void test_AbHttpClient_GetWithParameters() {
        System.out.println("---------test_AbHttpClient_SimpleGet");
        int expCode = 200;
        String expStr ="{\"method\":\"GET\",\"time\":\"2022-03-05 04:56:38\",\"parameters\":{\"name\":\"Alan\",\"age\":\"18\",\"username\":\"alan_0\",\"lastname\":\"Prove\"}}";
        AbHttpClient instance = new AbHttpClient();
        instance.setLog( AbHttpClientTest.LOG );

        instance.setHost(HOST );
        //Adding parameters
        instance.addParam("username", "alan_0");
        instance.addParam("name", "Alan");
        instance.addParam("lastname", "Prove");
        instance.addParam("age", "alan_0");

        String expResult = "/api/users";
        String result = instance.get(END_POINT);

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
        
        String result = instance.post(END_POINT);

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
    //@Test
    public void test_AbHttpClient_POST_Parameters() {
        System.out.println("---------test_AbHttpClient_SimplePOST");
        int expCode = 201;
        AbHttpClient instance = new AbHttpClient();
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
        
        printData(instance);
        
        int code = instance.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
    }
    //@Test 
    public void test_AbHttpClient_POST_Authentication(){
        System.out.println("-------------------- test_AbHttpClient_POST_Authentication");
        int expCode = 201;
        AbHttpClient target = new AbHttpClient();
        target.setLog(LOG);
        target.setHost(HOST );
        
        target.setAuthiorization("alan_0", "woods_0");
        
        String result = target.post(END_POINT );
        

        Map<String, String> headers = target.getResponseHeaders();
        List<String> list = null;
        if(  headers  == null ){
            fail("The header of the response is NULL.");
        }
        if( headers.size() < 1){
            fail("No headers faound in the response.");
        }

        printData(target);
        
        int code = target.getStatusCode();
        if( code != expCode ){
            fail("The status code is ["+code+"] when should be ["+expCode+"]");
        }
    }

}
