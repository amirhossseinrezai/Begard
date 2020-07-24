package com.example.begard;

import android.widget.ImageView;
import java.io.Serializable;

public class Data implements Serializable {

    ImageView imageView;
    public int[] mImage= new int[]{R.drawable.nature,R.drawable.begard,R.drawable.banner,
            R.drawable.ashkani, R.drawable.fereydon, R.drawable.madarbozorg, R.drawable.negar, R.drawable.sotka,
            R.drawable.tarlan};

    private String[] strData = new String[]{"Ali","Amir","Hosein","Mohammad","Kian","Illia","Mina","Zahra","Fateme","Sasan","Morteza"};

    public Data(String placeName, String about) {
    }

    public String getPlaceID() {
        return PlaceID;
    }

    public String getPlacename() {
        return Placename;
    }

    public String getAddress() {
        return Address;
    }

    public String getPrice() {
        return Price;
    }

    public String getAbout() {
        return About;
    }

    public void setPlaceID(String placeID) {
        PlaceID = placeID;
    }

    public void setPlacename(String placename) {
        Placename = placename;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setAbout(String about) {
        About = about;
    }

    //public String Description = " اقامتگاه بوم گردی نه چم کاشان که دارای قدمتی مربوط به دوران قاجار است، پس از مرمت های انجام شده برروی بخش های مختلف در سال 1396 با عنوان اقامتگاه سنتی نه چم کاشان به بهره برداری رسید. این اقامتگاه بومگردی برای پذیرایی از گردشگرانی که علاقمند به اقامت در چنین خانه های تاریخی و قدیمی هستند، دارای شش باب اتاق و یک حیاط مرکزی به نام گودال باغچه می باشد. یک باب اتاق در حیاط گودال باغچه و در پایین واقع شده است که برای ورود به این اتاق باید از تعدادی پله عبور کرد علاوه بر آن در بخش ورود به اقامتگاه نیز تعدادی پله وجود دارد که لازم است افراد سالمند و خانواده هایی که کودک خردسال به همراه دارند به این نکته توجه داشته باشند. 5 باب اتاق دیگر نیز در طبقه بالای این محوطه و در کنار یکدیگر واقع شده اند. به دلیل حفظ سبک سنتی و قدیمی تمامی اتاق ها فاقد تلویزیون هستند همچنین 5 باب از اتاق های اقامتگاه بوم گردی نه چم که به خانه تاریخی عادل کاشان نیز معروف است فاقد سرویس بهداشتی و حمام هستند که برای رفاه حال مهمانان گرامی در داخل حیاط 2 سرویس بهداشتی فرهنگی و ایرانی و حمام به صورت عمومی وجود دارد که یکی از آنها سرویس بهداشتی مجهز مرکزی بوده و دارای جکوزی نیز می باشد. ";
    public String PlaceID;
    public String Placename;
    public String Address;
    public String Price;
    public String About;
    public Data(){}

    public Data(int [] mImage){
        this.mImage = mImage;
    }

    public Data(String PlaceID, String Placename, String Price, String Address, String About){

        this.About = About;
        this.mImage = mImage;
        this.PlaceID = PlaceID;
        this.Placename = Placename;
        this.Price = Price;
        this.Address = Address;
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
    /*public  String getDescription() {
        return description; }
    public  String getTitle() {
        return title;
    }*/
}
