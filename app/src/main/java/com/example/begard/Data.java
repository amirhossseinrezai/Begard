package com.example.begard;

import java.io.Serializable;

public class Data implements Serializable {
    public  String title ;
    public  String getDescription() {
        return description;
    }
    public  String description;
    public Data (String dta){
        this.title=dta;
    }
    public  String getTitle() {
        return title;
    }
}
