/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClient extends AbHttpClientGET {

    /**
     * Default constructor.
     */
    public AbHttpClient() {
    }
    /**
     * It execute a GET request tot he zspecified end point.
     * @param endPoint It is the endoint of the host.
     * @return It is the response body of the request.
     */
    public String get(String endPoint) {
        clearResponseData();
        if (endPoint == null) {
            endPoint = "";
        }
        endPoint = setFormat(endPoint);
        if (endPoint.isEmpty()) {
            return "";
        }
        if (!this.isReady()) {
            return null;
        }
        String uri = this.getHost() + "/" + endPoint;
        this.mStatus = -1;
        this.mBody = null;
        
        uri = uri + this.getParamsForm();
        if (!getRequest(uri)) {
            return null;
        }
        if (this.mResponse != null) {
            this.mStatus = this.mResponse.statusCode();
            this.mBody = this.mResponse.body();
        }
        this.loadResponseHeaders();
        return this.getResponseBody();
    }

    /**
     * It execute a HTTP POST request.
     *
     * @param endPoint It is the enpoint of the host to execute the HTTP POST
     * request.
     * @return It is the response body of the request.
     */
    public String post(String endPoint) {
        clearResponseData();
        if (endPoint == null) {
            endPoint = "";
        }
        endPoint = setFormat(endPoint);
        if (endPoint.isEmpty()) {
            return "";
        }
        if (!this.isReady()) {
            return null;
        }
        String uri = this.getHost() + "/" + endPoint;
        this.mStatus = -1;
        this.mBody = null;

        if (!postRequest(uri)) {
            return null;
        }
        if (this.mResponse != null) {
            this.mStatus = this.mResponse.statusCode();
            this.mBody = this.mResponse.body();
        }
        this.loadResponseHeaders();
        return this.getResponseBody();
    }

}
