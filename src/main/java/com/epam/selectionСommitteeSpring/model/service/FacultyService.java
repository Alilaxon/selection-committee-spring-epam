package com.epam.selectionСommitteeSpring.model.service;

import com.epam.selectionСommitteeSpring.model.DTO.FacultyForm;
import com.epam.selectionСommitteeSpring.model.Entity.Faculty;
import com.epam.selectionСommitteeSpring.model.Entity.Subject;
import com.epam.selectionСommitteeSpring.model.exception.FacultyIsReservedException;
import com.epam.selectionСommitteeSpring.model.repository.FacultyRepository;
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
        faculty.setSubjects(facultyForm.getRequiredSubjects());
        faculty.setRecruitment(false);


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
   public void deleteFaculty(Long id){

        facultyRepository.deleteById(id);
   }

   public Faculty getFaculty(Long id){

        return facultyRepository.findById(id).get();
   }
   public Faculty updateFaculty(FacultyForm facultyForm){

     return facultyRepository
             .save(new Faculty(facultyForm));

   }

    public Faculty closeFacultyById(Long id){

        Faculty faculty = facultyRepository.findById(id).get();
        faculty.setRecruitment(true);

        return facultyRepository
                .save(faculty);
    }
    public Faculty openFacultyById(Long id){

        Faculty faculty = facultyRepository.findById(id).get();
        faculty.setRecruitment(false);

        return facultyRepository.save(faculty);
    }
}
