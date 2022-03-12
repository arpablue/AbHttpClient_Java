/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Augusto Flores
 */
class AbHttpClientResponseDAO extends AbHttpClientDAO{
    /**
     * It response contain the body of teh response.
     */
    protected String mBody;
    /**
     * it contains the status fo teh response.
     */
    protected int mStatus;
    /**
     * It are the headers used in the request.
     */
    protected Map<String, String> mRequestHeaders;
    /**
     * It is the heaer of the reponse.
     */
    protected Map<String,String> mResponseHeaders;
    /**
     * It is the response.
     */
    protected HttpResponse<String> mResponse;
    /**
     * It return the body of the response, if the response not exists or a request has not been did it
     * then the return null.
     * @return It is the nody of the response.
     */
    public String  getResponseBody(){
        return this.mBody;
    }
    /**
     * It return the headers of the last response, if no request has been made then return null.
     * @return It is the headers of the request.
     */
    public Map<String,String>  getResponseHeaders(){
        if( this.mResponse == null ){
            return null;
        }
        return this.mResponseHeaders;
    }
    /**
     * It return the code of the current status, if a problems appears with the request or the request has not been executed 
     * then return -1
     * @return it the status code of the request.
     */
    
    public int getStatusCode(){
        
        return this.mStatus;
    }
    /**
     * It returnt he value of a header.
     * @param key It i sthe key of the header.
     */
    public String getHeader(String key){
        if( this.mResponseHeaders == null ){
            return null;
        }
        return this.mResponseHeaders.get(key);
    }
    /**
     * It specify the headers used in the request.
     * @param headers It is the list of headers used in the request.
     */
    public void setRequestHeaders( Map<String,String> headers){
        this.mRequestHeaders = headers;
    }
    /**
     * It return the current headers used inthe requests.
     * @return It is the list of the headers.
     */
    public Map<String,String> getRequestHeaders(){
        if( this.mRequestHeaders == null ){
            this.mRequestHeaders = new HashMap<String,String>();
        }
        return this.mRequestHeaders;
    }
    /**
     * It set a value for a header, if the key not exists then is added to the headers, if the key exists the current value is replaced.
     * @param key It i sthe value of the header field.
     * @param value it i sthe value assigned to the header.
     */
    public void setRequestHeaders( String key, String value){
        this.getRequestHeaders().put(key, value);
    }
    /**
     * It return the value of a header used in the HTTP requests, if the key not exists then it return null.
     * @param key it isthe name of the header field.
     * @return  It i sthe current value corresponding to field header specified.
     */
    public String getRequestHeader(String key){
        return this.getRequestHeaders().get(key);
    }
        /**
     * It delete all data related to the response.
     */
    protected void clearResponseData(){
        this.getRequestHeaders().clear();
        this.mResponse = null;
    }
    /**
     * It delete all data related to the request.
     */
    protected void clearRequestData(){
        this.mRequestHeaders.clear();
    }
    /**
     * It delete all data related to the response and request.
     */
    public void clearData(){
        this.clearRequestData();
        this.clearResponseData();
    }
    
}
