package com.softserve.edu.dto;

import java.util.List;
import java.util.Objects;

public class MentorStudent {
    private String mentorName;
    private List<String> studentNames;

    public MentorStudent(String mentorName, List<String> studentNames) {
        this.mentorName = mentorName;
        this.studentNames = studentNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MentorStudent that = (MentorStudent) o;

        if (!Objects.equals(mentorName, that.mentorName)) return false;
        return Objects.equals(studentNames, that.studentNames);
    }

    @Override
    public int hashCode() {
        int result = mentorName != null ? mentorName.hashCode() : 0;
        result = 31 * result + (studentNames != null ? studentNames.hashCode() : 0);
        return result;
    }
}
