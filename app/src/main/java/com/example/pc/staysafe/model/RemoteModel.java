package com.example.pc.staysafe.model;

import android.content.Context;

import java.util.HashMap;

/**
 * Remote model using HTTP requests
 */
public class RemoteModel {

    public RemoteModel(Context context) {
    }

    public HashMap<String, Integer> getPointsHistory() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Monday", 100);
        map.put("Tuesday", 120);
        map.put("Wednesday", 200);
        map.put("Tuesday", 200);
        map.put("Friday", 220);
        map.put("Saturday", 270);
        map.put("Sunday", 400);
        
        return map;
    }
}
