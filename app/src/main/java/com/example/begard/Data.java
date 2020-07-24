package com.example.begard;

import java.io.Serializable;

public class Data implements Serializable {
    private   int[] mImage= new int[]{R.drawable.nature,R.drawable.begard,R.drawable.banner};
    private String[] strData = new String[]{"Ali","Amir","Hosein","Mohammad","Kian","Illia","Mina","Zahra","Fateme","Sasan","Morteza"};
    private String title ;
    private String description = " اقامتگاه بوم گردی نه چم کاشان که دارای قدمتی مربوط به دوران قاجار است، پس از مرمت های انجام شده برروی بخش های مختلف در سال 1396 با عنوان اقامتگاه سنتی نه چم کاشان به بهره برداری رسید. این اقامتگاه بومگردی برای پذیرایی از گردشگرانی که علاقمند به اقامت در چنین خانه های تاریخی و قدیمی هستند، دارای شش باب اتاق و یک حیاط مرکزی به نام گودال باغچه می باشد. یک باب اتاق در حیاط گودال باغچه و در پایین واقع شده است که برای ورود به این اتاق باید از تعدادی پله عبور کرد علاوه بر آن در بخش ورود به اقامتگاه نیز تعدادی پله وجود دارد که لازم است افراد سالمند و خانواده هایی که کودک خردسال به همراه دارند به این نکته توجه داشته باشند. 5 باب اتاق دیگر نیز در طبقه بالای این محوطه و در کنار یکدیگر واقع شده اند. به دلیل حفظ سبک سنتی و قدیمی تمامی اتاق ها فاقد تلویزیون هستند همچنین 5 باب از اتاق های اقامتگاه بوم گردی نه چم که به خانه تاریخی عادل کاشان نیز معروف است فاقد سرویس بهداشتی و حمام هستند که برای رفاه حال مهمانان گرامی در داخل حیاط 2 سرویس بهداشتی فرهنگی و ایرانی و حمام به صورت عمومی وجود دارد که یکی از آنها سرویس بهداشتی مجهز مرکزی بوده و دارای جکوزی نیز می باشد. ";
    public String PlaceID;
    public String Placename;
    public String Address;
    public String Price;

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
