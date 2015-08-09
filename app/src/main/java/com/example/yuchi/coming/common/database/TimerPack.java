package com.example.yuchi.coming.common.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choes_000 on 2015/5/3.
 */
public class TimerPack {

    private String event;
    private int totalSecond;

    public TimerPack(){

    }

    public TimerPack(String _event, int _second){
        this.event = _event;
        this.totalSecond = _second;
    }

    public String getEventStr(){return event;}

    public void setEventStr(String eventStr){
        this.event = eventStr;
    }

    public int getTotalSecond(){
        return totalSecond;
    }

    public void setTotalSecond(int totalSecond){
        this.totalSecond = totalSecond;
    }
}
