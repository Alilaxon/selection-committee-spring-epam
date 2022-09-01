package com.epam.selectioncommittee.spring.controller.util;

import java.util.List;

public  class AverageGrade {

    private AverageGrade() {
    }

    public static Long counter(List<Long> list){

        return Math.round(list.stream()
                .mapToLong(Long::longValue)
                .average().getAsDouble());
    }
}
