/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

/**
 * It is the interface to be applied to a log object to sent message.
 * @author Augusto Flores
 */
 interface IHttpLog {
    /**
     * It send a line with a end of line character.
     * @param message It is the message send tot he logger.
     */
    void log(String message);
    /**
     * It sent a empty line to the log.
     */
    void log();
}
