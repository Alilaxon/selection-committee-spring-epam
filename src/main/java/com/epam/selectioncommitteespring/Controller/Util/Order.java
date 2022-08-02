package com.epam.selectioncommitteespring.Controller.Util;

import com.epam.selectioncommitteespring.Model.Entity.Faculty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    public static List<Faculty> sort(List<Faculty> input, String sort, String order) {
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
}
