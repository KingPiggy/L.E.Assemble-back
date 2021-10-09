package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.config.auth.LoginUser;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController {

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "index";
    }

    @GetMapping("/store-events")
    public String products(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "store-events";
    }

    @GetMapping("/my-store")
    public String myStore(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "my-store";
    }

    @GetMapping("/my-page")
    public String myPage(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "my-page";
    }

    @GetMapping("/about")
    public String about(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "about";
    }

    @GetMapping("/my-login")
    public String myLogin(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "my-login";
    }
}
