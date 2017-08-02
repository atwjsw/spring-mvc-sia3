package com.habuma.spitter.mvc;

import com.habuma.spitter.domain.Spitter;
import com.habuma.spitter.domain.Spittle;
//import com.habuma.spitter.service.SpitterService;
import com.habuma.spitter.persistence.jdbc.SpitterDao;
import com.habuma.spitter.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import javax.inject.Inject;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Controller
public class HomeController {

  private SpitterService spitterService;

  @Autowired
  private SpitterDao spitterDao;

  @Inject
  public HomeController(SpitterService spitterService) {
    this.spitterService = spitterService;
  }
  
  @RequestMapping(value={"/","/home"}, method=RequestMethod.GET)
  public String showHomePage(Map<String, Object> model) {
//    model.put("spittles", spitterService.getRecentSpittles(spittlesPerPage));
    model.put("spittles", spitterDao.getRecentSpittle());
    model.put("spittle", new Spittle());
//    Spitter spitter = new Spitter();
//    spitter.setUsername("habuma");
//    spitter.setFullName("habuma habuma");
//    spitter.setEmail("habuma@habuma.com");
//    Spittle spittle = new Spittle();
//    spittle.setSpitter(spitter);
//    spittle.setText("habuma is cool...");
//    spittle.setWhen(new Date());
//    model.put("spittles", Arrays.asList(spittle, spittle, spittle));
    return "home";
  }

  public static final int DEFAULT_SPITTLES_PER_PAGE = 25;
  
  private int spittlesPerPage = DEFAULT_SPITTLES_PER_PAGE;
  
  public void setSpittlesPerPage(int spittlesPerPage) {
    this.spittlesPerPage = spittlesPerPage;
  }
  
  public int getSpittlesPerPage() {
    return spittlesPerPage;
  }

}
