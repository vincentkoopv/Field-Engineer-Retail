package com.demo.retail.fieldengineerretaildemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class ListViewAdapter extends BaseAdapter {

    Context context;
    private LinkedList<ObjectSale> listOfObjectSales = new LinkedList<ObjectSale>();

    @Override
    public int getCount() {
        return listOfObjectSales.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfObjectSales.get(position);
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
            case 5:
                titleText.setText(R.string.percentage);
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

        if (position == 0) {
            value1.setText(R.string.first_value);
            value2.setText(R.string.second_value);
            value3.setText(R.string.third_value);
            value4.setText(R.string.fourth_value);
            value5.setText(R.string.fifth_value);
        } else {
            for (int columns = 0; columns < getCount(); ++columns) {
                ObjectSale objectSale = listOfObjectSales.get(columns);
                String value = " ";
                switch(position){
                    case 1:
                        value = objectSale.getName();
                        break;
                    case 2:
                        value = objectSale.getIndustry();
                        break;
                    case 3:
                        value = objectSale.getValue();
                        break;
                    case 4:
                        value = objectSale.getMessage();
                        break;
                    case 5:
                        value = objectSale.getPercentage();
                        break;
                    default:
                        break;
                }

                switch(columns){
                    case 0:
                        value1.setText(value);
                        break;
                    case 1:
                        value2.setText(value);
                        break;
                    case 2:
                        value3.setText(value);
                        break;
                    case 3:
                        value4.setText(value);
                        break;
                    case 4:
                        value5.setText(value);
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

    public void addOrUpdateList(LinkedList<ObjectSale> newListOfObjectSales) {
        listOfObjectSales = newListOfObjectSales;
    }

}
