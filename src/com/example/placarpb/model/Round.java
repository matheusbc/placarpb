package com.example.placarpb.model;

import java.util.Arrays;
import java.util.List;

public class Round {
    private final String mGroup;
    private final String mRound;
    private final List<Game> mGames;
    private final Ranking[] mRanking;

    public Round(String mGroup, String mRound, List<Game> games, Ranking... ranking) {
        this.mGroup = mGroup;
        this.mRound = mRound;
        this.mGames = games;
        this.mRanking = ranking;
    }

    public List<Game> getGames() {
        return mGames;
    }

    public Ranking[] getRanking() {
        Arrays.sort(mRanking);
        return mRanking;
    }

    public String getmGroup() {
        return mGroup;
    }

    public String getmRound() {
        return mRound;
    }

    @Override
    public String toString() {
        return "Group: " + mGroup + " - " + mRound + " - Games: " + mGames.toString() + " - " +
                "Ranking: " + Arrays.toString(mRanking);
    }
}
