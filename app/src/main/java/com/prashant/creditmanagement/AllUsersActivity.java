package com.prashant.creditmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AllUsersActivity extends AppCompatActivity {

    ListView listView;
    static ArrayAdapter adapter;
    static int indexOfUserSelectedForTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        setTitle("All Users");

        listView=findViewById(R.id.listView);

        adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,MainActivity.usersName);
        listView.setAdapter(adapter);

        final Intent intent3=getIntent();
        final int intentStartedByActivity=intent3.getIntExtra("intentStartedByActivity",-1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(intentStartedByActivity!=3){
                    Intent intent2=new Intent(getApplicationContext(),OneUserActivity.class);
                    intent2.putExtra("index",position);
                    startActivity(intent2);
                }
                else{ // if we have reached this activity from OneUserActivity
                    indexOfUserSelectedForTransfer=position;
                    //Log.i("saved value of position",String.valueOf(indexOfUserSelectedForTransfer));
                    finish();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
