package com.epam.selectioncommittee.spring.controller;

import com.epam.selectioncommittee.spring.controller.util.url.UserUrl;
import com.epam.selectioncommittee.spring.model.dto.UserForm;
import com.epam.selectioncommittee.spring.model.entity.Statement;
import com.epam.selectioncommittee.spring.model.exception.EmailIsReservedException;
import com.epam.selectioncommittee.spring.model.exception.UsernameIsReservedException;
import com.epam.selectioncommittee.spring.model.service.StatementService;
import com.epam.selectioncommittee.spring.model.entity.User;
import com.epam.selectioncommittee.spring.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    private final UserService userService;

    private final StatementService statementService;

    @Autowired
    public UserController(UserService userService,
                          StatementService statementService) {

        this.userService = userService;
        this.statementService = statementService;
    }

    @GetMapping
    public String main() {

        return "main";
    }

  //  @GetMapping("/registered")
    @GetMapping(UserUrl.REGISTERED)
    public String registrationCompleted() {

        return "registrationCompleted";
    }

    //   @GetMapping("/user")
    @GetMapping(UserUrl.USER)
    public String userPage(@RequestParam("id") Long id, Model model) {

        User user = userService.findUserById(id);

        List<Statement> statements = statementService.findAllStatementsByUserId(user);

        model.addAttribute("statements", statements)
                .addAttribute("userProfile", user);

        return "user/userInfo";
    }

    //   @GetMapping("/registration/form")
    @GetMapping(UserUrl.REGISTRATION)
    public String getCreateUser(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "registration";
    }

    //   @PostMapping("/registration/form")
    @PostMapping(UserUrl.REGISTRATION)
    public String postCreateUser(@ModelAttribute("userForm") @Valid UserForm userForm,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            log.info(" registration: validation error");

            return "registration";
        }

        try {
            userService.createUser(userForm);

            return "redirect:/registered";

        } catch (UsernameIsReservedException exception) {

            log.info(" registration: LoginIsReserved");

            model.addAttribute("LoginIsReserved", true);

        } catch (EmailIsReservedException exception) {

            log.info(" registration: EmailIsReservedException");

            model.addAttribute("EmailIsReserved", true);
        }

        return "registration";
    }


}
