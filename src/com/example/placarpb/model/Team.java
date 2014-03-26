package com.example.placarpb.model;

public class Team {
    private final int mId;
    private final String mName;
    private final String mShield;

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

    @Override
    public String toString() {
        return mName;
    }
}
