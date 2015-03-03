/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ApiDAO;
import com.aeromexico.tideveloper.models.Api;
import com.aeromexico.tideveloper.models.Responce;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "apis";
    }
    
    @RequestMapping(value="/apisJson",method = RequestMethod.GET)
    public @ResponseBody Responce getAllApis(){
        List<Api> ListApi =apiDao.findAll();
       // Api api =apiDao.findById(1);
        Responce responce=new Responce();
        responce.setDraw(1);
        responce.setRecordsTotal(1);
        responce.setRecordsFiltered(1);
        responce.setData(ListApi);
        System.out.println("Recupera los datos del api: ");
        return responce;
    }
            
}
