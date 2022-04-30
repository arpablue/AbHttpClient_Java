/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClientPUT  extends AbHttpClientPOST {
        /**
     * It made a single request without body.
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestPUTNoBody(HttpRequest.Builder builder) {
        builder = builder.PUT(HttpRequest.BodyPublishers.noBody());
        return builder;
    }
    /**
     * It made a request with data from a form.
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestPUTFormData(HttpRequest.Builder builder) {
        byte[] data = this.getParamsForm().getBytes(StandardCharsets.UTF_8);
        builder = builder.PUT(HttpRequest.BodyPublishers.ofByteArray(data));
        return builder;
    }
    /**
     * It made a request using a json to send the data
     * @param builder It is the builder of the request.
     * @return It is true the request has been made without problems.
     */
    protected HttpRequest.Builder requestPUTJsonData(HttpRequest.Builder builder) {
                builder = builder.PUT(HttpRequest.BodyPublishers.ofString( this.mData ));
        return builder;
    }
}
