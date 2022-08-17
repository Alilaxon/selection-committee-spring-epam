package com.epam.selectionСommitteeSpring.controller.guest;

import com.epam.selectionСommitteeSpring.model.DTO.UserForm;

import com.epam.selectionСommitteeSpring.model.exception.EmailIsReservedException;
import com.epam.selectionСommitteeSpring.model.exception.UsernameIsReservedException;
import com.epam.selectionСommitteeSpring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;


    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public String registrationCompleted(){
        return "registrationCompleted";
    }

    @GetMapping("/form")
    public String registration(@ModelAttribute("userForm") UserForm userForm, Model model){

        System.out.println("registration");
        return "registration";
    }
    //                String login,
//                String password,
//                String email,
//                String firstname,
//                String surname,
//                String city,
//                String region
    @PostMapping()
    public String newUser(@ModelAttribute("userForm")
                              @Valid UserForm userForm
            , BindingResult bindingResult,Model model
//
    ) {
        if(bindingResult.hasErrors()){
            return "registration";
        }
        System.out.println("newUser");
        System.out.println(userForm.getUsername());


      try {
          userService.addNewUser(userForm);
          return "redirect:/registration";
      }
      catch (UsernameIsReservedException exception){
      model.addAttribute("LoginIsReserved",true);
      }
      catch (EmailIsReservedException exception){
      model.addAttribute("EmailIsReserved",true);
      }


     return "registration";
    }


}
