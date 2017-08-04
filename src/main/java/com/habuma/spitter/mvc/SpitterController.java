package com.habuma.spitter.mvc;

import com.habuma.spitter.domain.Spitter;
import com.habuma.spitter.domain.Spittle;
import com.habuma.spitter.persistence.jdbc.SpitterDao;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/spitters")
public class SpitterController {

//    @Value("#{s3Properties['webRootPath']}")
//    String webRootPath;

    @Value("${webRootPath}")
    String webRootPath;

//    @Value("#{s3Properties['s3.accessKey']}")
//    private String s3AccessKey;
//    @Value("#{s3Properties['s3.secretKey']}")
//    private String s3SecretKey;

    @Autowired
    private SpitterDao spitterDao;

    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String createSpitterProfile(Model model) {
        System.out.println(model);
        model.addAttribute(new Spittle());
        model.addAttribute(new Spitter());
        return "spitters/edit";
    }

//    @RequestMapping(method = RequestMethod.POST, headers="Accept=application/json")
//    public @ResponseBody Spitter addSpitterFromAPI(@RequestBody Spitter spitter, HttpServletResponse response) {
//        System.out.println("in addSpitterFromApi" + spitter);
//        spitterDao.addSpitter(spitter);
//        response.setHeader("Location", "http://localhost:8080");
//        return spitter;
//    }

    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new ImageUploadException("Only JPG images accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws ImageUploadException {
        try {
            File file = new File(webRootPath + "/resources/" + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new ImageUploadException("Unable to save image", e);
        }
    }

    @RequestMapping(value="/{username}", method=RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        model.addAttribute(spitterDao.getSpitterByUsername(username));
        model.addAttribute(new Spittle());
        return "spitters/view";
    }

    /**
     * restful method for getSpitterByUsername
     * @param username
     * @return
     */
    @RequestMapping(value="/spitter/{username}", method=RequestMethod.GET)
    public @ResponseBody Spitter showSpitterProfilRest(@PathVariable String username) {
        System.out.println("in showSpitterProfilRest");
        return spitterDao.getSpitterByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSpitterFromForm(@Valid Spitter spitter,
                                     BindingResult bindingResult,
                                     @RequestParam(value = "image", required = false) MultipartFile image) {
        System.out.println("in addSpitterFromForm " + spitterDao);
//        System.out.println("in addSpitterFromForm" + spitter);
        System.out.println("in addSpitterFromForm " + bindingResult.toString());
        if (bindingResult.hasErrors()) {
//      System.out.println("has Error");
            return "spitters/edit";
        }

        //    spitterService.saveSpitter(spitter);
        spitterDao.addSpitter(spitter);
        System.out.println(image);
        try {
            if (!image.isEmpty()) {
                validateImage(image);
                saveImage(spitter.getId() + ".jpg", image);
            }
        } catch (ImageUploadException e) {
            System.out.println(e);
            bindingResult.reject("image.typeError", e.getMessage()); //<sf:errors id="image.typeError" cssClass="error" />
            return "spitters/edit";
        }

//        return "spitters/edit";
        return "redirect:/spitters/" + spitter.getUsername();
    }
}
