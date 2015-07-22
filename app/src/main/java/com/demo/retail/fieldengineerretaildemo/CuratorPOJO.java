package com.demo.retail.fieldengineerretaildemo;

import java.io.Serializable;
import java.util.List;

public class CuratorPOJO implements Serializable {
    public String title;
    public List<Dataset> dataset;

    public class Dataset implements Serializable{
        String curator_title;
        String curator_tagline;
    }
}
