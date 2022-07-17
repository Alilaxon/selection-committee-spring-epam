package com.epam.selectioncommitteespring.Controller.Guest;

import com.epam.selectioncommitteespring.DAO.TempDB;
import com.epam.selectioncommitteespring.Model.DTO.UserForm;

import com.epam.selectioncommitteespring.Model.exception.EmailIsReservedException;
import com.epam.selectioncommitteespring.Model.exception.UsernameIsReservedException;
import com.epam.selectioncommitteespring.Model.service.UserService;
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
    private  TempDB tempDB;

    @Autowired
    public RegistrationController(UserService userService, TempDB tempDB) {
        this.userService = userService;
        this.tempDB = tempDB;
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


      tempDB.save(userForm);
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
    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("users",tempDB.index()) ;
        return "show";
    }

}
