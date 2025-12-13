/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.util;

public class MyTime {
    private int hours, minutes, seconds, milliseconds;
    
    public MyTime(int h, int m, int s, int ms){
        if(h < 0 || m < 0 || s < 0 || ms < 0) throw new IllegalArgumentException();
        hours = h;
        minutes = m;
        seconds = s;
        milliseconds = ms;
    }
    
    public MyTime(long s){
        if(s < 0) throw new IllegalArgumentException();
        hours = (int) (s/3600);
        minutes = (int) (s/60 - hours*60);
        seconds = (int) (s - minutes*60);
        milliseconds = 0;
    }
    
    public MyTime(double s){
        this((int)Math.floor(s));
        milliseconds = ((int)((s - Math.floor(s))*1000));
    }
    
    public int toSeconds(){
        return hours*3600 + minutes*60 + seconds + milliseconds*1000;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    public String toString(){
        String ret = "";
        
        if(hours < 10) ret += "0" + hours + ":";
        else ret += hours + ":";
        
        if(minutes < 10) ret += "0" + minutes + ":";
        else ret += minutes + ":";
        
        if(seconds < 10) ret += "0" + seconds;
        else ret += seconds;
        
        if(milliseconds != 0) ret += "." + milliseconds/1000;
        
        return ret;
        //hours + ":" + minutes + ":" + seconds + "." + milliseconds;
    }

}