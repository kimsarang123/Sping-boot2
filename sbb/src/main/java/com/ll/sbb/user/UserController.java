package com.ll.sbb.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login_form";
    }

    @GetMapping("/signup")
    public String signup(UserForm userForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "signup_form";
        }
        if (!userForm.getUsername1().equals(userForm.getPassword1().equals(userForm.getPassword2().equals(userForm.getEmail1())))){
            this.userService.create(userForm.getUsername1(),userForm.getEmail1(), userForm.getPassword1());
        }
        return "redirect:/article/list";
    }
}
