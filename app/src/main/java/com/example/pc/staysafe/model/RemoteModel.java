package com.example.pc.staysafe.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.pc.staysafe.R;
import com.example.pc.staysafe.dialog.ProgressBarDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Remote model using HTTP requests
 */
public class RemoteModel {
    public static final String POST_NICK = "nick";
    public static final String POST_HASH = "password";

    private static User user = null;

    private Context context;
    private ProgressBarDialog progressBarDialog;


    public RemoteModel(Context context) {
        this.context = context;
        progressBarDialog = new ProgressBarDialog(context);
    }

    public static User getUser() {
        return user;
    }

    /* =============================================================================================
     * Volley listeners
     * =============================================================================================
     */
    private class OnSuccess implements Response.Listener<JSONObject> {
        private Response.Listener<JSONObject> listener;
        private Response.Listener<JSONObject> secondaryListener;

        OnSuccess(Response.Listener<JSONObject> listener,
                  Response.Listener<JSONObject> secondaryListener) {
            this.listener = listener;
            this.secondaryListener = secondaryListener;
        }

        @Override
        public void onResponse(JSONObject response) {
            progressBarDialog.close();

            listener.onResponse(response);
            if (secondaryListener != null) {
                secondaryListener.onResponse(response);
            }
        }
    }

    private class OnFailure implements Response.ErrorListener {
        private Response.ErrorListener listener;
        private Response.ErrorListener secondaryListener;

        OnFailure(Response.ErrorListener listener, Response.ErrorListener secondaryListener) {
            this.listener = listener;
            this.secondaryListener = secondaryListener;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            progressBarDialog.close();

            Log.w("RemoteModel", error);
            listener.onErrorResponse(error);
            if (secondaryListener != null) {
                secondaryListener.onErrorResponse(error);
            }
        }
    }

    /* =============================================================================================
     * Account management
     * =============================================================================================
     */
    public void login(String nick, String password, Response.Listener<JSONObject> onSuccess,
                      Response.ErrorListener onFailure) {
        try {
            new JSONObjectRequestWrapper(context)
                    .create("WhiteTeam", "login")
                    .put(RemoteModel.POST_NICK, nick)
                    .put(RemoteModel.POST_HASH, password)
                    .send(new OnSuccess(loginOnSuccess, onSuccess),
                          new OnFailure(genericOnFailure, onFailure));
            progressBarDialog.show("Fucking Olaf, fix your wifi already!!!!!");

        } catch (JSONException e) {
            Toast.makeText(context, "Wrong format", Toast.LENGTH_LONG).show();
            if (onFailure == null) {
                onFailure.onErrorResponse(null);
            }
        }
    }

    public void register(String nick, String password, Response.Listener<JSONObject> onSuccess,
                      Response.ErrorListener onFailure) {
        try {
            new JSONObjectRequestWrapper(context)
                    .create("WhiteTeam", "register")
                    .put(RemoteModel.POST_NICK, nick)
                    .put(RemoteModel.POST_HASH, password)
                    .send(new OnSuccess(registerOnSuccess, onSuccess),
                            new OnFailure(genericOnFailure, onFailure));
            progressBarDialog.show("Fucking Olaf, fix your wifi already!!!!!");

        } catch (JSONException e) {
            Toast.makeText(context, "Wrong format", Toast.LENGTH_LONG).show();
            if (onFailure == null) {
                onFailure.onErrorResponse(null);
            }
        }
    }

    private Response.Listener<JSONObject> loginOnSuccess = response -> {
        Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show();
    };

    private Response.Listener<JSONObject> registerOnSuccess = response -> {
        try {
            int test = response.getInt("code");
            Log.w("AAA", String.valueOf(test));
            Log.w("AAA", response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "You have been registered; Please log in.", Toast.LENGTH_SHORT).show();
    };

    private Response.ErrorListener genericOnFailure = response -> {
        Toast.makeText(context, "Error, please check a connection", Toast.LENGTH_SHORT).show();
    };


    private class User {

    }
}
