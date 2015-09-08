package com.example.yuchi.coming;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choes_000 on 2015/5/3.
 */
public class TimerPack {

    private static final String TAG = "TimerPack";

    private String event;
    private int totalSecond;
    private int id;

    public TimerPack(){

    }

    public TimerPack(String _event, int _second){
        this.event = _event;
        this.totalSecond = _second;
    }

    public TimerPack(int _id,String _event, int _second){
        this.id = _id;
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

    public int getId(){
        return id;
    }

    public void id(int id){
        this.id = id;
    }
}
