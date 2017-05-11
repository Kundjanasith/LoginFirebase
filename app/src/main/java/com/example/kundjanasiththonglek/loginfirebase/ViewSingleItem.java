package com.example.kundjanasiththonglek.loginfirebase;

import android.view.View;

/**
 * Created by kundjanasiththonglek on 5/4/17.
 */

public class ViewSingleItem {
    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getImage_Title() {
        return Image_Title;
    }

    public void setImage_Title(String image_Title) {
        Image_Title = image_Title;
    }

    private String Image_URL, Image_Title;

    public ViewSingleItem(String image_URL, String image_Title){
        Image_URL = image_URL;
        Image_Title = image_Title;

    }

    public ViewSingleItem(){

    }
}
