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
public class Logger implements IHttpLog
{


    @Override
    public void log(String message) {
        System.out.println( message );
    }

    @Override
    public void log() {
        log("");
    }
    
}
