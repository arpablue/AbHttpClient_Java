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

import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
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
     * It is the cookies of the current request.
     */
    protected Map<String, String> mCookies;
    /**
     * It is the user used for the authentication.
     */
    private String mAuthUser;
    /**
     * It is the password used for the authentication.
     */
    private String mAuthPwd;

    /**
     * It specify the if the connection allow redirect
     *
     * @param redirect
     */
    public void setAllowRedirect(boolean redirect) {
        this.mAllowRedirect = redirect;
    }

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
     * This encode a prameter for a HTTP request.
     *
     * @param txt It is the texst to be formated.
     * @return It is the string under URL formated.
     */
    protected static String encoderURL(String txt) {
        if (txt == null) {
            return "";
        }
        return URLEncoder.encode(txt, StandardCharsets.UTF_8);
    }

    /**
     * It decode any encode parameters
     *
     * @param txt This the text to encode.
     * @return It is the string decoder.
     */
    protected static String decoderURL(String txt) {
        if (txt == null) {
            return "";
        }
        return URLDecoder.decode(txt, StandardCharsets.UTF_8);
    }

    /**
     * Add a parameter to the current request. It is used to pass form
     * parameters in a request.
     *
     * @param name it is the name of the parameter in the form
     * @param value It is the value of the parameter.
     */
    public void addParam(String name, String value) {
        StringBuilder res = new StringBuilder();
        res.append("");
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
     * It return the current params in JSON format. If the parameters not exists
     * then return an empty string.
     *
     * @return It is the parameters in JSON foramt.
     */
    protected String getParamsJSON() {
        StringBuilder res = new StringBuilder();
        res.append("");
        if (!this.parametersExists()) {
            return "";
        }
        res.append("{");
        Map<String, String> list = this.mParams;
        boolean flag = false;
        for (Map.Entry<String, String> entry : list.entrySet()) {
            if (flag) {
                res.append(",");
            }
            flag = true;
            res.append("\"");
            res.append(entry.getKey().toString());
            res.append("\"");
            res.append(":");
            res.append("\"");
            res.append(entry.getValue().toString());
            res.append("\"");
        }
        res.append("}");
        return res.toString();
    }

    /**
     * It verify if parameters exists in the
     *
     * @return
     */
    protected boolean parametersExists() {
        if (this.mParams == null) {
            return false;
        }
        if (this.mParams.size() < 1) {
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
        //res.append("?");
        boolean flag = false;
        for (Map.Entry<String, String> entry : this.mParams.entrySet()) {
            if (flag) {
                res.append("&");
            }
            flag = true;
            res.append(encoderURL(entry.getKey().toString()));
            res.append("=");
            res.append(encoderURL(entry.getValue()));
        }

        return res.toString();
    }

    /**
     * apply the ciomnfiguratiopnj for the redirect.
     *
     * @param builder it is the builder to apply the configuration.
     * @return returnthe builder witn the redirect.
     */
    protected HttpClient.Builder applyRedirect(HttpClient.Builder builder) {
        try {
            if (this.isAllowRedirect()) {
                builder = builder.followRedirects(
                        HttpClient.Redirect.ALWAYS
                );
            }
        } catch (Exception e) {
            log("ERROR: " + e.getMessage());
        } finally {
            return builder;
        }
    }

    /**
     * this apply the authentication to the request.
     *
     * @param builderit is the builder tro apply the authentication.
     * @return it is the builder with the authentication
     */
    protected HttpClient.Builder applyAuthorization(HttpClient.Builder builder) {
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

    /**
     * It apply the cokkies configuration to set the cookies values in the
     * request.
     *
     * @param builder It is the builder with the cokies configuration.
     * @return
     */
    protected boolean getCookiesRequest() {
        boolean res = false;
        this.mCookies = new HashMap<String, String>();
        try {
            if (this.getHost() == null) {
                return res;
            }
            // Instantiate CookieManager;
            // make sure to set CookiePolicy
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            // get content from URLConnection;
            // cookies are set by web site
            URL url = new URL(this.getHost());
            URLConnection connection = url.openConnection();
            connection.getContent();

            // get cookies from underlying
            // CookieStore
            CookieStore cookieJar = manager.getCookieStore();
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
     * It returnt he current pf the request.
     * @return It is the cookies of the request.
     */
    public Map<String, String> getCookies() {
        return this.mCookies;
    }

    public void setCookiesRequest( URI uri) {
        try {
            // instantiate CookieManager
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            CookieStore cookieJar = manager.getCookieStore();

            // create cookie
            HttpCookie cookie = new HttpCookie("anotherField", "John Doe");

            // add cookie to CookieStore for a
            // particular URL
            URL url = new URL( this.getHost() );
            cookieJar.add( url.toURI(), cookie);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
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
            builder = this.applyRedirect(builder);
            builder = this.applyAuthorization(builder);

            client = builder.build();
        } catch (Exception e) {
            log("ERROR: " + e.getMessage());
        } finally {
            return client;
        }

    }

}
