package com.droidking.recyclerviewexample.activities;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.droidking.recyclerviewexample.R;
import com.droidking.recyclerviewexample.database.MyDatabase;

public class AddNewGroup extends AppCompatActivity {

    EditText groupName , groupNotes ;
    Button saveBTN;
    MyDatabase myDatabase;
    String groupNotesText , groupNameText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_group);
        groupName = (EditText) findViewById(R.id.group_name_EditText);
        groupNotes = (EditText) findViewById(R.id.group_notes_EditText);
        saveBTN = (Button) findViewById(R.id.saveBTN);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDatabase = new MyDatabase(this);


        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCurrentFocus() != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                groupNameText = groupName.getText().toString();
                groupNotesText = groupNotes.getText().toString();
                String [] args = new String [] {groupNameText};
                Cursor c = myDatabase.getGroupName(args);

                if (groupNameText.isEmpty()){
                    Toast.makeText(getBaseContext() , "أدخل إسم المجموعة!", Toast.LENGTH_SHORT).show();
                }else if (c != null && c.moveToNext()){
                    Toast.makeText(getBaseContext() , "هذه المجموعة موجودة بالفعل!", Toast.LENGTH_SHORT).show();
                }else{
                    myDatabase.setGroupData(groupNameText, groupNotesText);
                    finish();
                }

            }
        });

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
        }else if (id == android.R.id.home){
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
