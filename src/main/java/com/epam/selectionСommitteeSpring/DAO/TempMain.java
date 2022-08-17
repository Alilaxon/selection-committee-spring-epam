package com.epam.selectionСommitteeSpring.DAO;

import com.epam.selectionСommitteeSpring.model.DTO.UserForm;

public class TempMain {
    public static void main(String[] args) {
        TempDB tempDB = new TempDB();
        UserForm user = new UserForm();
        user.setUsername("Alilax");
        tempDB.save(user);
    }
}
