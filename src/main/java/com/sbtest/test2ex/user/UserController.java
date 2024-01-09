package com.sbtest.test2ex.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/signup")
    public String signup() {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signupPost(@RequestParam("username") String username, @RequestParam("password1") String password1, @RequestParam("password2") String password2) {
        if (!password1.equals(password2)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        this.userService.signup(username,password1);
        return "redirect:/article/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }


    @PostMapping("/login")
    public String loginPost(@RequestParam("username") String username) {
        this.userSecurityService.loadUserByUsername(username);
        return "redirect:/article/list";
    }

}
