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
import java.util.LinkedList;
import java.util.Stack;

import static com.demo.retail.fieldengineerretaildemo.MainActivity.OBJECT_SALE_KEY;

public class TableViewActivity extends Activity {
    private ListView listView;
    private Button backButton;

    private ArrayList<Stack<String>> listOfValues = new ArrayList<Stack<String>>();

    private LinkedList<ObjectSale> listOfObjectSales = new LinkedList<ObjectSale>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        listOfObjectSales = ((ListOfObjectSaleWrapper) getIntent().getExtras().get(OBJECT_SALE_KEY)).getListOfObjectSales();

        listView = (ListView) findViewById(R.id.listview);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this);
        listViewAdapter.addOrUpdateList(listOfObjectSales);
        listView.setAdapter(listViewAdapter);

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                ListOfObjectSaleWrapper serializableObject = new ListOfObjectSaleWrapper(listOfObjectSales);
                intent.putExtra(OBJECT_SALE_KEY, serializableObject);
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
