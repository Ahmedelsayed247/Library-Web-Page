/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author pvv
 */
public class FileControl {
    
    public static String text = "";
    
    public static void readfile (File file) throws FileNotFoundException, IOException{
        FileInputStream fis = new FileInputStream(file) ;
        String out = "" ;
        int count = 0 ;
        while((count = fis.read()) != -1){
            out += (char)count ;
        }
        fis.close(); 
        text = out ;
    }
    
    public static void write(File file) throws FileNotFoundException, IOException{
        FileOutputStream fio =new FileOutputStream(file) ;
        byte [] b = text.getBytes() ;
        fio.write(b);
        fio.close();
    }
    
}