/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.abhttpclient;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Augusto Flores
 */
public class AbHttpClientFileTest {
    Logger log = new Logger();
    public AbHttpClientFileTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * It verify if a file exists.
     * @param path it is the the file.
     * @return It is true if the file exists
     */
    protected static boolean fileExists(String path){
        try{
            if( path == null ){
                return false;
            }
            if( path.isEmpty() ){
                return false;
            }
            File file = new File(path);
            if( !file.exists() ){
                return false;
            }
            if( !file.isFile()){
                return false;
            }
            return true;
        }catch(Exception e){
            
        }
        return false;
    }

    /**
     * Test of downLoad method, of class AbHttpClientFile.
     */
    @Test
    public void test_AbHttpClient_DownLoad() {
        System.out.println("--------------- test_AbHttpClient_DownLoad");
        String remoteFile = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/e567fb97-6603-48a6-afa5-b27957c72221/d81eojv-d4468b32-785a-4662-a8c1-09af20a02ca9.jpg/v1/fill/w_1024,h_683,q_75,strp/robotic_spider_man_by_itripto1234-d81eojv.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwic3ViIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl0sIm9iaiI6W1t7InBhdGgiOiIvZi9lNTY3ZmI5Ny02NjAzLTQ4YTYtYWZhNS1iMjc5NTdjNzIyMjEvZDgxZW9qdi1kNDQ2OGIzMi03ODVhLTQ2NjItYThjMS0wOWFmMjBhMDJjYTkuanBnIiwid2lkdGgiOiI8PTEwMjQiLCJoZWlnaHQiOiI8PTY4MyJ9XV19.0c31vprE2hd2UzuJMR6XmF2BfwLgAUUzk33kYu968VM";
        String localFile = "testResults/testTargetFile.jpg";
        AbHttpClientFile instance = new AbHttpClientFile();
        instance.setLog( log );
        boolean expResult = false;
        boolean result = instance.downLoad( remoteFile, localFile );
        if( !result ){
            fail("Problems to try to download the file.");
         }
        result = fileExists( localFile );
        if( !result ){
            fail("The local file is not created of the dowloaded file");
        }
        
    }
    
}
