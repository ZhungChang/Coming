package com.example.yuchi.coming.common.database;

/**
 * Created by choes_000 on 2015/5/3.
 */
public class TimerData {
    private String event;

    private String hour;

    private String minute;

    private String second;

    public String getEventStr(){
        return event;
    }

    public void setEventStr(String eventStr){
        this.event = eventStr;
    }

    public String getHourStr(){
        return hour;
    }

    public void setHourStr(String hourStr){
        this.hour = hourStr;
    }

    public String getMinuteStr(){
        return minute;
    }

    public void setMinuteStr(String minuteStr){
        this.minute = minuteStr;
    }

    public String getSecond(){
        return second;
    }

    public void setSecond(String second){
        this.second = second;
    }

    public TimerData(String _event, String _hour, String _minute, String _second){
        event = _event;
        hour = _hour;
        minute = _minute;
        second = _second;
    }
}
