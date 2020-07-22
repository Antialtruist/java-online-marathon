package com.softserve.edu.service;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;

import java.util.List;

public interface DataService {

    public void addStudent(String studentName);

    public void addMentor(String mentorName);

    public void addSprint(String sprintName);

    public void addCommunication(String studentName, String mentorName);

    public void addSolution(String studentName, String sprintName, int score);

    public Entity getEntityByName(List<Entity> entities, String name);

    public Entity getEntityById(List<Entity> entities, int id);

    public List<Entity> getStudents();

    public List<Entity> getMentors();

    public List<Entity> getSprints();

    public List<Communication> getCommunication();

    public List<Solution> getSolution();
}