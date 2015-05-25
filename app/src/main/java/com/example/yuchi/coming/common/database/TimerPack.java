package com.example.yuchi.coming.common.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choes_000 on 2015/5/3.
 */
public class TimerPack implements Parcelable {

    private String event;
    private int changeToSecond;

    private TimerPack(){

    }

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
