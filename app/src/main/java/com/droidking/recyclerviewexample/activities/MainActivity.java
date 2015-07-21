package com.droidking.recyclerviewexample.activities;

import android.content.Intent;
import android.database.Cursor;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.droidking.recyclerviewexample.R;
import com.droidking.recyclerviewexample.adapters.MainRecyclerViewAdapter;
import com.droidking.recyclerviewexample.database.MyDatabase;
import com.droidking.recyclerviewexample.model.Information;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    RecyclerView mainRecyclerView;
    MainRecyclerViewAdapter mainRecyclerViewAdapter;
    MyDatabase myDatabase;
    FloatingActionButton shawGroupsBtn;
    String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shawGroupsBtn = (FloatingActionButton) findViewById(R.id.shawGroupsBtn);
        mainRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);


        myDatabase = new MyDatabase(this);

        setMainRecyclerView();

        shawGroupsBtn.setOnClickListener(this);

    }


    public List<Information> getData() {
        List<Information> data = new ArrayList<>();
        Information mainInfo = null;
        Cursor c = myDatabase.getGroupData();
        if (c != null) {
            while (c.moveToNext()) {
                int nameIndex = c.getColumnIndex(myDatabase.GROUP_NAME);
                String nameText = c.getString(nameIndex);
                this.groupName = nameText;
                int notesIndex = c.getColumnIndex(myDatabase.GROUP_NOTES);
                String notesText = c.getString(notesIndex);
                mainInfo = new Information();
                mainInfo.setGROUP_NAME(nameText);
                mainInfo.setGROUP_NOTES(notesText);
                mainInfo.setGROUP_FULL_ICON_ID(R.mipmap.ic_group_full);
                data.add(mainInfo);

            }
        }

        return data;
    }


    public void setMainRecyclerView() {
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(getData());
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(mainRecyclerViewAdapter);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shawGroupsBtn:
                Intent i4 = new Intent(getBaseContext(), AddNewGroup.class);
                startActivity(i4);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMainRecyclerView();
    }
}
