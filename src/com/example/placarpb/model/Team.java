package com.example.placarpb.model;

import android.content.Context;
import android.widget.ImageView;
import com.example.placarpb.download.Downloader;

public class Team {
    private final int mId;
    private final String mName;
    private final String mShield;
    private final static String FI_URL = "http://www.futebolinterior.com" +
            ".br/imagens/clubes/escudos_25/";

    public Team(int id, String name, String shield) {
        this.mId = id;
        this.mName = name;
        this.mShield = shield;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getShield() {
        return mShield;
    }

    public void loadShield(ImageView imageView, Context context) {
        new Downloader(null, imageView, null, context).execute(FI_URL + getShield());
    }

    @Override
    public String toString() {
        return mName;
    }
}
