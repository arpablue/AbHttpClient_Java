/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClientRequest extends AbHttpClientGET {
    /**
     * It is the content to identify the POST REQUEST.
     */
    protected static final int REQUEST_GET = 0;
    /**
     * It is the content to identify the PUT REQUEST.
     */
    protected static final int REQUEST_PUT = 1;
    /**
     * It is the content to identify the POST REQUEST.
     */
    protected static final int REQUEST_POST = 2;
    /**
     * It is the content to identify the DELETE REQUEST.
     */
    protected static final int REQUEST_DELETE = 3;
    public static final String getRequestName(int requestType){
        
    }
    /**
     * It execute a specified request to the uri specified.
     *
     * @param requestType it is teh request type.
     * @param uri It i sthe uri to do the request.
     * @return It is true if the request has been executed without problem.
     */
    public boolean request(int requestType, String uri) {
        try {

            HttpClient client = createClient();
            URI uriB = URI.create(uri);
            HttpRequest.Builder builder = HttpRequest.newBuilder(uriB);
            //--------------
            if (this.mData == null) {
                if (!this.parametersExists()) {
                    builder = requestNoBody( requestType, builder );
                } else {
                    this.setRequestHeaders("Content-type", "application/x-www-form-urlencoded");
                    builder = requestFormData( requestType, builder );
                }
            } else {
                    this.setRequestHeaders("Accept", "application/json");
                    this.setRequestHeaders("Content-type", "application/json");
                    builder = requestJsonData( requestType, builder );
            }
            //------------------------------
            this.setHeadersRequest( builder );

            HttpRequest request = builder.build();

            this.mResponse = client.send( request, HttpResponse.BodyHandlers.ofString() );

            this.mStatus = this.mResponse.statusCode();
            if (this.mResponse != null) {
                this.mBody = this.mResponse.body();
            }

            return true;
        } catch (Exception e) {
            log("ERROR: Problems to send the HTTP request. " + e.getMessage());
        }
        return false;
        
    }
    /**
     * It made a request without a body.
     * @param request It is the kind of request.
     * @param builder It is the builder to create the request.
     * @return It is the new builder with the request created
     */
    protected HttpRequest.Builder requestNoBody( int request, HttpRequest.Builder builder ) {
        switch( request ){
            case AbHttpClientRequest.REQUEST_POST :
                builder = this.requestPOSTNoBody(builder);
                break;
            case AbHttpClientRequest.REQUEST_PUT:
                builder = this.requestPUTNoBody(builder);
                break;
            case AbHttpClientRequest.REQUEST_DELETE:
                builder = this.requestDELETENoBody(builder);
                break;
        }
        return builder;
    }
    /**
     * It made a request without a body.
     * @param request It is the kind of request.
     * @param builder It is the builder to create the request.
     * @return It is the new builder with the request created
     */
    protected HttpRequest.Builder requestFormData( int request, HttpRequest.Builder builder ) {
        switch( request ){
            case AbHttpClientRequest.REQUEST_POST :
                builder = this.requestPOSTFormData(builder);
                break;
            case AbHttpClientRequest.REQUEST_PUT:
                builder = this.requestPUTFormData(builder);
                break;
            case AbHttpClientRequest.REQUEST_DELETE:
                builder = this.requestDELETEFormData(builder);
                break;
        }
        return builder;
    }
    /**
     * It made a request without a body.
     * @param request It is the kind of request.
     * @param builder It is the builder to create the request.
     * @return It is the new builder with the request created
     */
    protected HttpRequest.Builder requestJsonData( int request, HttpRequest.Builder builder ) {
        switch( request ){
            case AbHttpClientRequest.REQUEST_POST :
                builder = this.requestPOSTJsonData(builder);
                break;
            case AbHttpClientRequest.REQUEST_PUT:
                builder = this.requestPUTJsonData(builder);
                break;
            case AbHttpClientRequest.REQUEST_DELETE:
                builder = this.requestDELETEJsonData(builder);
                break;
        }
        return builder;
    }

}
