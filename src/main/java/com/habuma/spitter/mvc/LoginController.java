package com.habuma.spitter.mvc;

import com.habuma.spitter.domain.hibernate.Spittle;
import com.habuma.spitter.persistence.jdbc.SpitterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private SpitterDao spitterDao;

    @RequestMapping(method = GET)
    public String showLoginForm(@RequestParam(value = "login_error", required = false) String error, Model model) {
        System.out.println("showLoginForm()");
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }
            model.addAttribute("spittles", spitterDao.getRecentSpittle());
            model.addAttribute(new Spittle());

//        return "login";
        return "home";
    }
}
