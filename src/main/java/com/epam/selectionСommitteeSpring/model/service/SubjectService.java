package com.epam.selectionСommitteeSpring.model.service;

import com.epam.selectionСommitteeSpring.model.dto.SubjectForm;
import com.epam.selectionСommitteeSpring.model.entity.Subject;
import com.epam.selectionСommitteeSpring.model.exception.SubjectIsReservedException;
import com.epam.selectionСommitteeSpring.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public Subject addNewSubject(SubjectForm subjectForm) throws SubjectIsReservedException {
        checkSubjectNameEN(subjectForm.getNameEN());
        Subject subject = new Subject();
        subject.setNameEN(subjectForm.getNameEN());
        subject.setNameRU(subjectForm.getNameRU());


        return subjectRepository.save(subject);

    }

    public Page<Subject> getAllSubjectsPage(Pageable pageable){

        return subjectRepository.findAll(pageable);
    }
    public List<Subject> getAllSubjects() {

        return subjectRepository.findAll();
    }

    private void checkSubjectNameEN(String nameEN) throws SubjectIsReservedException {
        if (subjectRepository.existsByNameEN(nameEN)) {
            throw new SubjectIsReservedException();
        }
    }

    public void deleteSubject (Long id){
        subjectRepository.deleteById(id);
    }
}
