package com.demo.retail.fieldengineerretaildemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class ListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Stack<String>> listOfValues = new ArrayList<Stack<String>>();

    @Override
    public int getCount() {
        return listOfValues.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listview_row, null);
        TextView titleText = (TextView) convertView.findViewById(R.id.title);

        switch (position) {
            case 1:
                titleText.setText(R.string.name);
                break;
            case 2:
                titleText.setText(R.string.industry);
                break;
            case 3:
                titleText.setText(R.string.value);
                break;
            case 4:
                titleText.setText(R.string.message);
                break;
            default:
                titleText.setText(" ");
                break;
        }


        TextView value1 = (TextView) convertView.findViewById(R.id.value1);
        TextView value2 = (TextView) convertView.findViewById(R.id.value2);
        TextView value3 = (TextView) convertView.findViewById(R.id.value3);
        TextView value4 = (TextView) convertView.findViewById(R.id.value4);
        TextView value5 = (TextView) convertView.findViewById(R.id.value5);

        //Setup headers else setup values
        if (position == 0) {
            value1.setText(R.string.first_value);
            value2.setText(R.string.second_value);
            value3.setText(R.string.third_value);
            value4.setText(R.string.fourth_value);
            value5.setText(R.string.fifth_value);
        } else {
            for (int x = 4; x >= 0; --x) {
                String stringValue = getStringInStack(position, x);

                switch (x) {
                    case 0:
                        value1.setText(stringValue);
                        break;
                    case 1:
                        value2.setText(stringValue);
                        break;
                    case 2:
                        value3.setText(stringValue);
                        break;
                    case 3:
                        value4.setText(stringValue);
                        break;
                    case 4:
                        value5.setText(stringValue);
                        break;
                    default:
                        break;
                }
            }
        }


        return convertView;
    }

    public ListViewAdapter(Context context) {
        this.context = context;
    }

    private String getStringInStack(int listPosition, int stackPosition) {

        if (listOfValues.get(listPosition).size() > stackPosition && listOfValues.get(listPosition).get(stackPosition) != null) {
            return listOfValues.get(listPosition).get(stackPosition);
        }

        return " ";
    }

    public void addOrUpdateList(ArrayList<Stack<String>> newStringStack) {
        listOfValues = newStringStack;
    }

}
