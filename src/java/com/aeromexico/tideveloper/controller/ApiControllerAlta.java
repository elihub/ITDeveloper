/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ApiDAO;
import com.aeromexico.tideveloper.models.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
   public String getAltaApi(Model model){
       Api api=new Api();
        model.addAttribute("api",api);
       return "new";
   }
   @RequestMapping(value = "new", method = RequestMethod.POST)
   public String postAltaApi(@ModelAttribute("api") Api api, Model model){
       System.out.println(api.toString());
       System.out.println("Estoy en alta de apis");
       return "apis";
   }
    
}
