/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;



import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Augusto Flores
 */
class AbHttpClientRequestDAO extends AbHttpClientRequestCookie {

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
            builder = this.applyCookies(builder);
            client = builder.build();
        } catch (Exception e) {
            log("ERROR: " + e.getMessage());
        } finally {
            return client;
        }
    }

}
