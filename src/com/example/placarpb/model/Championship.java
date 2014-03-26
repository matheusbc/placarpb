package com.example.placarpb.model;

import java.util.Arrays;
import java.util.List;

public class Championship {
    private final int mId;
    private final String mName;
    private final List<Game> mGames;
    private final Ranking[] mRanking;

    public Championship(int id, String name, List<Game> games, Ranking... ranking) {
        this.mId = id;
        this.mName = name;
        this.mGames = games;
        this.mRanking = ranking;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<Game> getGames() {
        return mGames;
    }

    public Ranking[] getRanking() {
        Arrays.sort(mRanking);
        return mRanking;
    }

    @Override
    public String toString() {
        return mName + " - Games: " + mGames.toString() + " - Ranking: "
                + Arrays.toString(mRanking);
    }
}
