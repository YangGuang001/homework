package com.yang.IOTest;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

/**
 * Created by yz on 2018/4/20.
 */
public class Log4jConfig {
    public void enableInfo(String target){
        LogManager.getLogger(target).setLevel(Level.INFO);
    }

    public void enableWarn(String target){
        LogManager.getLogger(target).setLevel(Level.WARN);
    }

    public void enableError(String target){
        LogManager.getLogger(target).setLevel(Level.ERROR) ;
    }

    public void enableDebug(String target){
        LogManager.getLogger(target).setLevel(Level.DEBUG) ;
    }
    public void enableTrack(String target){
        LogManager.getLogger(target).setLevel(Level.TRACE) ;
    }

    public void enableOff(String target){
        LogManager.getLogger(target).setLevel(Level.OFF) ;
    }
}
