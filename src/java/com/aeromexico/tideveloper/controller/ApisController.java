/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ApiDAO;
import com.aeromexico.tideveloper.models.Api;
import com.aeromexico.tideveloper.models.ajax.Response;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Elida Carrillo
 */
@Controller
@RequestMapping("/apis/*")
@SessionAttributes("api")
public class ApisController {
    @Autowired
    ApiDAO apiDao;
    
    @RequestMapping(value = "view", method =RequestMethod.GET)
    public String getApi(Model model){
        System.out.println("En getApis");
        return "apis";
    }
    
    @RequestMapping(value="/apisJson",method = RequestMethod.GET)
    public @ResponseBody Response getAllApis(){
        List<Api> ListApi =apiDao.findAll();
       // Api api =apiDao.findById(1);
        Response responce=new Response();
        responce.setDraw(1);
        responce.setRecordsTotal(1);
        responce.setRecordsFiltered(1);
        responce.setData(ListApi);
        System.out.println("Recupera los datos del api: ");
        System.out.println(responce);
        return responce;
    }
    @RequestMapping(value="view/{idApi}", method = RequestMethod.GET )  
    public String getApiById(@PathVariable("idApi") int idApi, Model model){
        Api api=apiDao.findById(idApi);
        model.addAttribute("api",api);
        return "apisVersiones";
    }
    
    @RequestMapping(value="view/{idApi}", method = RequestMethod.POST )  
    public String postApiById(@PathVariable("idApi") int idApi, Model model, @ModelAttribute("api") Api apiMod){
        apiDao.update(apiMod);
        model.addAttribute("api",apiMod);
        return "apisVersiones";
    }
    
    @RequestMapping(value="view/downloads", method = RequestMethod.GET )  
    public RedirectView getDeleteResource(HttpServletRequest request, @RequestParam(value = "version") int posVersion
            , @RequestParam(value = "resource") int posResource){
        System.out.println("Estamos en redireccion");
        RedirectView rv = new RedirectView(request.getContextPath()+"/apis/view/1");
	rv.setExposeModelAttributes(false);
	return rv;
    }
}
