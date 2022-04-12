/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Augusto Flores
 */
class AbHttpClientRequestCookie extends AbHttpClientAuthorization {
    /**
     * It is the cookies of the current request.
     */
    protected Map<String, String> mCookies;
    /**
     * It contain the indicator if the Cookies Manager has been configured.
     */
    public boolean mIsCookieSet = false;
    /**
     * It is the uri refrence to the current URI.
     */
    protected URI mHostURI;
    /**
     * It contain the current Cookie Manager.
     */
    protected CookieManager mCookieMgr;
    /**
     * It return the curren tURI to the host.
     */
    protected URI getHostURI(){
        if( this.mHostURI != null ){
            return mHostURI;
        }
        if( this.getHost() == null ){
            return null;
        }
        mHostURI = URI.create(this.getHost() );
        return mHostURI;
    }
    /**
     * It apply the cookies manager to the request.
     * @param builder It is the builder to be used
     * @return It is the builder with the cokkies configutration.
     */
    protected HttpClient.Builder applySettings( HttpClient.Builder builder ){
        builder = super.applySettings(builder);
        builder = builder.cookieHandler( getCookieManager() );
        return builder;
    }
    /**
     * It returnthe current Cookie Manager.
     * @return It returtn the current CookieManager.
     */
    protected CookieManager getCookieManager() {
        if (mCookieMgr == null) {
            mCookieMgr = new CookieManager();
            //mCookieMgr = new java.net.CookieManager();
            mCookieMgr.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            java.net.CookieHandler.setDefault(mCookieMgr);
        }
        return mCookieMgr;
    }
    /**
     * It add a cokie to the nest request.
     * @param title It is the title of the cookie.
     * @param value It is the value of the cookie
     * @return It is true if the cookie has been added without problems.
     */
    public boolean addCookie(String title, String value){
        try {

            CookieStore cookieJar = getCookieManager().getCookieStore();

            // create cookie
            HttpCookie cookie = new HttpCookie( title, value );
            cookie.setDomain(this.getHost());
            cookie.setPath( "/" );
            cookie.setVersion( 0 );
            // add cookie to CookieStore for a
            
            getCookieManager().getCookieStore().add( getHostURI() , cookie);
            
        } catch (Exception e) {
            log("ERROR: Unable to set cookie using CookieHandler, " + e.getMessage());
            return false;
        }
        return true;
        
    }
    /**
     * It apply the cokkies configuration to set the cookies values in the
     * request.
     *
     * @param builder It is the builder with the cokies configuration.
     * @return
     */
    protected boolean loadCookiesResponse() {
        boolean res = false;
        this.mCookies = new HashMap<String, String>();
        try {
            if (this.getHost() == null) {
                return res;
            }

            // get content from URLConnection;
            // cookies are set by web site
            URL url = new URL(this.getHost());
            URLConnection connection = url.openConnection();
            connection.getContent();
            

            // get cookies from underlying
            // CookieStore
            CookieStore cookieJar = getCookieManager().getCookieStore();
            
            List<HttpCookie> cookies = cookieJar.getCookies();
            
            for (HttpCookie cookie : cookies) {
                mCookies.put(cookie.getName(), cookie.getValue());
            }
            res = true;
        } catch (Exception e) {
            log("ERROR: " + e.getMessage());

        } finally {
            return res;
        }
    }
    /**
     * It return the current cookies used inthe request, this could be used to
     * send values or is the list of cookies of the host after send a request.
     *
     * @return
     */
    public Map<String, String> getCookies() {
        return this.mCookies;
    }
}
