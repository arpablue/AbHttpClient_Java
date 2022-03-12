/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

/**
 * It contain the methods to the mençmbers of the class.
 *
 * @author Augusto Flores
 */
class AbHttpClientDAO extends AbHttpClienLog {
    /**
     * It is the tiem that is waiting to stablish  a connection.
     */
    private int mTimeOut = 5000;
    /**
     * It is the max time to wait to get a response.
     */
    private int mReadTimeOut = 5000;
    /**
     * It is the list of parameters used for the connection 
     */
    Map<String,String> mParams;
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
     * It containt the reference to the host.
     */
    private String mHost;
    /**
     * Default constructor.
     */
    AbHttpClientDAO(){}
    /**
     * It set the in format a url.
     * @param target It is the url to be set in format.
     * @return It is the url under format.
     */
    protected static String setFormat(String target){
        if( target == null ){
            return null; 
        }
        if( target.isEmpty()){
            return null;
        }
        target = "/"+target;
        target = target.replace("/", " ");
        target = target.trim();
        target = target.replace(" ", "/");
        return target;
    }
    /**
     * It spoecify the host ot be used.
     * @param host It is the host if reference.
     */
    public void setHost(String host){
        mHost = setFormat( host );
    }
    /**
     * It return the current host.
     * @return It is the current host.
     */
    public String getHost(){
        return mHost;
    }
    /**
     * It set the max time to wait to stablish a connection, the units are in seconds.
     * @param miliseconds it sht ethe mx tiem to wait to stablish a connection.
     */
    public void setTiemOut( int miliseconds){
        this.mTimeOut = miliseconds;
    }
    /**
     * It return the max tiem to wait for to stablish a connection, the units are seconds.
     * @return it is the max time to wait for a connection.
     */
    public int getTiemOut(){
        return this.mTimeOut;
    }
    /**
     * It specify the max tiem to wait for a response, the units are in seconds.
     * @param miliseconds It is the max tiem to wait a response.
     */
    public void setReadTimeOut( int miliseconds){
        this.mReadTimeOut = miliseconds;
    }
    /**
     * It return the max time to wait for a response, the unita ar in miliseconds.
     * @return it is the max time to wait for a response.
     */
    public int getReadTimeOut(){
        return this.mReadTimeOut;
    }
    /**
     * It return true if the data gfora  request is ready.
     * @return It is ture if all data is ready for a request.
     */
    protected boolean isReady(){
        String host = this.getHost();
        if( host == null ){
            return false;
        }
        if( host.isEmpty() ){
            return false;
        }
        return true;
    }
            
}
