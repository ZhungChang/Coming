package com.example.yuchi.coming.common.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choes_000 on 2015/5/3.
 */
public class TimerPack implements Parcelable {

    private String event;
    private int changeToSecond;
    private int Second, Min, Hr;

    public TimerPack(){

    }

    public int getSecond(){return Second;}

    public void setSecond(int Second){this.Second = Second;}

    public int getMin(){return Min;}

    public void setMin(int Min){this.Min = Min;}

    public int getHr(){return Hr;}

    public void setHr(int Hr){this.Hr = Hr;}

    public String getEventStr(){return event;}

    public void setEventStr(String eventStr){
        this.event = eventStr;
    }

    public int getchangeToSecond(){
        return changeToSecond;
    }

    public void setchangeToSecond(int changeToSecond){
        this.changeToSecond = changeToSecond;
    }

    public TimerPack(String _event, int _second){
        event = _event;
        changeToSecond = _second;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
