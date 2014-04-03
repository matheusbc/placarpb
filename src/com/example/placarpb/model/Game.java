package com.example.placarpb.model;

public class Game {
    private final int mId;
    private final String mStatus;
    private final Team mHomeTeam;
    private final Team mVisitingTeam;
    private final int mHomeTeamGoals;
    private final int mVisitingTeamGoals;
    private final String mTime;
    private final String mObs;
    private String[] mGoals;

    public Game(int id, String status, Team homeTeam, Team visitingTeam, int homeTeamGoals,
                int visitingTeamGoals, String time, String obs, String... goals) {
        this.mId = id;
        this.mStatus = status;
        this.mHomeTeam = homeTeam;
        this.mVisitingTeam = visitingTeam;
        this.mHomeTeamGoals = homeTeamGoals;
        this.mVisitingTeamGoals = visitingTeamGoals;
        this.mTime = time;
        this.mObs = obs;
        this.mGoals = goals;
    }

    public int getId() {
        return mId;
    }

    public String getStatus() {
        return mStatus;
    }

    public Team getHomeTeam() {
        return mHomeTeam;
    }

    public Team getVisitingTeam() {
        return mVisitingTeam;
    }

    public String getTime() {
        return mTime;
    }

    public int getHomeTeamGoals() {
        return mHomeTeamGoals;
    }

    public int getVisitingTeamGoals() {
        return mVisitingTeamGoals;
    }

    @Override
    public String toString() {
        return mStatus + " - " + mTime + " | " + mHomeTeam + " " + mHomeTeamGoals + "x" +
                mVisitingTeamGoals + " " + mVisitingTeam;
    }
}
