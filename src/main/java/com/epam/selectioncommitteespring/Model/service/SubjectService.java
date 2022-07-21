package com.epam.selectioncommitteespring.Model.service;

import com.epam.selectioncommitteespring.Model.DTO.SubjectForm;
import com.epam.selectioncommitteespring.Model.Entity.Subject;
import com.epam.selectioncommitteespring.Model.exception.SubjectIsReservedException;
import com.epam.selectioncommitteespring.Model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public Subject addNewSubject (SubjectForm subjectForm) throws SubjectIsReservedException {
        checkSubjectNameEN(subjectForm.getNameEN());
        Subject subject = new Subject();
        subject.setNameEN(subjectForm.getNameEN());
        subject.setNameRU(subjectForm.getNameRU());
        subject.setNameUK(subjectForm.getNameUK());

    return     subjectRepository.save(subject);

    }

    public List<Subject> getAllSubjects(){
        return
                subjectRepository.findAll();
    }

    private void checkSubjectNameEN(String nameEN) throws SubjectIsReservedException{
        if(subjectRepository.existsByNameEN(nameEN)){
            throw new SubjectIsReservedException();
        }
    }
}
