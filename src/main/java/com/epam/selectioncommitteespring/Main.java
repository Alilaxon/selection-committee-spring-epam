package com.epam.selectioncommitteespring;

import com.epam.selectioncommitteespring.Model.DTO.UserForm;
import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.repository.UserRepository;
import com.epam.selectioncommitteespring.Model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class Main {

    private  final UserService userService;

    @Autowired
    public Main(UserService userService) {
        this.userService = userService;
    }


    public static void main(String[] args) {


        UserForm userForm = new UserForm();
        userForm.setUsername("alilax");
        userForm.setEmail("alilax@gmail.com");
        userForm.setPassword("qwerty12");
        System.out.println(userForm.getUsername() + "\n" +
                userForm.getEmail() + "\n" + userForm.getPassword());
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        System.out.println(user.getUsername() + "\n" +
                user.getEmail() + "\n" + user.getPassword());



    }


}
