package com.busnow.entity;

import org.json.JSONException;
import org.json.JSONObject;

import static com.busnow.constants.EntityConstants.*;

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
            BusCode = HTTP_REQUEST_ERROR_MESSAGE;
        }

    }

    public String getBusCode() {
        return BusCode;
    }

    public ThisBus getNext() {
        return next;
    }

    public ThisBus getNext2() {
        return next2;
    }

    public ThisBus getNext3() {
        return next3;
    }
}
