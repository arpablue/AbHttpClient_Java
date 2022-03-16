/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * tIt calls impment the authentication used in Http request.
 *
 * @author Augusto Flores
 */
public class AbHttpAuthenticator extends AbHttpAuthenticatorDAO {
    /**
     * default constructor.
     */
    public AbHttpAuthenticator(){}
    /**
     * iot constructor allow a user and a password for the credentials.
     * @param user It is the user used in the authentication.
     * @param Password 
     */
    public AbHttpAuthenticator( String user, String Password){
        
    }
    /**
     * It return the Authentication used in the request.
     *
     * @return It is the Authenthication object to be used.
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        if (this.mPassword == null) {
            return new PasswordAuthentication(
                    this.mUser,
                    null
            );
        }
        return new PasswordAuthentication(
                this.mUser,
                this.mPassword.toCharArray()
        );
    }
}
