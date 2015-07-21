package com.demo.retail.fieldengineerretaildemo;


import java.io.Serializable;

public class ObjectSale implements Serializable {
    private String name = "";
    private String industry = "";
    private String value = "";
    private String message = "";
    private String percentage = "";


    public ObjectSale() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage + "%";
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public String getPercentage() {
        return percentage;
    }

}
