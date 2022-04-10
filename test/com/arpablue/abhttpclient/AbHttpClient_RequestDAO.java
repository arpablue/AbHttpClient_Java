/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.util.Map;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClient_RequestDAO {
    protected static Logger LOG = new Logger();
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
        printData( "Cookies Response",instance.getCookies());
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
    
}
