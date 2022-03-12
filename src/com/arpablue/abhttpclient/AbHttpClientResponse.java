/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Augusto Flores
 */
class AbHttpClientResponse extends AbHttpClientResponseDAO {

    /**
     * It return true if the object is ready for a http request
     *
     * @return It is true if all is ready for a request.
     */
    protected boolean readyForRequest() {
        
        if (this.getHost() == null) {
            return false;
        }
        if (this.getHost().isEmpty()) {
            return false;
        }
        if (this.getHost().isBlank()) {
            return false;
        }
        return true;
    }
    /**
     * It load sthe headers fron the response.
     */
    protected void loadResponseHeaders() {
        if (this.mResponse == null) {
            return;
        }
        Map<String, List<String>> headers = this.mResponse.headers().map();
        Map<String, String> res = new HashMap<String, String>();
        List<String> list = null;
        String value = "";
        boolean flag = false;
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            list = entry.getValue();
            value = "";
            flag = false;
            for (String cad : list) {
                if (flag) {
                    value = value + ",";
                }
                value = value + cad;
                flag = true;
            }
            res.put(entry.getKey().replace(":", ""), value);
        }
        this.mResponseHeaders = res;
    }

    /**
     * It load the headers tot he builder of the request.
     *
     * @param build it is the build used for the request.
     */
    protected void setHeadersRequest(HttpRequest.Builder build) {
        if (build == null) {
            return;
        }
        Map<String, String> headers = this.getRequestHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            build.header(entry.getKey(), entry.getValue());
        }
    }
    
}
