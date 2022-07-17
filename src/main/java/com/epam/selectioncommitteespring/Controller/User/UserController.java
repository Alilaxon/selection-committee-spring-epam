package com.epam.selectioncommitteespring.Controller.User;


import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userPage(@RequestParam(value = "userId",required = false,defaultValue="1") Long id, Model model){
       User user = userService.findUserById(id);

        System.out.println(user.getUsername()+ " " + user.getEmail());
       model.addAttribute("userProfile",user);
        return "User/UserPage";
    }
}
