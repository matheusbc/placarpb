package com.example.placarpb;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.placarpb.download.Downloader;

import java.util.logging.LogRecord;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        ImageView team1 = new ImageView(this);
        team1.setMinimumWidth(getResources().getDimensionPixelSize(R.dimen.team_shield));
        team1.setMaxWidth(getResources().getDimensionPixelSize(R.dimen.team_shield));
        team1.setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.team_shield));
        team1.setMaxHeight(getResources().getDimensionPixelSize(R.dimen.team_shield));
        layout.addView(team1);
        final ImageView team2 = (ImageView) findViewById(R.id.team2);

        new Downloader(null, team1, null, this).execute("http://www.futebolinterior.com" +
                ".br/imagens/clubes/escudos_25/121.png");
        new Downloader(null, team2, null, this).execute("http://www.futebolinterior.com" +
                ".br/imagens/clubes/escudos_25/122.png");
    }
}
