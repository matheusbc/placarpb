package br.com.matheusbc.placarpb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import br.com.matheusbc.placarpb.parser.JsonParser;
import com.squareup.picasso.Picasso;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
//        ImageView team1 = new ImageView(this);
//        team1.setMinimumWidth(getResources().getDimensionPixelSize(R.dimen.team_shield));
//        team1.setMaxWidth(getResources().getDimensionPixelSize(R.dimen.team_shield));
//        team1.setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.team_shield));
//        team1.setMaxHeight(getResources().getDimensionPixelSize(R.dimen.team_shield));
//        layout.addView(team1);
//        final ImageView team2 = (ImageView) findViewById(R.id.team2);

//        new Downloader(null, team1, null, this).execute("http://www.futebolinterior.com" +
//                ".br/imagens/clubes/escudos_25/121.png");
//        new Team(124, "Teste", "124.png").loadShield(team2, this);

        Picasso.with(this).load("http://placar.futebolinterior.com" +
                ".br/imagens/clubes/escudos_25/162.png").resize(50, 50).placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher).into((ImageView) findViewById(R.id.imageView));
        new JsonParser(this).execute("http://matheusbc.com.br/rounds.php");

    }

    private LinearLayout createGameLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;
    }
}
