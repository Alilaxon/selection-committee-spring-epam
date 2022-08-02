package com.epam.selectioncommitteespring.Model.DTO;

import com.epam.selectioncommitteespring.Model.Entity.Faculty;
import com.epam.selectioncommitteespring.Model.Entity.User;

import java.util.List;

public class StatementForm {

 User user;

 Faculty faculty;

 List<Long> grades;

 public StatementForm() {
 }

 public StatementForm(User user, Faculty faculty, List<Long> grades) {
  this.user = user;
  this.faculty = faculty;
  this.grades = grades;
 }

 public User getUser() {
  return user;
 }

 public void setUser(User user) {
  this.user = user;
 }

 public Faculty getFaculty() {
  return faculty;
 }

 public void setFaculty(Faculty faculty) {
  this.faculty = faculty;
 }

 public List<Long> getGrades() {
  return grades;
 }

 public void setGrades(List<Long> grades) {
  this.grades = grades;
 }
}
