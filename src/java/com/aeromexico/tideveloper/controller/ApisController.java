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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Elida Carrillo
 */
@Controller
public class ApisController {
    @Autowired
    ApiDAO apiDao;
    
    @RequestMapping(value = "/apis", method =RequestMethod.GET)
    public String getApi(Model model){
        System.out.println("En getApis");
        Api api =apiDao.findById(1);
        System.out.println(api.toString());
        model.addAttribute("api", api);
        return "apis";
    }
    
    //public String getApi()
            
}
