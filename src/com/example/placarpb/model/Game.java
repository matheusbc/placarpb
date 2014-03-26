package com.example.placarpb.model;

public class Game {
    private final int mId;
    private final String mStatus;
    private final Team mHomeTeam;
    private final Team mVisitingTeam;
    private final int mHomeTeamGoals;
    private final int mVisitingTeamGoals;
    private final String mTime;

    public Game(int id, String status, Team homeTeam, Team visitingTeam, int mHomeTeamGoals,
                int mVisitingTeamGoals, String time) {
        this.mId = id;
        this.mStatus = status;
        this.mHomeTeam = homeTeam;
        this.mVisitingTeam = visitingTeam;
        this.mHomeTeamGoals = mHomeTeamGoals;
        this.mVisitingTeamGoals = mVisitingTeamGoals;
        this.mTime = time;
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

    public int getmHomeTeamGoals() {
        return mHomeTeamGoals;
    }

    public int getmVisitingTeamGoals() {
        return mVisitingTeamGoals;
    }

    @Override
    public String toString() {
        return mStatus + " - " + mTime + " | " + mHomeTeam + " " + mHomeTeamGoals + "x" +
                mVisitingTeamGoals + " " + mVisitingTeam;
    }
}
