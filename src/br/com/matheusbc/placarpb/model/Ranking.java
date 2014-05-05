package br.com.matheusbc.placarpb.model;

public class Ranking implements Comparable<Ranking> {
    private String mTeamName;
    private final String mLegendColor;
    private final int mPoints;
    private final int mGames;
    private final int mWins;
    private final int mGoals;
    private final int mPosition;

    public Ranking(String legendColor, int points, int games, int wins,
                   int goals, int position) {
        this.mLegendColor = legendColor;
        this.mPoints = points;
        this.mGames = games;
        this.mWins = wins;
        this.mGoals = goals;
        this.mPosition = position;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getLegendColor() {
        return mLegendColor;
    }

    public int getPoints() {
        return mPoints;
    }

    public int getGames() {
        return mGames;
    }

    public int getWins() {
        return mWins;
    }

    public int getGoals() {
        return mGoals;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
    }

    @Override
    public String toString() {
        return mPosition + " - " + mTeamName + " - " + mPoints + " " + mGames + " " + mWins + " "
                + mGoals;
    }

    @Override
    public int compareTo(Ranking another) {
        final int comparison;
        if (mPosition < another.getPosition()) {
            comparison = 1;
        } else if (mPosition > another.getPosition()) {
            comparison = -1;
        } else if (mWins > another.getWins()) {
            comparison = 1;
        } else if (mWins < another.getWins()) {
            comparison = -1;
        } else if (mGoals > another.getGoals()) {
            comparison = 1;
        } else if (mGoals < another.getGoals()) {
            comparison = -1;
        } else {
            comparison = 0;
        }
        return comparison;
    }
}
