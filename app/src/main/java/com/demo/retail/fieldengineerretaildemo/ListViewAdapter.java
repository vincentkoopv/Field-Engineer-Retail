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
        TextView nthValueTitleText = (TextView) convertView.findViewById(R.id.title);

        switch (position) {
            case 0:
                nthValueTitleText.setText(R.string.first_value);
                break;
            case 1:
                nthValueTitleText.setText(R.string.second_value);
                break;
            case 2:
                nthValueTitleText.setText(R.string.third_value);
                break;
            case 3:
                nthValueTitleText.setText(R.string.fourth_value);
                break;
            case 4:
                nthValueTitleText.setText(R.string.fifth_value);
                break;
            default:
                nthValueTitleText.setText(" ");
                break;
        }


        TextView value1 = (TextView) convertView.findViewById(R.id.value1);
        TextView value2 = (TextView) convertView.findViewById(R.id.value2);
        TextView value3 = (TextView) convertView.findViewById(R.id.value3);
        TextView value4 = (TextView) convertView.findViewById(R.id.value4);
        TextView value5 = (TextView) convertView.findViewById(R.id.value5);

        ObjectSale objectSale = listOfObjectSales.get(position);
        for (int columns = 0; columns < getCount(); ++columns) {
            switch (columns) {
                case 0:
                    value1.setText(objectSale.getName());
                    break;
                case 1:
                    value2.setText(objectSale.getIndustry());
                    break;
                case 2:
                    value3.setText(objectSale.getValue());
                    break;
                case 3:
                    value4.setText(objectSale.getMessage());
                    break;
                case 4:
                    value5.setText(objectSale.getPercentage());
                    break;
                default:
                    break;
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
