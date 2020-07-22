package com.softserve.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.service.DataService;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    private List<Entity> students = new ArrayList<>();
    private List<Entity> mentors = new ArrayList<>();
    private List<Entity> sprints = new ArrayList<>();
    private List<Communication> communication = new ArrayList<>();
    private List<Solution> solution = new ArrayList<>();

    @Override
    public void addStudent(String studentName) {
        students.add(new Entity(studentName));
    }

    @Override
    public void addMentor(String mentorName) {
        mentors.add(new Entity(mentorName));
    }

    @Override
    public void addSprint(String sprintName) {
    	sprints.add(new Entity(sprintName));
    }

    @Override
    public void addCommunication(String studentName, String mentorName) {
        int studentId = getEntityByName(students,studentName).getId();
        int mentorId = getEntityByName(mentors, mentorName).getId();
        communication.add(new Communication(studentId, mentorId));
    }

    @Override
    public void addSolution(String studentName, String sprintName, int score) {
    	int studentId = getEntityByName(students,studentName).getId();
        int sprintId = getEntityByName(sprints, sprintName).getId();
        solution.add(new Solution(studentId, sprintId, score));
    }

    @Override
    public Entity getEntityByName(List<Entity> entities, String name) {
        for (Entity e : entities) {
            if (e != null && e.getName().equals(name)) {
                return e;
            }
        }
        throw new NullPointerException("Entity with name " + name + " doesn't exist");
    }

    @Override
    public Entity getEntityById(List<Entity> entities, int id) {
        for (Entity e : entities) {
            if (e != null && e.getId() == id) {
                return e;
            }
        }
        throw new NullPointerException("Entity with id " + id + " doesn't exist");
    }

    @Override
	public List<Entity> getStudents() {
		return students;
	}

    @Override
	public List<Entity> getMentors() {
		return mentors;
	}

	@Override
	public List<Entity> getSprints() {
		return sprints;
	}

	@Override
	public List<Communication> getCommunication() {
		return communication;
	}

	@Override
	public List<Solution> getSolution() {
		return solution;
	}
}
