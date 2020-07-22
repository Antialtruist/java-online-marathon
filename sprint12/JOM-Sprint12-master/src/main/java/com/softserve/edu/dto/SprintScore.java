package com.softserve.edu.dto;

public class SprintScore {
    private String sprintName;
    private int score;

    public SprintScore(String sprintName, int score) {
        this.sprintName = sprintName;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + score;
        result = prime * result + ((sprintName == null) ? 0 : sprintName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SprintScore other = (SprintScore) obj;
        if (score != other.score)
            return false;
        if (sprintName == null) {
            return other.sprintName == null;
        } else return sprintName.equals(other.sprintName);
    }
}
