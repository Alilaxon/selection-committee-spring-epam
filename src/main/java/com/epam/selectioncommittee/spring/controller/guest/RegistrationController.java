package com.epam.selectioncommittee.spring.controller.guest;
import com.epam.selectioncommittee.spring.model.dto.UserForm;
import com.epam.selectioncommittee.spring.model.exception.EmailIsReservedException;
import com.epam.selectioncommittee.spring.model.exception.UsernameIsReservedException;
import com.epam.selectioncommittee.spring.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger log = LogManager.getLogger(RegistrationController.class);
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public String registrationCompleted(){

        return "registrationCompleted";
    }

    @GetMapping("/form")
    public String registration(@ModelAttribute("userForm") UserForm userForm){

        return "registration";
    }

    @PostMapping()
    public String newUser(@ModelAttribute("userForm") @Valid UserForm userForm,
                          BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){

            log.info(" registration: validation error");

            return "registration";
        }

      try {
          userService.createUser(userForm);
          return "redirect:/registration";
      }
      catch (UsernameIsReservedException exception){

          log.info(" registration: LoginIsReserved");

      model.addAttribute("LoginIsReserved",true);
      }
      catch (EmailIsReservedException exception){

          log.info(" registration: EmailIsReservedException");

      model.addAttribute("EmailIsReserved",true);
      }


     return "registration";
    }


}
