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
        this.userService.signup(userForm.getUsername(),userForm.getPassword1(), userForm.getEmail());
        return "redirect:/article/list";
    }

}
