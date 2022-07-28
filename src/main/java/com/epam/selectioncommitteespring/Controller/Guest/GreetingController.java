package com.epam.selectioncommitteespring.Controller.Guest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*

Первый тесторвый контроллер
*/
@Controller

public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                               String name,Model model){
        model.addAttribute("name",name);

        return "greeting";
    }
}
