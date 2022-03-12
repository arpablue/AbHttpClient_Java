/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Augusto Flores
 */
class AbHttpClientFile extends AbHttpClientResponse {

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
