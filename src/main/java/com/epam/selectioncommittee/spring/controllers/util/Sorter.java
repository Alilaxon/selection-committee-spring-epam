package com.epam.selectioncommittee.spring.controllers.util;

import com.epam.selectioncommittee.spring.model.entity.Faculty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter {

    private Sorter() {
    }

    public static List<Faculty> facultySorting(List<Faculty> input, String sort, String order) {
        List<Faculty> facultyList;
        if (sort.equals("generalPlaces")) {
            if (order.equals("desc")) {
                facultyList = input.stream()
                        .sorted(Comparator.comparing(Faculty::getGeneralPlaces).reversed())
                        .collect(Collectors.toList());
            } else {
                facultyList = input.stream()
                        .sorted(Comparator.comparing(Faculty::getGeneralPlaces))
                        .collect(Collectors.toList());
            }
        } else if (sort.equals("budgetPlaces")) {
            if (order.equals("desc")) {
                facultyList = input.stream()
                        .sorted(Comparator.comparing(Faculty::getBudgetPlaces).reversed())
                        .collect(Collectors.toList());
            } else {
                facultyList = input.stream()
                        .sorted(Comparator.comparing(Faculty::getBudgetPlaces))
                        .collect(Collectors.toList());
            }
        } else {
            if (order.equals("desc")) {
                facultyList = input.stream()
                        .sorted(Comparator.comparing(Faculty::getName).reversed())
                        .collect(Collectors.toList());
            } else {

                facultyList = input.stream()
                        .sorted(Comparator.comparing(Faculty::getName))
                        .collect(Collectors.toList());
            }
        }
        return facultyList;
    }
    public static Pageable subjectSorting (

            String page,String size){


        return PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(size), Sort.by("id").ascending());

    }

    public static Pageable userSorting (String order,String sort,String page,String size){
        Pageable pageable;
        if (order.equals("asc")) {

            pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(size), Sort.by(sort).ascending());
        } else {
            pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(size), Sort.by(sort).descending());
        }

        return pageable;

    }
}
