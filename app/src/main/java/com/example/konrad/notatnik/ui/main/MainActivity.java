package com.example.konrad.notatnik.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.konrad.notatnik.R;
import com.example.konrad.notatnik.ui.main.adapter.MyAdapter;
import com.example.konrad.notatnik.ui.main.storage.StorageTask;
import com.example.konrad.notatnik.ui.task.TaskActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;
    static final int PICK_CONTACT_REQUEST_EDIT = 2;
    private int max_ID = 0;
    private Context mContext = this;
    private RecyclerView mRecyclerView;
    private ArrayList<StorageTask> mArrayListStorageTask = new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadData();

        mRecyclerView = findViewById(R.id.recyclerViewList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        myAdapter = new MyAdapter(mArrayListStorageTask);
        mRecyclerView.setAdapter(myAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,TaskActivity.class);
                startActivityForResult(intent,PICK_CONTACT_REQUEST);
            }
        });

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StorageTask storagetask) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                intent.putExtra(TaskActivity.EXTRA_ID, storagetask.getmOwn_ID());
                intent.putExtra(TaskActivity.EXTRA_TITLE, storagetask.getmTitle());
                intent.putExtra(TaskActivity.EXTRA_DESCRIPTION,storagetask.getmDescription());
                startActivityForResult(intent,PICK_CONTACT_REQUEST_EDIT);
            }
        });

    }
    @Override
    protected  void onPause(){
        super.onPause();
        SaveData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        int id = 0;

        if (requestCode == PICK_CONTACT_REQUEST) {
            if(resultCode == RESULT_OK){

                String title = data.getStringExtra("title");
                String desc = data.getStringExtra("desc");
                StorageTask storageTask = new StorageTask(title,desc,max_ID);
                max_ID++;

                mArrayListStorageTask.add(storageTask);

                SaveData();

            } else if(requestCode == Activity.RESULT_CANCELED){

                }
        }else if(requestCode == PICK_CONTACT_REQUEST_EDIT){
                if(resultCode == RESULT_OK) {
                    String title = data.getStringExtra("title");
                    String desc = data.getStringExtra("desc");
                    id = data.getIntExtra(TaskActivity.EXTRA_ID, -1);

                    mArrayListStorageTask.get(id).setmDescription(desc);
                    mArrayListStorageTask.get(id).setmTitle(title);
                    SaveData();
                }

        }

        SharedPreferences settings = getSharedPreferences("ID", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id_max",max_ID);
        Log.v("Save","value: "+ max_ID);
        editor.commit();

        myAdapter= new MyAdapter(mArrayListStorageTask);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myAdapter);
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken<ArrayList<StorageTask>>() {}.getType();
        mArrayListStorageTask = gson.fromJson(json,type);

        SharedPreferences settings = getSharedPreferences("ID", 0);
        max_ID = settings.getInt("id_max", 0);

        Log.v("Load","value: "+ max_ID);

        if(mArrayListStorageTask == null){
            mArrayListStorageTask = new ArrayList<>();
        }
    }

    private void SaveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mArrayListStorageTask);
        editor.putString("task list",json);
        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
