package com.epam.selectioncommitteespring.Model.service;

import com.epam.selectioncommitteespring.Model.DTO.FacultyForm;
import com.epam.selectioncommitteespring.Model.Entity.Faculty;
import com.epam.selectioncommitteespring.Model.exception.FacultyIsReservedException;
import com.epam.selectioncommitteespring.Model.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;

    public Faculty addFaculty(FacultyForm facultyForm) throws FacultyIsReservedException {
        checkFacultyName(facultyForm.getFacultyName());
        Faculty faculty = new Faculty();
        faculty.setName(facultyForm.getFacultyName());
        faculty.setBudgetPlaces(facultyForm.getBudgetPlaces());
        faculty.setGeneralPlaces(facultyForm.getGeneralPlaces());

        return facultyRepository.save(faculty);
    }

    private void checkFacultyName(String name) throws FacultyIsReservedException {
        if(facultyRepository.existsByName(name)){
            throw new FacultyIsReservedException();
        }
    }

    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }
}
