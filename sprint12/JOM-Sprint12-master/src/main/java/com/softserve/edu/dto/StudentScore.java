package com.softserve.edu.dto;

import java.util.List;

public class StudentScore {
    private String studentName;
    private List<SprintScore> sprintScore;

    public StudentScore(String studentName, List<SprintScore> sprintScore) {
        this.studentName = studentName;
        this.sprintScore = sprintScore;
    }

    public List<SprintScore> getSprintScore() {
        return sprintScore;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sprintScore == null) ? 0 : sprintScore.hashCode());
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
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
		StudentScore other = (StudentScore) obj;
		if (sprintScore == null) {
			if (other.sprintScore != null)
				return false;
		} else if (!sprintScore.equals(other.sprintScore))
			return false;
		if (studentName == null) {
			return other.studentName == null;
		} else return studentName.equals(other.studentName);
	}
}
