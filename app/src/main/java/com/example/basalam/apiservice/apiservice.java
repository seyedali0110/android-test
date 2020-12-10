package com.example.basalam.apiservice;



import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.basalam.datamodel.Getset;
//import com.example.readjsonvolley.datamodel.Getset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class apiservice {
    Context context;
    public apiservice(Context context){
        this.context=context;
    }
    public void getpost(final onpost onpost){

        final String url="https://jsonplaceholder.typicode.com/photos?albumId=1";
        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Getset> data=new ArrayList<>();
                for (int i=0;i<response.length();i++)
                {
                    Getset getset=new Getset();
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        getset.setTitle(jsonObject.getString("title"));
                        getset.setUrlimg(jsonObject.getString("thumbnailUrl"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    data.add(getset);
                }
                onpost.onpost(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonArrayRequest);

    }

    public interface onpost
    {
        void onpost(List<Getset>data);
    }


}

