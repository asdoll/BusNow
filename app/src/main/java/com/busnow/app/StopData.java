package com.busnow.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StopData {
    private String stopCode;
    private String JsonData;
    private List<BusHere> BusData = new ArrayList<>();

    StopData (String stopCode) {
        this.stopCode = stopCode;
        generateBusDatas(getCurrentData());
    }

    private String getCurrentData() {
        return null;
    }

    private void generateBusDatas(String JsonData) {
        try {
            JSONObject obj = new JSONObject(JsonData);
            stopCode = obj.getString("BusStopCode");

            JSONArray arr = obj.getJSONArray("Services");
            for (int i = 0; i < arr.length(); i++) {
                BusData.add(new BusHere(arr.getJSONObject(i)));
            }
        } catch (JSONException e) {
            BusData = new ArrayList<>();
        }
    }


}
