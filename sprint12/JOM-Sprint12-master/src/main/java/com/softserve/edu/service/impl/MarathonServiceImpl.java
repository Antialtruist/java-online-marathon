package com.softserve.edu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@Service
public class MarathonServiceImpl implements MarathonService {

    private DataService dataService;

    @Autowired
    public MarathonServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public List<String> getStudents() {
        return dataService
                .getStudents()
                .stream()
                .map(Entity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getMentors() {
        return dataService
                .getMentors()
                .stream()
                .map(Entity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public StudentScore studentResult(String studentName) {
        Entity student = dataService.getEntityByName(dataService.getStudents(), studentName);
        List<SprintScore> sprintScore = new ArrayList<>();
        for (Solution s : dataService.getSolution()) {
            if (s.getIdStudent() == student.getId()) {
                sprintScore.add(new SprintScore(
                        dataService.getEntityById(
                                dataService.getSprints(), s.getIdSprint()).getName(),
                        s.getScore()));
            }
        }
        return new StudentScore(studentName, sprintScore);
    }

    @Override
    public List<StudentScore> allStudentsResult() {
        return dataService
                .getStudents()
                .stream()
                .map(student -> studentResult(student.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public AverageScore studentAverage(String studentName) {
        double avg = studentResult(studentName)
                .getSprintScore()
                .stream()
                .mapToInt(SprintScore::getScore)
                .average()
                .orElse(0);
        return new AverageScore(studentName, avg);
    }

    @Override
    public List<AverageScore> allStudentsAverage() {
        return dataService
                .getStudents()
                .stream()
                .map(student -> studentAverage(student.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public MentorStudent mentorStudents(String mentorName) {
        Entity mentor = dataService.getEntityByName(dataService.getMentors(), mentorName);
        List<String> studentNames = dataService
                .getCommunication()
                .stream()
                .filter(communication -> communication.getIdMentor() == mentor.getId())
                .map(communication -> dataService
                        .getEntityById(dataService.getStudents(),
                                communication.getIdStudent()).getName()
                )
                .collect(Collectors.toList());
        return new MentorStudent(mentorName, studentNames);
    }
}
