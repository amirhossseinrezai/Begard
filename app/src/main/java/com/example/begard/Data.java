package com.example.begard;

import java.io.Serializable;

public class Data implements Serializable {
    private   int[] mImage= new int[]{R.drawable.nature,R.drawable.begard,R.drawable.banner};
    private String[] strData = new String[]{"Ali","Amir","Hosein","Mohammad","Kian","Illia","Mina","Zahra","Fateme","Sasan","Morteza"};
    private   String title ;
    private   String description;

    public Data() {
    }
    public Data(String data){
        this.title = data;
    }

    public String[] getStrData() {
        return strData;
    }
    public void setStrData(String[] strData) {
        this.strData = strData;
    }

    public int[] getmImage() {
        return mImage;
    }
    public void setmImage(int[] mImage) {
        this.mImage = mImage;
    }
    public  String getDescription() {
        return description;
    }
    public  String getTitle() {
        return title;
    }
}
