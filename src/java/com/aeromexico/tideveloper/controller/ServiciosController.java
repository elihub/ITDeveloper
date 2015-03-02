package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ServiciosDAO;
import com.aeromexico.tideveloper.models.Servicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String getServicioSoap(Model model){
        System.out.println("En getServicioSOAP");
        Servicios servicio = serviciosDAO.findById(1);
        System.out.println(servicio);
        model.addAttribute("serv", servicio);
        return "soap";
    }
    
    @RequestMapping(value = "/soapData")
    public @ResponseBody List<Servicios> getListServicios(){
        return serviciosDAO.findAll();
    } 
}
