package com.softserve.edu.dto;

import java.util.Objects;

public class AverageScore {
    private String studentName;
    private double avgScore;

    public AverageScore(String studentName, double avgScore) {
        this.studentName = studentName;
        this.avgScore = avgScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AverageScore that = (AverageScore) o;

        if (Double.compare(that.avgScore, avgScore) != 0) return false;
        return Objects.equals(studentName, that.studentName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = studentName != null ? studentName.hashCode() : 0;
        temp = Double.doubleToLongBits(avgScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
