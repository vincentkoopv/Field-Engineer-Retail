package com.demo.retail.fieldengineerretaildemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends Activity {

    public static final String LIST_OF_VALUES = "LIST_OF_VALUES";

    private TextView nameText;
    private TextView industryText;
    private TextView valueText;
    private TextView messageText;
    private Button submitButton;
    private Button clearButton;

    private ArrayList<Stack<String>> listOfValues = new ArrayList<Stack<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (TextView) findViewById(R.id.name_text);
        industryText = (TextView) findViewById(R.id.industry_text);
        valueText = (TextView) findViewById(R.id.value_text);
        messageText = (TextView) findViewById(R.id.message_text);
        submitButton = (Button) findViewById(R.id.submit_button);
        clearButton = (Button) findViewById(R.id.clear_button);

        if (getIntent().hasExtra(LIST_OF_VALUES)) {
            ListOfStringValues serializedList = (ListOfStringValues) getIntent().getExtras().get(LIST_OF_VALUES);
            listOfValues = serializedList.getListOfValues();
        } else {
            initListOfStringValues();
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int y = 1; y < 5; ++y) {
                    Stack<String> newStack = listOfValues.get(y);
                    String newValue;
                    switch (y) {
                        case 1:
                            newValue = nameText.getText().toString();
                            break;
                        case 2:
                            newValue = industryText.getText().toString();
                            break;
                        case 3:
                            newValue = valueText.getText().toString();
                            break;
                        case 4:
                            newValue = messageText.getText().toString();
                            break;
                        default:
                            newValue = "-";
                            break;
                    }

                    if (newStack.size() == 5) {
                        newStack.pop();
                        newStack.push(newValue);
                    } else {
                        newStack.push(newValue);
                    }
                    listOfValues.set(y, newStack);
                }
                showTableView();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameText.setText("");
                industryText.setText("");
                valueText.setText("");
                messageText.setText("");
            }
        });

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

    private void showTableView() {
        Intent intent = new Intent(this, TableViewActivity.class);
        ListOfStringValues serializedList = new ListOfStringValues(listOfValues);
        intent.putExtra(LIST_OF_VALUES, serializedList);
        startActivity(intent);
    }

    private void initListOfStringValues() {
        for (int x = 0; x < 5; ++x) {
            Stack<String> initStack = new Stack<String>();
            listOfValues.add(initStack);
        }
    }
}
