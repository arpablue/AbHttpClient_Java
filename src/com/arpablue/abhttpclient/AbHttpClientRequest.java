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
     * It is the content to identify the PATCH REQUEST.
     */
    protected static final int REQUEST_PATCH = 3;
    /**
     * It is the content to identify the DELETE REQUEST.
     */
    protected static final int REQUEST_DELETE = 4;
    
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
                    builder = requestFormData( requestType, builder );
                }
            } else {
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
        if( request == AbHttpClientRequest.REQUEST_POST ){
            builder = this.requestPOSTNoBody(builder);
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
        if( request == AbHttpClientRequest.REQUEST_POST ){
            builder = this.requestPOSTFormData(builder);
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
        if( request == AbHttpClientRequest.REQUEST_POST ){
            builder = this.requestPOSTJsonData(builder);
        }
        return builder;
    }

}
