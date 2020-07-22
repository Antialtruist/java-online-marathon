package com.softserve.edu.entity;

public class Solution {
    private int idStudent;
    private int idSprint;
    private int score;
    
	public Solution(int idStudent, int idSprint, int score) {
		this.idStudent = idStudent;
		this.idSprint = idSprint;
		this.score = score;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getIdSprint() {
		return idSprint;
	}

	public void setIdSprint(int idSprint) {
		this.idSprint = idSprint;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Solution solution = (Solution) o;

		if (idStudent != solution.idStudent) return false;
		if (idSprint != solution.idSprint) return false;
		return score == solution.score;
	}

	@Override
	public int hashCode() {
		int result = idStudent;
		result = 31 * result + idSprint;
		result = 31 * result + score;
		return result;
	}
}
