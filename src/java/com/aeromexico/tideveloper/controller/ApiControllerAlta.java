/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ApiDAO;
import com.aeromexico.tideveloper.models.Api;
import com.aeromexico.tideveloper.models.ApisVersiones;
import com.aeromexico.tideveloper.models.ApisVersionesResources;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Elida Carrillo
 */
@Controller
@RequestMapping("/apis/*")
public class ApiControllerAlta {

    @Autowired
    ApiDAO apiDao;

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getAltaApi(Model model) {
        Api api = new Api();
        ApisVersiones apiVersion = new ApisVersiones();
        List<ApisVersionesResources> apisVersionesResources = new ArrayList<ApisVersionesResources>();
        model.addAttribute("api", api);
        model.addAttribute("apiVersion", apiVersion);
        model.addAttribute("apisVersionesResources", apisVersionesResources);
        return "new";
    }

    @RequestMapping(value = "api", method = RequestMethod.POST)
    public String postAltaApi(@ModelAttribute("api") Api api,
            @ModelAttribute("apiVersion") ApisVersiones apisVersiones,
            @ModelAttribute("apisVersionesResources") ApisVersionesResources apisVersionesResources,
            @RequestParam("nameFiles") String[] names,
            @RequestParam("files") List<MultipartFile> files,
            Model model
    ) {        if (!files.isEmpty()) {
            try {
                int i=0;
                for (MultipartFile file : files) {             
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream
                            = new BufferedOutputStream(new FileOutputStream(new File(names[i])));
                    stream.write(bytes);
                    stream.close();
                    i++;
                    System.out.println("You successfully uploaded " + names[i] + "!");                     
                }

            } catch (Exception e) {
                return "You failed to upload  => " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
        System.out.println("Api" + api.toString());
        System.out.println("Api versones" + apisVersiones.toString());
        System.out.println("Api versones resources" + apisVersionesResources.toString());
        System.out.println("Estoy en alta de apis");

        return "apis";
    }

}
