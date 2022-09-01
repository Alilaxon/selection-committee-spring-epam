package com.epam.selectioncommittee.spring.controllers.util;

import java.util.List;

public  class AverageGrade {

    public static Long counter(List<Long> list){

        return Math.round(list.stream()
                .mapToLong(Long::longValue)
                .average().getAsDouble());
    }
}
