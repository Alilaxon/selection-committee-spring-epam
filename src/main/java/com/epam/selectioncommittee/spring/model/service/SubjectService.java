package com.epam.selectioncommittee.spring.model.service;

import com.epam.selectioncommittee.spring.model.builders.SubjectBuilder;
import com.epam.selectioncommittee.spring.model.dto.SubjectForm;
import com.epam.selectioncommittee.spring.model.entity.Subject;
import com.epam.selectioncommittee.spring.model.exception.SubjectIsReservedException;
import com.epam.selectioncommittee.spring.model.repository.SubjectRepository;
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

    private  final SubjectRepository subjectRepository;

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
