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

/**
 *
 * @author Augusto Flores
 */
class AbHttpClientGET extends AbHttpClientPOST {

    /**
     * It execute a single GET request.
     *
     * @param uri It is the endpoint of the host to be requested
     * @param json it is the data send in json format
     * @return It return the response of the execution.
     */
    protected boolean getRequest(String uri) {
        this.mStatus = -1;
        try {
            HttpClient client = createClient();
            HttpRequest.Builder builder = HttpRequest.newBuilder(
                    URI.create(uri)
            );

            builder = builder.GET();

            HttpRequest request = builder.build();

            //---- send the request
            this.mResponse = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            this.mStatus = this.mResponse.statusCode();
            if (this.mResponse != null) {
                this.mBody = this.mResponse.body();
            }
        } catch (Exception e) {
            this.log("HTTP-GET ERROR:  " + e.getMessage());
            return false;
        }
        return true;
    }

}
