/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * It contians the implementation of the method to download or manage files inthe server.
 * @author Augusto Flores
 */
class AbHttpClientFile extends AbHttpClientResponse {
    /**
     * It delete a file in the server.
     * @param filePath It is the path of the file.
     * @return It i strue if the file has been deleted wityhout problems.
     */
    protected static boolean deleteFile(String filePath){
        try{
            if( filePath == null ) {
                return false;
            }
            if( filePath.isEmpty() ){
                return false;
            }
            File file = new File( filePath );
            if( !file.exists() ){
                return true;
            }
            if( !file.isFile()){
                return true;
            }
            return file.delete();
            
        }catch(Exception e){
            System.out.println("ERROR: Problems to try to delete the ["+filePath+"]file. "+e.getMessage());
        }
        return false;
    }
    /**
     * It download a file from specified url.
     * @param remoteFileName Ti is the url fo teh file.
     * @param localFileName It is the local name of the file.
     * @return TI is true if the file has been downloaded without problems.
     */
    public boolean downLoad(String remoteFileName, String localFileName) {
        try {
            if( !deleteFile(localFileName) ){
                return false;
            }
            InputStream inputStream = new URL( remoteFileName ).openStream();
            Files.copy( inputStream, Paths.get(localFileName ), StandardCopyOption.REPLACE_EXISTING);
            
            return true;
        } catch (Exception e) {
            log("ERROR: " + e.getMessage());
        }
        return false;
    }
}
