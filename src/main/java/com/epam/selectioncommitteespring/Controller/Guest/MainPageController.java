package com.epam.selectioncommitteespring.Controller.Guest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainPageController {

    @GetMapping
    public String main(){
        return "main";
    }


}
