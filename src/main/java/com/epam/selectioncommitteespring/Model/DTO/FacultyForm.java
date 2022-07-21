package com.epam.selectioncommitteespring.Model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FacultyForm {


    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer budget_places;

    @NotNull
    private Integer general_places;
}
