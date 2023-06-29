package com.example.springproject.filmLibrary.controller.mvc;

import com.example.springproject.filmLibrary.dto.UserDTO;
import com.example.springproject.filmLibrary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserControllerMVC {

    private final UserService userService;

    public UserControllerMVC(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        return "/users/list";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO) {
        userDTO.setRoleId(1L);
        userService.create(userDTO);
        return "redirect:/main";
    }
}

