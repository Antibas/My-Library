/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.io;

import com.antibas.util.Methods;

import java.io.*;
import java.util.function.Function;

/**
 *
 * @author User
 */
public abstract class FileIO {
    public static float fileCompare(File f1, File f2) throws FileNotFoundException{
        String text1 = fileToString(f1), text2 = fileToString(f1);
        float percentage; 
        
        int size = Math.min(text1.length(), text2.length());
        int maxSubS = StringIO.LCS(text1, text2).length();//.getFirst().max();
        
        System.out.println(size);
        System.out.println(maxSubS);
        
        percentage = ((float)(maxSubS)/(float)(size)) * 100f;
        return percentage;
    }
    
    public static String fileToStringSplit(File f, String splitter) throws FileNotFoundException{
        BufferedReader reader = getBufferedReader(f);
        String text;
        String rettext = "";
        try {
            while ((text = reader.readLine()) != null)
                rettext += text + splitter;
        } catch (IOException ex) {
            throw new Error(ex.getMessage());
        }
        return rettext;
    }
    
    public static String fileToString(File f) throws FileNotFoundException{
        BufferedReader reader = getBufferedReader(f);
        String text;
        StringBuilder rettext = new StringBuilder();
        try {
            while ((text = reader.readLine()) != null)
                rettext.append(text);
        } catch (IOException ex) {
            throw new Error(ex.getMessage());
        }
        return rettext.toString();
    }
    
    public static void forEachLine(File f, Function<? super String, ? extends String> action) throws IOException{
    	BufferedReader reader = getBufferedReader(f);
        BufferedWriter writer = getBufferedWriter(f);
        String text;
        try {
            while ((text = reader.readLine()) != null) writer.write(action.apply(text));
        } catch (IOException ex) {
            throw new Error(ex.getMessage());
        }
    }
    
    public static int fileLines(File f) throws FileNotFoundException{
        BufferedReader reader = getBufferedReader(f);
        String text;
        int s = 0;
        try {
            while ((text = reader.readLine()) != null)
                s++;
        } catch (IOException ex) {
            throw new Error(ex.getMessage());
        }
        return s;
    }
    
    public static int fileColumns(File f) throws FileNotFoundException {
        BufferedReader reader = getBufferedReader(f);
        int[] s = new int[45];
        int i = 0;
        String text;
        try {
            while (((text = reader.readLine()) != null)){
                s[i] = text.trim().split(" ").length;
                i++;
            }
        } catch (IOException ex) {
            throw new Error(ex.getMessage());
        }
        return Methods.max(s);
    }
    
    public static BufferedReader getBufferedReader(File f) throws FileNotFoundException {
    	return new BufferedReader(new FileReader(f));
    }
    
    public static BufferedWriter getBufferedWriter(File f) throws IOException {
    	return new BufferedWriter(new FileWriter(f));
    }
}
