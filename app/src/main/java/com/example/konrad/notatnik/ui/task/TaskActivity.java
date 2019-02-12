package com.example.konrad.notatnik.ui.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.notatnik.R;

public class TaskActivity extends AppCompatActivity {

    private TextView mDescribeTextView;
    private TextView mTitleTextView;


    public static final String EXTRA_ID = "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.codinginflow.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        mDescribeTextView = (TextView) findViewById(R.id.contentEditText);
        mTitleTextView = (TextView) findViewById(R.id.titleEditText);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            mTitleTextView.setText(intent.getStringExtra(EXTRA_TITLE));
            mDescribeTextView.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }

        checkActionBar();

    }

    public void checkActionBar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_save){
            Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            int id_task = getIntent().getIntExtra(EXTRA_ID,-1);
            if(id_task != -1) {
                intent.putExtra(EXTRA_ID,  id_task);
            }
            intent.putExtra("desc",mDescribeTextView.getText().toString());
            intent.putExtra("title",mTitleTextView.getText().toString());
            setResult(Activity.RESULT_OK,intent);

            finish();
            return true;
        }
        if (id == R.id.action_Share) {
            Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_delete){
            Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
