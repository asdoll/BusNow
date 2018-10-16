package com.busnow.entity;

import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.DateTime;

import static org.joda.time.Seconds.seconds;
import static org.joda.time.Seconds.secondsBetween;
import static com.busnow.constants.EntityConstants.*;

public class ThisBus {
    private String busCode;
    private int time;
    private String type;
    private String load;

    ThisBus (String busCode, JSONObject JsonData){
        this.busCode = busCode;
        generateData(JsonData);
    }

    private void generateData(JSONObject jsonData) {
        try {
            int seconds = getTime(jsonData.getString("EstimatedArrival"));
            time = seconds/TIME_SECOND_TO_MIN;
            type = jsonData.getString("Type");
            load = jsonData.getString("Load");
        } catch (Exception e){
            busCode = HTTP_REQUEST_ERROR_MESSAGE;
        }
    }

    private int getTime(String ArrivalTime){
        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeParser();
        DateTime dtz = formatter.parseDateTime(ArrivalTime);
        DateTime now = DateTime.now();
        Seconds time;
        if (now.isAfter(dtz)){
            time = seconds(0);
        }else {
            time = secondsBetween(now,dtz);
        }
        return time.getSeconds();
    }

    public String getBusCode() {
        return busCode;
    }

    public int getComingTime() {
        return time;
    }

    public String getLoad() {
        return load;
    }

    public String getType() {
        return type;
    }
}
