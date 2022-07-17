package com.epam.selectioncommitteespring.Controller.Guest;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping
    public String login(){
        return "loginPage";
    }
}
