package com.demo.retail.fieldengineerretaildemo;

import java.io.Serializable;
import java.util.LinkedList;

public class ListOfObjectSaleWrapper implements Serializable {
    private LinkedList<ObjectSale> listOfObjectSales;

    public ListOfObjectSaleWrapper(LinkedList<ObjectSale> newListOfValues){
        listOfObjectSales = newListOfValues;
    }

    public void setListOfObjectSales(LinkedList<ObjectSale> newListOfValues){
        listOfObjectSales = newListOfValues;
    }

    public LinkedList<ObjectSale> getListOfObjectSales(){
        return listOfObjectSales;
    }

}
