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
public class AbHttpClienLog implements IHttpLog{
    /**
     * It is the logger used inthe message.
     */
    private IHttpLog mLog;
    /**
     * It specify the log object used to send message.
     * @param log It is the log object.
     */
    public void setLog(IHttpLog log){
        mLog = log;
    }
    /**
     * It return the current log object.
     * @return 
     */
    public IHttpLog getLog(){
        return mLog;
    }
    /**
     * It send a message to the logger.
     * @param message It is the message send to the logger.
     */
    @Override
    public void log(String message) {
        if(mLog == null){
            return;
        }
        mLog.log(message);
    }
    /**
     * It send an empty text tot he logger.
     */
    @Override
    public void log() {
        log("");
    }
}
