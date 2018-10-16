package com.busnow.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import static com.busnow.constants.EntityConstants.*;

public class StopData {
    private String stopCode;
    private String JsonData;
    private List<BusHere> BusData = new ArrayList<>();

    public StopData (String JsonData) throws Exception {
        this.JsonData = JsonData;
        generateBusDatas(JsonData);
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

    public String getStopCode() {
        return stopCode;
    }
    public List<BusHere> getBusData() {
        return BusData;
    }
}
