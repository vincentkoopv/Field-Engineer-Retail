package com.demo.retail.fieldengineerretaildemo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Stack;

import static com.demo.retail.fieldengineerretaildemo.MainActivity.LIST_OF_VALUES;

public class TableViewActivity extends Activity {
    private ListView listView;

    private ArrayList<Stack<String>> listOfValues = new ArrayList<Stack<String>>();

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        ListOfStringValues serializedList = (ListOfStringValues) getIntent().getExtras().get(LIST_OF_VALUES);
        listOfValues = serializedList.getListOfValues();

        listView = (ListView) findViewById(R.id.listview);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this);
        listViewAdapter.addOrUpdateList(listOfValues);
        listView.setAdapter(listViewAdapter);

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                ListOfStringValues serializedList = new ListOfStringValues(listOfValues);
                intent.putExtra(LIST_OF_VALUES, serializedList);
                startActivity(intent);
            }
        });

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
