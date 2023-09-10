package com.nilesh.stocksman.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nilesh.stocksman.R;
import com.nilesh.stocksman.adapters.GlobalAdapter;
import com.nilesh.stocksman.adapters.StockAdapter;
import com.nilesh.stocksman.models.GlobalData;
import com.nilesh.stocksman.utils.Datafetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView global_recycler;
    private GlobalAdapter globalAdapter;

    private List<GlobalData> glist;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        global_recycler = v.findViewById(R.id.globalrec);
        global_recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        glist = new ArrayList<>();
        globalAdapter = new GlobalAdapter(glist);
        global_recycler.setAdapter(globalAdapter);

        fetchGlobalData();

        return v;
    }


    private void fetchGlobalData(){
        String url = "https://www.alphavantage.co/query?function=MARKET_STATUS&apikey=A5KBD19HM58U6A9V";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the "data" JSON array from the response
                            JSONArray dataArray = response.getJSONArray("markets");
                            int maxItemsToShow = 4;

                            // Iterate through the JSON array and populate the RecyclerView
                            for (int i = 0;i < Math.min(maxItemsToShow, dataArray.length()); i++) {
                                JSONObject item = dataArray.getJSONObject(i);
                                String market_type = item.getString("market_type");
                                    String region = item.getString("region");
                                    String local_open = item.getString("local_open");
                                    String local_close = item.getString("local_close");
                                    String current_status = item.getString("current_status");


                                    GlobalData gdata = new GlobalData(market_type, region, local_open, local_close, current_status);
                                    glist.add(gdata);
                            }

                            globalAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(jsonObjectRequest);    }


}

//    private void fetchGlobalData() {
//        // Instantiate the RequestQueue
//
////        A5KBD19HM58U6A9V
//        String url = "https://www.alphavantage.co/query?function=MARKET_STATUS&apikey=A5KBD19HM58U6A9V";
//
//        RequestQueue queue = Volley.newRequestQueue(requireContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//
//                        for (int i = 0; i < response.length(); i++) {
//
//                            try {
//
//
//
////                                JSONObject jsonObject = response.getJSONObject(i);
////
////                                JSONArray array = jsonObject.getJSONArray("markets");
////
////                                for (int j = 0; j < array.length(); j++) {
////
////                                    JSONObject object  = array.getJSONObject(j);
////
////
////                                    String market_type = object.getString("market_type");
////                                    String region = object.getString("region");
////                                    String local_open = object.getString("local_open");
////                                    String local_close = object.getString("local_close");
////                                    String current_status = object.getString("current_status");
//
//
////                                    GlobalData gdata = new GlobalData(market_type, region, local_open, local_close, current_status);
////                                    glist.add(gdata);
//
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
//                        }
//                        globalAdapter.notifyDataSetChanged();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//
//        queue.add(jsonArrayRequest);
//    }
//}