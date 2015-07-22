package com.demo.retail.fieldengineerretaildemo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import static com.demo.retail.fieldengineerretaildemo.MainActivity.CURATOR_POJO_KEY;
import static com.demo.retail.fieldengineerretaildemo.MainActivity.OBJECT_SALE_KEY;

public class TableViewActivity extends Activity {
    private ListView listView;
    private Button backButton;
    private TextView restResponse;
    private CuratorPOJO curatorPOJO;
    private LinkedList<ObjectSale> listOfObjectSales = new LinkedList<ObjectSale>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        restResponse = (TextView) findViewById(R.id.rest_response);
        if(getIntent().hasExtra(CURATOR_POJO_KEY)){
            curatorPOJO = (CuratorPOJO) getIntent().getExtras().get(CURATOR_POJO_KEY);
            restResponse.setText(curatorPOJO.title + "\n\n");
            for (CuratorPOJO.Dataset dataset : curatorPOJO.dataset) {
                restResponse.setText(restResponse.getText() + dataset.curator_title +
                        " - " + dataset.curator_tagline + "\n");
            }
        }
        else{
            restResponse.setText(R.string.rest_fail);
        }

        if(getIntent().hasExtra(OBJECT_SALE_KEY)){
            listOfObjectSales = ((ListOfObjectSaleWrapper) getIntent().getExtras().get(OBJECT_SALE_KEY)).getListOfObjectSales();
        }

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

        initHeaders();

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initHeaders() {
        ((TextView) findViewById(R.id.header1)).setText(R.string.name);
        ((TextView) findViewById(R.id.header2)).setText(R.string.industry);
        ((TextView) findViewById(R.id.header3)).setText(R.string.value);
        ((TextView) findViewById(R.id.header4)).setText(R.string.message);
        ((TextView) findViewById(R.id.header5)).setText(R.string.percentage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_table_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
