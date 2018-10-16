package com.busnow.app;

import org.json.JSONObject;

public class ThisBus {
    private String busCode;
    private String time;
    private String type;
    private String load;

    ThisBus (String busCode, JSONObject JsonData){
        this.busCode = busCode;
        generateData(JsonData);
    }

    private void generateData(JSONObject jsonData) {
        try {
            time = jsonData.getString("EstimatedArrival");
            type = jsonData.getString("Type");
            load = jsonData.getString("Load");
        } catch (Exception e){
            busCode = "ERROR";
        }
    }


}
