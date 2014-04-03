package com.example.placarpb.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import com.example.placarpb.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonParser extends AsyncTask<String, Integer, List<Championship>> {
    private static final String FI_URL = "http://www.futebolinterior.com.br/gerados/placar_";
    private final Context mContext;
    private ProgressDialog mDialog;

    public JsonParser(Context context) {
        mContext = context;
    }

    @Override
    protected List<Championship> doInBackground(String... urls) {
        int count = urls.length;
        Log.i("COUNT", "" + count);
        List<Championship> championships = new ArrayList<Championship>();
        for (int i = 0; i < count; i++) {
            try {
                URL url = new URL(urls[i]);
                championships = readJsonStream(url.openStream());
                publishProgress((int) ((i / (float) count) * 100));
                if (isCancelled()) break;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return championships;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog = ProgressDialog.show(mContext, "Loading...",
                "Carregando resultados.", true);
    }

    @Override
    protected void onPostExecute(List<Championship> championships) {
        super.onPostExecute(championships);
        mDialog.dismiss();
        for (Championship c : championships) {
            Log.i("CHAMPIONSHIP", c.toString());
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    public List<Championship> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Championship> championships = new ArrayList<Championship>();
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                championships.add(getChampionship(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
        return championships;
    }

    private Championship getChampionship(JsonReader reader) throws IOException {
        int id = 0;
        String championshipName = "";
        String roundId = "";
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if ("id".equals(name)) {
                id = Integer.valueOf(reader.nextString());
            } else if ("championship_name".equals(name)) {
                championshipName = reader.nextString();
            } else if ("round_id".equals(name)) {
                roundId = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        URL url = new URL(FI_URL + roundId + ".json");
        Round[] rounds = readChampionshipsGroups(url.openStream());
        return new Championship(id, championshipName, roundId);
    }

    public Round[] readChampionshipsGroups(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        Log.i("JSON", "!");
        try {
            return readGroups(reader);
        } finally {
            reader.close();
        }
    }

    public Round[] readGroups(JsonReader reader) throws IOException {
        List<Round> groups = new ArrayList<Round>();
        Log.i("JSONNAME", "AQUI");
        reader.beginArray();
        while (reader.hasNext()) {
            Log.i("JSONNAME", "AQUI!");
            groups.add(readGroup(reader));
        }
        reader.endArray();
        Round[] rounds = new Round[groups.size()];
        return rounds;
    }

    private Round readGroup(JsonReader reader) throws IOException {
        Log.i("JSONNAME", "AQUI!!");
        String group = "";
        String roundName = "";
        ArrayList<Game> games = new ArrayList<Game>();
        Ranking[] rankings = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            Log.i("JSONNAME", name);
            if (name.equals("id_ano")) {
                Log.i("JSONANO", reader.nextString() + "");
            } else if (name.equals("tabela")) {
//                reader.skipValue();
                games = readGames(reader);
            } else if (name.equals("classificacao")) {
                rankings = readRankings(reader);
            } else if (name.equals("grupo")) {
                group = reader.nextString();
            } else if (name.equals("rodada")) {
                roundName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Round round = new Round(group, roundName, games, rankings);
        return round;
    }

    private ArrayList<Game> readGames(JsonReader reader) throws IOException {
        ArrayList<Game> games = new ArrayList<Game>();
        reader.beginArray();
        while (reader.hasNext()) {
            games.add(readGame(reader));
        }
        reader.endArray();
        return null;
    }

    private Game readGame(JsonReader reader) throws IOException {
        String homeTeamName = "";
        String visitingTeamName = "";
        String homeTeamShield = "";
        String visitingTeamShield = "";
        String gameStatus = "";
        String gameTime = "";
        String obs = "";
        String[] goals = null;
        int gameId = 0;
        int homeTeamGoals = 0;
        int visitingTeamGoals = 0;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("mandante")) {
                homeTeamName = reader.nextString();
            } else if (name.equals("visitante")) {
                visitingTeamName = reader.nextString();
            } else if (name.equals("escudom")) {
                homeTeamShield = reader.nextString();
            } else if (name.equals("escudov")) {
                visitingTeamShield = reader.nextString();
            } else if (name.equals("id")) {
                gameId = reader.nextInt();
            } else if (name.equals("ptn_mandante")) {
                homeTeamGoals = reader.nextInt();
            } else if (name.equals("ptn_visitante")) {
                visitingTeamGoals = reader.nextInt();
            } else if (name.equals("status")) {
                gameStatus = reader.nextString();
            } else if (name.equals("tempo")) {
                gameTime = reader.nextString();
            } else if (name.equals("obs")) {
                obs = reader.nextString();
            } else if (name.equals("historico")) {
                goals = readGoals(reader);
            } else {
                reader.skipValue();
            }
        }
        Team homeTeam = new Team(Integer.valueOf(homeTeamShield.split("\\.")[0]), homeTeamName,
                homeTeamShield);
        Log.i("JSONHOMETEAM", homeTeam.toString());
        Team visitingTeam = new Team(Integer.valueOf(visitingTeamShield.split("\\.")[0]),
                visitingTeamName, visitingTeamShield);
        Log.i("JSONVISITINGTEAM", visitingTeam.toString());
        Game game = new Game(gameId, gameStatus, homeTeam, visitingTeam, homeTeamGoals,
                visitingTeamGoals, gameTime, obs, goals);
        Log.i("JSONGAME", game.toString());
        reader.endObject();
        return game;
    }

    private String[] readGoals(JsonReader reader) throws IOException {
        ArrayList<String> goals = new ArrayList<String>();
        String name = "";
        String value = "";
        reader.beginObject();
        while (reader.hasNext()) {
            name = reader.nextName();
            value = reader.nextString();
            goals.add(value + name);
        }
        reader.endObject();
        String[] goalsReturn = new String[goals.size()];
        goals.toArray(goalsReturn);
        return goalsReturn;
    }

    private Ranking[] readRankings(JsonReader reader) throws IOException {
        String teamName = "";
        ArrayList<Ranking> rankings = new ArrayList<Ranking>();
        int position = 0;
        reader.beginObject();
        while (reader.hasNext()) {
            teamName = reader.nextName();
            Ranking ranking = readRanking(reader, ++position);
            ranking.setTeamName(teamName);
            rankings.add(ranking);
            Log.i("JSONRANKING", ranking.toString());
        }
        reader.endObject();
        Ranking[] rankingsReturn = new Ranking[rankings.size()];
        rankings.toArray(rankingsReturn);
        return rankingsReturn;
    }

    private Ranking readRanking(JsonReader reader, int position) throws IOException {
        String legendColor = "";
        int points = 0;
        int games = 0;
        int wins = 0;
        int goals = 0;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("cor")) {
                legendColor = reader.nextString();
            } else if (name.equals("pg")) {
                points = Integer.valueOf(reader.nextString());
            } else if (name.equals("jg")) {
                games = Integer.valueOf(reader.nextString());
            } else if (name.equals("vi")) {
                wins = Integer.valueOf(reader.nextString());
            } else if (name.equals("sg")) {
                goals = Integer.valueOf(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Ranking(legendColor, points, games, wins, goals, position);
    }
}
