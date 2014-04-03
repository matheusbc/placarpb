package com.example.placarpb.model;

import java.util.Arrays;
import java.util.List;

public class Championship {
    private final int mId;
    private final String mName;
    private final Round[] mRounds;

    public Championship(int id, String name, Round... rounds) {
        this.mId = id;
        this.mName = name;
        this.mRounds = rounds;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Round[] getmRounds() {
        return mRounds;
    }

    @Override
    public String toString() {
        return mName;
    }
}
