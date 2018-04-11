package com.example.pc.staysafe.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class JSONObjectRequestWrapper {
    private final String URL_TEMPLATE = "http://app16.sspbrno.cz/eu/%s/API/%s.php";

    private static RequestQueue queue;
    private JSONObject jsonData;
    private String url;


    public JSONObjectRequestWrapper(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }
        jsonData = new JSONObject();
    }

    /**
     * Initialize URL
     * @param teamName name of the team
     * @param action action af the action, request is send to `{action}.php`
     * @return current instance
     */
    public JSONObjectRequestWrapper create(String teamName, String action) {
        url = String.format(URL_TEMPLATE, teamName, action);
        return this;
    }

    /**
     * Add data to POST
     */
    public JSONObjectRequestWrapper put(String key, int value) throws JSONException {
        jsonData.put(key, value);
        return this;
    }

    /**
     * Add data to POST
     */
    public JSONObjectRequestWrapper put(String key, long value) throws JSONException {
        jsonData.put(key, value);
        return this;
    }

    /**
     * Add data to POST
     */
    public JSONObjectRequestWrapper put(String key, double value) throws JSONException {
        jsonData.put(key, value);
        return this;
    }

    /**
     * Add data to POST
     */
    public JSONObjectRequestWrapper put(String key, Object value) throws JSONException {
        jsonData.put(key, value);
        return this;
    }

    /**
     * Add data to POST
     */
    public JSONObjectRequestWrapper put(String key, Boolean value) throws JSONException {
        jsonData.put(key, value);
        return this;
    }

    /**
     * Send requests and calls given listener
     * @param listener executed when response was successfully received
     * @param errorListener executed wen failed
     */
    public void send(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        queue.add(new JsonObjectRequest(Request.Method.POST, url, jsonData, listener, errorListener));
    }

    public void send(Response.Listener<JSONObject> listener) {
        queue.add(new JsonObjectRequest(Request.Method.POST, url, jsonData, listener, printError));
    }

    /**
     * Default errorListener
     */
    private Response.ErrorListener printError = error -> { Log.w("AAA", error.toString()); };
}