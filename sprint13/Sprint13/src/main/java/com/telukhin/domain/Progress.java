package com.telukhin.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@Table(name = "progress")
public class Progress {

  public enum TaskStatus{
    PASS, FAIL, PENDING
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Task task;

  @ManyToOne
  private User trainee;

  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  @CreationTimestamp
  private LocalDate started;

  @UpdateTimestamp
  private LocalDate updated;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public User getTrainee() {
    return trainee;
  }

  public void setTrainee(User trainee) {
    this.trainee = trainee;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public LocalDate getStarted() {
    return started;
  }

  public void setStarted(LocalDate started) {
    this.started = started;
  }

  public LocalDate getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDate updated) {
    this.updated = updated;
  }


}
