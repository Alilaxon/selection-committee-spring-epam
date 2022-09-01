package com.epam.selectioncommittee.spring.controller.guest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainPageController {

    @GetMapping
    public String main(){
        return "main";
    }


}
