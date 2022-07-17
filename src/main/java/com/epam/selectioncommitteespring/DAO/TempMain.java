package com.epam.selectioncommitteespring.DAO;

import com.epam.selectioncommitteespring.Model.DTO.UserForm;

public class TempMain {
    public static void main(String[] args) {
        TempDB tempDB = new TempDB();
        UserForm user = new UserForm();
        user.setUsername("Alilax");
        tempDB.save(user);
    }
}
