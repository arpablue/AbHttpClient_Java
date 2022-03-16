/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.Authenticator;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpAuthenticatorDAO extends Authenticator {

    /**
     * It i sthe user used for the authentication.
     */
    protected String mUser;
    /**
     * It is the pasword used in the authentication.
     */
    protected String mPassword;
    /**
     * It Ispecify the user 
     * @param user 
     */
    public void setUser( String user){
        this.mUser = user;
    }
    /**
     * It specify the password used in the authotication.
     * @param password It is the password used in the authentivçcation
     */
    public void setPassword( String password ){
        this.mPassword = password;
    }
    /**
     * It return the user used inthe authentication.
     * @return It is the user of the authentication.
     */
    public String getUser(){
        return this.mUser;
    }
    /**
     * It rturn the password used in the authentication,
     * @return It is the password used in the authentication.
     */
    public String getPassword(){
        return this.mPassword;
    }
    /**
     * It return the auhentication used.
     * @return It is the authentication used.
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("{\"autherization\":{\"user\"=");
        
        if( this.mUser == null ){
            res.append("null");
        }else{
            res.append("\""+this.mUser+"\"");
        }
        
        res.append(",\"password\":");
        
        if( this.mUser == null ){
            res.append("null");
        }else{
            res.append("\""+this.mPassword+"\"");
        }
        
        res.append("}}");
        return res.toString();
    }
}
