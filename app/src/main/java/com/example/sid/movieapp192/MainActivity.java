package com.example.sid.movieapp192;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataListener {
    private ListView mListView;
    ArrayList<Model> list;
    CustomAdapter customAdapter;
    String URL ="http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lvMovieList);

        list = new ArrayList<>();
        //checking internet connection
        if (isConnectedToInternet()){
            DataTask dataTask = new DataTask(this,URL,this);
            dataTask.execute();
        }
        else{
            Toast.makeText(this, "No Internet Connection..!!", Toast.LENGTH_SHORT).show();
        }
        //adding adpter to listview
        customAdapter = new CustomAdapter(this,list);
        mListView.setAdapter(customAdapter);
    }

    private boolean isConnectedToInternet() {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null){
            isConnected=true;
        }
        return isConnected;
    }

    @Override
    //parsing data
    public void updateList(String data) {
        try {
            JSONObject jsonObject =  new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                String Name = object.getString("name");
                String Votes = object.getString("vote_count");
                String Id = object.getString("id");

                Model model = new Model();
                model.setmName(Name);
                model.setmId(Id);
                model.setmVotes(Votes);
                list.add(model);
            }
            customAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
