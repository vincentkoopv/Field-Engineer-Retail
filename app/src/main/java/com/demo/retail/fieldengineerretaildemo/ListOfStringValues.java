package com.demo.retail.fieldengineerretaildemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class ListOfStringValues implements Serializable {
    private ArrayList<Stack<String>> listOfValues = new ArrayList<Stack<String>>();

    public ListOfStringValues(ArrayList<Stack<String>> newListOfValues){
        listOfValues = newListOfValues;
    }

    public void setListOfValues(ArrayList<Stack<String>> newListOfValues){
        listOfValues = newListOfValues;
    }

    public ArrayList<Stack<String>> getListOfValues(){
        return listOfValues;
    }

}
