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
import java.util.Collections;
import java.util.Comparator;
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

    private TextView nameHeader;
    private TextView industryHeader;
    private TextView valueHeader;
    private TextView messageHeader;
    private TextView percentageHeader;

    private Boolean isDescending = true;

    private Boolean isNameDescending = true;
    private Boolean isIndustryDescending = true;
    private Boolean isValueDescending = true;
    private Boolean isMessageDescending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        restResponse = (TextView) findViewById(R.id.rest_response);
        if (getIntent().hasExtra(CURATOR_POJO_KEY)) {
            curatorPOJO = (CuratorPOJO) getIntent().getExtras().get(CURATOR_POJO_KEY);
            restResponse.setText(curatorPOJO.title + "\n\n");
            for (CuratorPOJO.Dataset dataset : curatorPOJO.dataset) {
                restResponse.setText(restResponse.getText() + dataset.curator_title +
                        " - " + dataset.curator_tagline + "\n");
            }
        } else {
            restResponse.setText(R.string.rest_fail);
        }

        if (getIntent().hasExtra(OBJECT_SALE_KEY)) {
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
        nameHeader = ((TextView) findViewById(R.id.header1));
        industryHeader = ((TextView) findViewById(R.id.header2));
        valueHeader = ((TextView) findViewById(R.id.header3));
        messageHeader = ((TextView) findViewById(R.id.header4));
        percentageHeader = ((TextView) findViewById(R.id.header5));
        labelHeaders();

        nameHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<ObjectSale> sortedListOfObjectSales = listOfObjectSales;
                labelHeaders();
                isNameDescending = !isNameDescending;
                if (isNameDescending) {
                    nameHeader.setText(getString(R.string.name) + "(D)");
                } else {
                    nameHeader.setText(getString(R.string.name) + "(A)");
                }
                Collections.sort(sortedListOfObjectSales, new sortObjectSaleString(0, isNameDescending));

                ListViewAdapter listViewAdapter = new ListViewAdapter(getApplicationContext());
                listViewAdapter.addOrUpdateList(listOfObjectSales);
                listView.setAdapter(listViewAdapter);
                listView.invalidate();
            }
        });

        industryHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<ObjectSale> sortedListOfObjectSales = listOfObjectSales;
                labelHeaders();
                isIndustryDescending = !isIndustryDescending;
                if (isIndustryDescending) {
                    industryHeader.setText(getString(R.string.industry) + "(D)");
                } else {
                    industryHeader.setText(getString(R.string.industry) + "(A)");
                }
                Collections.sort(sortedListOfObjectSales, new sortObjectSaleString(1, isIndustryDescending));

                ListViewAdapter listViewAdapter = new ListViewAdapter(getApplicationContext());
                listViewAdapter.addOrUpdateList(listOfObjectSales);
                listView.setAdapter(listViewAdapter);
                listView.invalidate();
            }
        });

        valueHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<ObjectSale> sortedListOfObjectSales = listOfObjectSales;
                labelHeaders();
                isValueDescending = !isValueDescending;
                if (isValueDescending) {

                    valueHeader.setText(getString(R.string.value) + "(D)");
                } else {
                    valueHeader.setText(getString(R.string.value) + "(A)");
                }
                Collections.sort(sortedListOfObjectSales, new sortObjectSaleString(2, isValueDescending));

                ListViewAdapter listViewAdapter = new ListViewAdapter(getApplicationContext());
                listViewAdapter.addOrUpdateList(listOfObjectSales);
                listView.setAdapter(listViewAdapter);
                listView.invalidate();
            }
        });

        messageHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedList<ObjectSale> sortedListOfObjectSales = listOfObjectSales;
                labelHeaders();
                isMessageDescending = !isMessageDescending;
                if (isMessageDescending) {
                    messageHeader.setText(getString(R.string.message) + "(D)");
                } else {
                    messageHeader.setText(getString(R.string.message) + "(A)");
                }
                Collections.sort(sortedListOfObjectSales, new sortObjectSaleString(3, isMessageDescending));

                ListViewAdapter listViewAdapter = new ListViewAdapter(getApplicationContext());
                listViewAdapter.addOrUpdateList(listOfObjectSales);
                listView.setAdapter(listViewAdapter);
                listView.invalidate();
            }
        });

        percentageHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void labelHeaders(){
        nameHeader.setText(R.string.name);
        industryHeader.setText(R.string.industry);
        valueHeader.setText(R.string.value);
        messageHeader.setText(R.string.message);
        percentageHeader.setText(R.string.percentage);
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


    class sortObjectSaleString implements Comparator<ObjectSale> {
        private int typeToSort = -1;
        private boolean isDescending = true;

        public sortObjectSaleString(int typeToSort, boolean isDescending) {
            this.typeToSort = typeToSort;
            this.isDescending = isDescending;
        }

        @Override
        public int compare(ObjectSale lhs, ObjectSale rhs) {
            if (lhs.getName().trim().isEmpty()) {
                return 1;
            }
            if (isDescending) {
                if (typeToSort == 0) {
                    return lhs.getName().compareToIgnoreCase(rhs.getName());
                } else if (typeToSort == 1) {
                    return lhs.getIndustry().compareToIgnoreCase(rhs.getIndustry());
                } else if (typeToSort == 2) {
                    return lhs.getValue().compareToIgnoreCase(rhs.getValue());
                } else if (typeToSort == 3) {
                    return lhs.getMessage().compareToIgnoreCase(rhs.getMessage());
                } else {
                    return 0;
                }
            } else {
                if (typeToSort == 0) {
                    return -lhs.getName().compareToIgnoreCase(rhs.getName());
                } else if (typeToSort == 1) {
                    return -lhs.getIndustry().compareToIgnoreCase(rhs.getIndustry());
                } else if (typeToSort == 2) {
                    return -lhs.getValue().compareToIgnoreCase(rhs.getValue());
                } else if (typeToSort == 3) {
                    return -lhs.getMessage().compareToIgnoreCase(rhs.getMessage());
                } else {
                    return 0;
                }
            }
        }
    }
}
