package com.demo.retail.fieldengineerretaildemo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

import retrofit.RestAdapter;

public class MainActivity extends Activity {

    private static final String API_URL = "http://freemusicarchive.org/api";
    private static final String API_KEY = "60BLHNQCAOUFPIBZ";

    public static final String OBJECT_SALE_KEY = "OBJECT_SALE_KEY";
    public static final String CURATOR_POJO_KEY = "CURATOR_POJO_KEY";

    private TextView nameText;
    private TextView industryText;
    private TextView valueText;
    private TextView messageText;
    private TextView percentText;

    private Button submitButton;
    private Button clearButton;
    private CuratorPOJO curatorPOJO;
    private LinkedList<ObjectSale> listOfObjectSales = new LinkedList<ObjectSale>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (TextView) findViewById(R.id.name_text);
        industryText = (TextView) findViewById(R.id.industry_text);
        valueText = (TextView) findViewById(R.id.value_text);
        messageText = (TextView) findViewById(R.id.message_text);
        percentText = (TextView) findViewById(R.id.percent_text);
        submitButton = (Button) findViewById(R.id.submit_button);
        clearButton = (Button) findViewById(R.id.clear_button);

        if (getIntent().hasExtra(OBJECT_SALE_KEY)) {
            listOfObjectSales = ((ListOfObjectSaleWrapper) getIntent().getExtras().get(OBJECT_SALE_KEY)).getListOfObjectSales();
        } else {
            initLinkedList();
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectSale newObjectSale = new ObjectSale();
                for (int y = 0; y < 5; ++y) {
                    switch (y) {
                        case 0:
                            newObjectSale.setName(nameText.getText().toString());
                            break;
                        case 1:
                            newObjectSale.setIndustry(industryText.getText().toString());
                            break;
                        case 2:
                            newObjectSale.setValue(valueText.getText().toString());
                            break;
                        case 3:
                            newObjectSale.setMessage(messageText.getText().toString());
                            break;
                        case 4:
                            newObjectSale.setPercentage(Integer.parseInt(percentText.getText().toString()));
                            break;
                        default:
                            break;
                    }
                }
                listOfObjectSales.removeLast();
                listOfObjectSales.push(newObjectSale);

                submitData();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameText.setText("");
                industryText.setText("");
                valueText.setText("");
                messageText.setText("");
                percentText.setText("");
            }
        });

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pivotal_green)));
        }
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

    private void submitData() {
        executeRest();
    }

    private void goToTableViewActivity() {
        Intent intent = new Intent(this, TableViewActivity.class);
        ListOfObjectSaleWrapper serializableObject = new ListOfObjectSaleWrapper(listOfObjectSales);
        intent.putExtra(OBJECT_SALE_KEY, serializableObject);
        intent.putExtra(CURATOR_POJO_KEY, curatorPOJO);
        startActivity(intent);
    }

    private void initLinkedList() {
        for (int i = 0; i < 5; ++i) {
            listOfObjectSales.add(new ObjectSale());
        }
    }

    private void executeRest() {
        BackgroundTask task = new BackgroundTask();
        task.execute();
    }

    private class BackgroundTask extends AsyncTask<Void, Void,
            CuratorPOJO> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .build();
        }

        @Override
        protected CuratorPOJO doInBackground(Void... params) {
            IApiMethods methods = restAdapter.create(IApiMethods.class);
            CuratorPOJO curators = methods.getCurators(API_KEY);

            return curators;
        }

        @Override
        protected void onPostExecute(CuratorPOJO curators) {
            curatorPOJO = curators;
            goToTableViewActivity();
        }
    }
}


