package com.demo.retail.fieldengineerretaildemo;

import java.util.List;

public class CuratorPOJO {
    public String title;
    public List<Dataset> dataset;

    public class Dataset{
        String curator_title;
        String curator_tagline;
    }
}
