package com.epam.selectioncommittee.spring.model.service;

import com.epam.selectioncommittee.spring.model.builders.FacultyBuilder;
import com.epam.selectioncommittee.spring.model.dto.FacultyForm;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.exception.FacultyIsReservedException;
import com.epam.selectioncommittee.spring.model.exception.SubjectIsReservedException;
import com.epam.selectioncommittee.spring.model.repository.FacultyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private static final Logger log = LogManager.getLogger(FacultyService.class);
    private  final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(FacultyForm facultyForm) throws FacultyIsReservedException {

        checkFacultyName(facultyForm.getFacultyName());

        log.info("Faculty '{}' was created", facultyForm.getFacultyName());

        return facultyRepository.save(FacultyBuilder.builder()
                .facultyName(facultyForm.getFacultyName())
                .facultyNameRU(facultyForm.getFacultyNameRU())
                .budgetPlaces(facultyForm.getGeneralPlaces())
                .generalPlaces(facultyForm.getGeneralPlaces())
                .requiredSubjects(facultyForm.getRequiredSubjects())
                .recruitment(false)
                .build());
    }


    private void checkFacultyName(String name) throws FacultyIsReservedException {

        if (facultyRepository.existsByName(name)) {

            log.warn("Faculty '{}' is reserved", name);

            throw new FacultyIsReservedException();
        }
    }

    public List<Faculty> getAllFaculties() {

        return facultyRepository.findAll();
    }

    public void deleteFaculty(Long id) {

        log.warn("Faculty = '{}' was deleted", facultyRepository.findById(id).get().getName());

        facultyRepository.deleteById(id);
    }

    public Faculty getFaculty(Long id) {

        return facultyRepository.findById(id).get();

    }

    public Faculty updateFaculty(FacultyForm facultyForm) throws FacultyIsReservedException {

        if (checkFacultyRename(facultyForm)) {

            throw new FacultyIsReservedException();
        }

        log.info("Faculty '{}' was updated", facultyForm.getFacultyName());

        return facultyRepository.save(FacultyBuilder.builder()
                .id(facultyForm.getId())
                .facultyName(facultyForm.getFacultyName())
                .facultyNameRU(facultyForm.getFacultyNameRU())
                .budgetPlaces(facultyForm.getBudgetPlaces())
                .generalPlaces(facultyForm.getGeneralPlaces())
                .requiredSubjects(facultyForm.getRequiredSubjects())
                .recruitment(facultyForm.getRecruitment())
                .build());
    }

    public Faculty closeFacultyById(Long id) {

        Faculty faculty = facultyRepository.findById(id).get();
        faculty.setRecruitment(true);

        log.info("Faculty '{}'  closed recruitment", faculty.getName());

        return facultyRepository.save(faculty);
    }

    public Faculty openFacultyById(Long id) {

        Faculty faculty = facultyRepository.findById(id).get();
        faculty.setRecruitment(false);

        log.info("Faculty '{}'  opened recruitment", faculty.getName());

        return facultyRepository.save(faculty);
    }

    private boolean checkFacultyRename(FacultyForm facultyForm) {
        return facultyRepository.existsByName(facultyForm.getFacultyName()) &&
                !facultyRepository.findByName(facultyForm.getFacultyName()).getId().equals(facultyForm.getId());
    }



}
