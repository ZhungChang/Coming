package com.example.yuchi.coming.common.clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by choes_000 on 2015/3/16.
 * Show time.
 */
public class TimeText {
    private DateFormat df;

    public TimeText(){
        df = new SimpleDateFormat("HH:mm:ss");
    }

    protected String StringTime(Date date){
        return df.format(date);
    }
}
