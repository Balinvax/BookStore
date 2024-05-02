package com.krutn.bookstore.controller;

import com.krutn.bookstore.service.CustomUserDetails;
import com.krutn.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCabinetController {

    @Autowired
    UserService userService;

    @GetMapping("/user/cabinet")
    public String userCabinet(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("user", userService.findById(userDetails.getId()).orElseThrow());
        } else {
            throw new IllegalStateException("Authentication principal is not of type CustomUserDetails");
        }
        return "userCabinet";
    }

}
