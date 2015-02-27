package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ServiciosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mperal01
 */
@Controller
@RequestMapping(value = "/servicios/**")
public class ServiciosController {
    
    @Autowired
    private ServiciosDAO serviciosDAO;
    
    @RequestMapping(value = "/soap", method = RequestMethod.GET )
    public String getServicioSoap(){
        System.out.println("En getServicioSOAP");
        System.out.println(serviciosDAO.findById(1));
        return "soap";
    }
}
