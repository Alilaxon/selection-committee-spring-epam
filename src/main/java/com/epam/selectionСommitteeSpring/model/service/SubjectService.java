package com.epam.selectionСommitteeSpring.model.service;

import com.epam.selectionСommitteeSpring.model.builders.SubjectBuilder;
import com.epam.selectionСommitteeSpring.model.dto.SubjectForm;
import com.epam.selectionСommitteeSpring.model.entity.Subject;
import com.epam.selectionСommitteeSpring.model.exception.SubjectIsReservedException;
import com.epam.selectionСommitteeSpring.model.repository.SubjectRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private static final Logger log = LogManager.getLogger(SubjectService.class);

    SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject createSubject(SubjectForm subjectForm) throws SubjectIsReservedException {

        checkSubjectNameEN(subjectForm.getNameEN());

        log.info("Subject '{}' was created", subjectForm.getNameEN());

        return subjectRepository.save(SubjectBuilder.builder()
                .nameEN(subjectForm.getNameEN())
                .nameRU(subjectForm.getNameRU())
                .build());

    }

    public Page<Subject> getAllSubjectsPage(Pageable pageable) {

        return subjectRepository.findAll(pageable);
    }

    public List<Subject> getAllSubjects() {

        return subjectRepository.findAll();
    }

    private void checkSubjectNameEN(String nameEN) throws SubjectIsReservedException {
        if (subjectRepository.existsByNameEN(nameEN)) {

            log.warn("Subject '{}' is reserved",nameEN);

            throw new SubjectIsReservedException();


        }
    }

    public void deleteSubject(Long id) {

        log.info("Subject '{}' is deleted",subjectRepository.findById(id).get().getNameEN());

        subjectRepository.deleteById(id);
    }
}
