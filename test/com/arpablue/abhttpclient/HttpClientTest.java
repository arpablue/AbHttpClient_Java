/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Augusto Flores
 */
public class HttpClientTest {

    protected static Logger LOG = new Logger();

    public HttpClientTest() throws IOException {
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

    protected static void printData(AbHttpClient instance) {
        Map<String, String> headers = instance.getResponseHeaders();
        String body = instance.getResponseBody();
        System.out.println("        ---- Status: " + instance.getStatusCode());
        System.out.println("                 --------------------Headers----------------------");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println("                          -  " + entry.getKey() + " =  " + entry.getValue());
        }
        System.out.println("                 --------------------------------------------------");
        System.out.println("                 --------------------Body-----------------------");
        System.out.println("                        " + body);
        System.out.println("");

    }

    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder
                    .append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder
                    .append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    @Test
    public void test_HTTP_POST_with_parameters() {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        // form parameters
        Map<Object, Object> data = new HashMap<>();
        data.put("username", "abc");
        data.put("password", "123");
        data.put("custom", "secret");
        data.put("ts", System.currentTimeMillis());

        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(data))
                .uri(URI.create("https://localhost/index.php"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = null;

        try {

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(HttpClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(HttpClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
    }
////    @Test
//    public void test_HTTP_GET_implementation() {
//        System.out.println("---------test_HTTP_GET_implementation");
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//
//            HttpRequest request = HttpRequest.newBuilder(
//                    URI.create("https://reqres.in/api/users")
//            )
//                    .GET()
//                    .build();
//
//            //---------------------Envio de peticion sincrona
//            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//            System.out.println("---Body");
//            System.out.println(response.body());
//            System.out.println("---Headers");
//            System.out.println(response.headers().map());
//
//        } catch (Exception e) {
//            System.out.println("ERROR: " + e.getMessage());
//            fail(e.getMessage());
//        }
//    }
//
////    @Test
//    public void test_HTTP_AsyncGET_implementation() {
//        System.out.println("---------test_HTTP_GET_implementation");
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//
//            HttpRequest request = HttpRequest.newBuilder(
//                    URI.create("https://reqres.in/api/users")
//            )
//                    .GET()
//                    .build();
//            //------------------------- Envio de petision asincrona
//            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString());
//
//            response.thenAccept(res -> {
//                System.out.println("---Body");
//                System.out.println(res.body());
//                System.out.println("---Head");
//                System.out.println(res.headers().map());
//            });
//            response.join();
//
//        } catch (Exception e) {
//            System.out.println("ERROR: " + e.getMessage());
//            fail(e.getMessage());
//        }
//    }
//
////    @Test
//    public void test_HTTP_GET_withHeaders() {
//        System.out.println("---------test_HTTP_GET_withHeaders");
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//
//            HttpRequest request = HttpRequest.newBuilder(
//                    URI.create("https://reqres.in/api/users")
//            )
//                    .GET()
//                    .header("Content-Type", "text/html")
//                    .build();
//
//            //---------------------Envio de peticion sincrona
////            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
////            System.out.println("---Body");
////            System.out.println(response.body());
////            System.out.println("---Headers");
////            System.out.println(response.headers().map());
//        } catch (Exception e) {
//            System.out.println("ERROR: " + e.getMessage());
//            fail(e.getMessage());
//        }
//    }
//    @Test
//    public void test_GET2Async(){
//        System.out.println("--------------- test_GET2Async");
//        String uri = "http://localhost/index.php?name=Alan&age=alan_0&username=alan_0&lastname=Prove";
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create( uri )).build();
//        client.sendAsync( request, HttpResponse.BodyHandlers.ofString() )
//                .thenApply( HttpResponse::body)
//                .thenAccept( System.out::println )
//                .join();
//    }
}
