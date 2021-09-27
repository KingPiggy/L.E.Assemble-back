package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.config.auth.LoginUser;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "index";
    }

    @GetMapping("/about")
    public String about(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "about";
    }

    @GetMapping("/mylogin")
    public String myLogin(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "mylogin";
    }
}
