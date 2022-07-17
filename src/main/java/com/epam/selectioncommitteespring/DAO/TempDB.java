package com.epam.selectioncommitteespring.DAO;

import com.epam.selectioncommitteespring.Model.DTO.UserForm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TempDB {
    private List<UserForm> users = new ArrayList<>();

    public void save(UserForm user){

        users.add(user);
    }
    public List<UserForm> index(){

        return users;
    }
}
