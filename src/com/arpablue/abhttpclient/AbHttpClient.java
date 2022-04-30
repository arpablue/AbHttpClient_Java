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
public class AbHttpClient extends AbHttpClientRequest {

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
        String params = this.getParamsForm();
        if( !params.isEmpty() ){
            uri = uri + "?" + this.getParamsForm();
        }
       log("HTTP GET request to: " + uri);
        if (!getRequest(uri)) {
            return null;
        }
        afterRequest();
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
        return doRequest( AbHttpClientRequest.REQUEST_POST , endPoint);
   }
    /**
     * It execute a HTTP POST request.
     *
     * @param endPoint It is the enpoint of the host to execute the HTTP POST
     * request.
     * @return It is the response body of the request.
     */
    public String put(String endPoint) {
        return doRequest( AbHttpClientRequest.REQUEST_PUT , endPoint);
   }
    /**
     * It execute a HTTP POST request.
     *
     * @param endPoint It is the enpoint of the host to execute the HTTP POST
     * request.
     * @return It is the response body of the request.
     */
    public String delete(String endPoint) {
        return doRequest( AbHttpClientRequest.REQUEST_DELETE , endPoint);
   }
    /**
     * This execute an update HTTP request to a endpoint
     * @param requestType It is the request type
     * @param endPoint It is the enfpoint to do the request
     * @return It is the response of therequest.
     */
    protected String doRequest( int requestType, String endPoint){
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
        
        log("HTTP request to: " + uri);

        if (!request( AbHttpClientRequest.REQUEST_POST ,uri)) {
            return null;
        }
        afterRequest();
        return this.getResponseBody();
        
    }
    /**
     * Thes are the action executed after the request.
     */
    protected void afterRequest(){
        if (this.mResponse != null) {
            this.mStatus = this.mResponse.statusCode();
            this.mBody = this.mResponse.body();
        }
        this.loadResponseHeaders();
        this.loadCookiesResponse();
        
    }

}
