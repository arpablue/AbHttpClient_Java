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
     * It is the tcode used to specified the PUT request.
     */
    protected final static byte REQUEST_PUT = 1;
    /**
     * It is the tcode used to specified the PUT request.
     */
    protected final static byte REQUEST_POST = 2;
    /**
     * It is the tcode used to specified the PUT request.
     */
    protected final static byte REQUEST_PATCH = 4;
    /**
     * It is the tcode used to specified the PUT request.
     */
    protected final static byte REQUEST_DELETE = 5;

    /**
     * It made a single request without body.
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestPOSTNoBody(HttpRequest.Builder builder) {
        builder = builder.POST(HttpRequest.BodyPublishers.noBody());
        return builder;
    }
    /**
     * It made a request with data from a form.
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestPOSTFormData(HttpRequest.Builder builder) {
        byte[] data = this.getParamsForm().getBytes(StandardCharsets.UTF_8);
        this.setRequestHeaders("Content-type", "application/x-www-form-urlencoded");
        builder = builder.POST(HttpRequest.BodyPublishers.ofByteArray(data));
        return builder;
    }
    /**
     * It made a request using a json to send the data
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestPOSTJsonData(HttpRequest.Builder builder) {
                this.setRequestHeaders("Accept", "application/json");
                this.setRequestHeaders("Content-type", "application/json");
                builder = builder.POST(HttpRequest.BodyPublishers.ofString( this.mData ));
        return builder;
    }

    /**
     * It execute hte Http POST request
     *
     * @param endPoint
     * @return
     */
    protected boolean postRequest(String uri) {
        try {
            String json = null;

            HttpClient client = createClient();
            URI uriB = URI.create(uri);
            HttpRequest.Builder builder = HttpRequest.newBuilder(uriB);
            if (this.mData != null) {
                json = this.mData;
            }
            if (json == null) {
                if (!this.parametersExists()) {
                    builder = builder.POST(HttpRequest.BodyPublishers.noBody());
                } else {
                    byte[] data = this.getParamsForm().getBytes(StandardCharsets.UTF_8);
                    this.setRequestHeaders("Content-type", "application/x-www-form-urlencoded");
                    builder = builder.POST(HttpRequest.BodyPublishers.ofByteArray(data));
                }
            } else {
                this.setRequestHeaders("Accept", "application/json");
                this.setRequestHeaders("Content-type", "application/json");
                builder = builder.POST(HttpRequest.BodyPublishers.ofString(json));
            }
            this.setHeadersRequest(builder);

            HttpRequest request = builder.build();

            this.mResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            this.mStatus = this.mResponse.statusCode();
            if (this.mResponse != null) {
                this.mBody = this.mResponse.body();
            }

            return true;
        } catch (Exception e) {
            log("ERROR: Problems to send the POST http request. " + e.getMessage());
        }
        return false;
    }

}
