package com.nilesh.stocksman.utils;

import android.content.Context;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nilesh.stocksman.adapters.GlobalAdapter;
import com.nilesh.stocksman.models.GlobalData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Datafetcher {
    private static final String TAG = Datafetcher.class.getSimpleName();
    private Context context;
    private RecyclerView glbrec;
    private GlobalAdapter gadapter;
    private List<GlobalData> gdata;

    public Datafetcher(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.glbrec = glbrec;
        this.gdata = new ArrayList<>();
        this.gadapter = new GlobalAdapter(gdata);
        this.glbrec.setAdapter(gadapter);
    }

    public void fetchDataFromAlphaVantageAPI() {
        String apiUrl = "YOUR_API_URL"; // Replace with your API URL
        String apiKey = "https://www.alphavantage.co/query?function=MARKET_STATUS&apikey=demo"; // Replace with your API key
//        + "?apikey=" + apiKey
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                apiUrl ,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error fetching data: " + error.getMessage());
                    }
                }
        );

        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void parseJsonResponse(JSONArray jsonArray) {
        List<GlobalData> stockDataList = new ArrayList<>();

//        market_type, region, local_open, local_close, current_status;

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                GlobalData stockData = new GlobalData();
                stockData.setMarket_type(jsonObject.getString("market_type"));
                stockData.setRegion(jsonObject.getString("region"));
                stockData.setCurrent_status(jsonObject.getString("current_status"));
                stockData.setLocal_close(jsonObject.getString("local_close"));
                stockData.setLocal_open(jsonObject.getString("local_open"));
                // Parse other data fields

                stockDataList.add(stockData);
            }

            GlobalAdapter adapter = new GlobalAdapter(stockDataList);
            glbrec.setAdapter(adapter);

        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON data: " + e.getMessage());
        }
    }

}

