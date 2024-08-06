package com.example.votinsystem;

public class VotingStatusManager {
    private static boolean userVoted = false;

    public static boolean isUserVoted() {
        return userVoted;
    }

    public static void setUserVoted(boolean voted) {
        userVoted = voted;
    }
}

