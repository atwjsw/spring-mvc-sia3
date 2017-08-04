package com.habuma.spitter.mvc;

import com.habuma.spitter.domain.Spittle;
import com.habuma.spitter.persistence.jdbc.SpitterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
  @Autowired
  private SpitterDao spitterDao;

  @RequestMapping("/admin")
  public void showAdminPage(Model model) {
    System.out.println("showAdminPage()");
    model.addAttribute("spittles", spitterDao.getRecentSpittle());
    model.addAttribute("spittle", new Spittle());
  }
}
