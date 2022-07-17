package com.epam.selectioncommitteespring.Controller.Admin;


import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminPage(
            @RequestParam(value = "userId",
                          required = false,
                          defaultValue="4") Long id, Model model){

        User user = userService.findUserById(id);

        System.out.println(user.getUsername()+ " " + user.getEmail());
        model.addAttribute("userProfile",user);

        return "Admin/AdminPage";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model){
        System.out.println(userService.getAllUsers());
     model.addAttribute("users",userService.getAllUsers());

        return "admin/AllUsers";
    }
}
