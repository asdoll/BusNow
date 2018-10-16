package com.busnow.app;

import org.json.JSONException;
import org.json.JSONObject;

public class BusHere {
    private String BusCode;
    private ThisBus next;
    private ThisBus next2;
    private ThisBus next3;

    BusHere(JSONObject JsonData){
        generateBusData(JsonData);
    }

    private void generateBusData(JSONObject jsonData) {
        try {
            BusCode = jsonData.getString("ServiceNo");
            next = new ThisBus(BusCode,jsonData.getJSONObject("NextBus"));
            next2 = new ThisBus(BusCode,jsonData.getJSONObject("NextBus2"));
            next3 = new ThisBus(BusCode,jsonData.getJSONObject("NextBus3"));
        } catch (JSONException e){
            BusCode = "ERROR";
        }

    }
}
