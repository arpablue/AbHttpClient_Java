/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author Augusto Flores
 */
class AbHttpClientPOST extends AbHttpClientRequestDAO {

    /**
     * It execute hte Http POST request
     *
     * @param endPoint
     * @return
     */
    protected boolean postRequest(String uri) {
        try {
            String json = null;
            
            //HttpClient client = HttpClient.newHttpClient();
            HttpClient client = createClient();

            URI uriB = URI.create(uri);
            
            
            HttpRequest.Builder builder = HttpRequest.newBuilder( uriB );
            
            
            if ( this.mData != null ) {
                json = this.mData;
               //builder = builder.POST(HttpRequest.BodyPublishers.ofString(this.mParams));
            } 
            
            if( json == null ){
                if( !this.parametersExists() ){
                    builder = builder.POST(HttpRequest.BodyPublishers.noBody() );
                }else{
                    byte[] data = this.getParamsForm().getBytes( StandardCharsets.UTF_8);
                    this.setRequestHeaders("Content-type", "application/x-www-form-urlencoded");
                    builder = builder.POST(HttpRequest.BodyPublishers.ofByteArray( data  ));
                }
            }else{
                this.setRequestHeaders("Accept", "application/json");
                this.setRequestHeaders("Content-type", "application/json");
                builder = builder.POST(HttpRequest.BodyPublishers.ofString(json));
            }
            this.setHeadersRequest(builder);
           
            HttpRequest request = builder.build();
            
            
            log("HTTP-POST request to: " + uri);
            
            
            this.mResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            return true;
        } catch (Exception e) {
            log("ERROR: Problems to send the POST http request. " + e.getMessage());
        }
        return false;
    }

}
