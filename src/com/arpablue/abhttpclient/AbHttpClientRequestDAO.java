/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Augusto Flores
 */
class AbHttpClientRequestDAO extends AbHttpClientFile {

    /**
     * It allow the redirection in the request.
     */
    private boolean mAllowRedirect = true;
    /**
     * it contain the data to be sent in the HttpRequest, the contians should be
     * in JSON format.
     */
    protected String mData;
    /**
     * It contains the parameters used for the request.
     */
    protected Map<String, String> mParams;

    /**
     * It specify the if the connection allow redirect
     *
     * @param redirect
     */
    public void setAllowRedirect(boolean redirect) {
        this.mAllowRedirect = redirect;
    }

    /**
     * It return if the redirection is allowed.
     *
     * @return It is true the redirection is allowed.
     */
    public boolean isAllowRedirect() {
        return this.mAllowRedirect;
    }

    /**
     * it specify the data to send in the request.
     *
     * @param data It is the data in JSON format.
     */
    public void setData(String data) {
        mData = data;
    }

    /**
     * It return the data used inthe request.
     *
     * @return it s the data used in the equest.
     */
    public String getData() {
        return this.mData;
    }

    /**
     * Add a parameter to the current request. It is used to pass form
     * parameters in a request.
     *
     * @param name it is the name of the parameter in the form
     * @param value It is the value of the parameter.
     */
    public void addParam(String name, String value) {
        if (this.mParams == null) {
            this.mParams = new HashMap<String, String>();
        }
        if (name == null) {
            return;
        }
        if (name.isEmpty()) {
            return;
        }
        if (value == null) {
            value = "";
        }
        mParams.put(name, value);
    }
    /**
     * It return the current params in JSON format. If the parameters not exists then return an empty string.
     * @return It is the parameters in JSON foramt.
     */
    protected String getParamsJSON(){
        String res = "";
        if( !this.parametersExists() ){
            return "";
        }
        res = res +"{";
        Map<String, String> list = this.mParams;
        boolean flag = false;
        for(Map.Entry<String, String> entry : list.entrySet() ){
            if( flag ){
                res = res + ",";
            }
            flag = true;
            res = res + "\""+entry.getKey()+"\":\""+entry.getValue()+"\"";
            
        }
        res = res + "}";
        return res;
    }
    /**
     * It verify if parameters exists in the 
     * @return 
     */
   protected  boolean parametersExists(){
        if( this.mParams == null ){
            return false;
        }
        if( this.mParams.size() < 1 ){
            return false;
        }
        return true;
   }
    /**
     * It return a string with the parameters for a GET request, if t not
     * parameters exists then return a empty string.
     *
     * @return It i sthe string that contians the parameters for a GET request.
     */
    protected String getParamsForm() {
        if (this.mParams == null) {
            return "";
        }
        if (this.mParams.size() < 1) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        res.append("?");
        boolean flag = false;
        for (Map.Entry<String, String> entry : this.mParams.entrySet()) {
            if (flag) {
                res.append("&");
            }
            flag = true;
            res.append(entry.getKey());
            res.append("=");
            res.append(entry.getValue());
        }
        return res.toString();
    }

    /**
     * It create the client with all necessary settings for the request. If a
     * problem exists in the moment to create the builder then return null.
     *
     * @param uri It is the uri used by the builder.
     * @return It is the builder created.
     */
    protected HttpClient createClient() {
        HttpClient client = null;
        HttpClient.Builder builder = null;
        try {
            builder = HttpClient.newBuilder();

            if (this.isAllowRedirect()) {
                builder = builder.followRedirects(
                        HttpClient.Redirect.ALWAYS
                );
            }
            client = builder.build();

        } catch (Exception e) {
            log("ERROR: " + e.getMessage());
        } finally {
            return client;
        }

    }

}
