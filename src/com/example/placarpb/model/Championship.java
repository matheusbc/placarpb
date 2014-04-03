package com.example.placarpb.model;

public class Championship {
    private final int mId;
    private final String mName;
    private final String mRoundId;
    private final Round[] mRounds;

    public Championship(int id, String name, String roundId, Round... rounds) {
        this.mId = id;
        this.mName = name;
        mRoundId = roundId;
        this.mRounds = rounds;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Round[] getRounds() {
        return mRounds;
    }

    @Override
    public String toString() {
        return mName;
    }
}
