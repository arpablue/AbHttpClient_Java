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
public class AbHttpClientDELETE extends AbHttpClientPUT {
        /**
     * It made a single request without body.
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestDELETENoBody(HttpRequest.Builder builder) {
        builder = builder.DELETE();
        return builder;
    }
    /**
     * It made a request with data from a form.
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestDELETEFormData(HttpRequest.Builder builder) {
        builder = builder.DELETE();
        return builder;
    }
    /**
     * It made a request using a json to send the data
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestDELETEJsonData(HttpRequest.Builder builder) {
                builder = builder.DELETE();
        return builder;
    }

}
