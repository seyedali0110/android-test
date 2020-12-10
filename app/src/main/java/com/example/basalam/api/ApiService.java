package com.example.basalam.api;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.basalam.model.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private final Context context;

    public ApiService(Context context) {
        this.context = context;
    }

    public void getPosts(final DataCallback DataCallback) {

        final String url = "https://jsonplaceholder.typicode.com/photos?albumId=1";
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Data> data = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    Data data1 = new Data();
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        data1.setTitle(jsonObject.getString("title"));
                        data1.setImageAddress(jsonObject.getString("thumbnailUrl"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    data.add(data1);
                }
                DataCallback.onDataReceived(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonArrayRequest);
    }

    public interface DataCallback {
        void onDataReceived(List<Data> data);
    }
}

