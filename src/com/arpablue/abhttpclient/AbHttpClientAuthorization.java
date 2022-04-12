/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.http.HttpClient;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClientAuthorization extends AbHttpClientFile {
    /**
     * It is the user used for the authentication.
     */
    private String mAuthUser;
    /**
     * It is the password used for the authentication.
     */
    private String mAuthPwd;
    /**
     * It specify the credentials used for the authorization.
     *
     * @param user It is the user used for the authentication.
     * @param password It i sthe password used for the authentication.
     */
    public void setAuthiorization(String user, String password) {
        this.mAuthUser = user;
        this.mAuthPwd = password;
    }
    /**
     * this apply the authentication to the request.
     *
     * @param builderit is the builder tro apply the authentication.
     * @return it is the builder with the authentication
     */
    protected HttpClient.Builder applySettings(HttpClient.Builder builder) {
        try {
            if (this.mAuthUser != null) {
                AbHttpAuthenticator auth = new AbHttpAuthenticator(this.mAuthUser, this.mAuthPwd);
                builder = builder.authenticator(auth);
            }
        } catch (Exception e) {
            log("ERROR: " + e.getMessage());
        } finally {
            return builder;
        }
    }

}
