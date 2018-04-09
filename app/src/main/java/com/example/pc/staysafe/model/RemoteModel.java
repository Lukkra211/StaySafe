package com.example.pc.staysafe.model;

import java.util.HashMap;

public class RemoteModel {
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
