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

    @GetMapping("/products")
    public String products(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "products";
    }

    @GetMapping("/store")
    public String store(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "store";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "mypage";
    }

    @GetMapping("/about")
    public String about(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "about";
    }

    @GetMapping("/mylogin")
    public String myLogin(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        return "mylogin";
    }
}
