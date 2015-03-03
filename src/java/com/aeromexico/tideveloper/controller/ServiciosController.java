package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ServiciosDAO;
import com.aeromexico.tideveloper.models.ajax.Response;
import com.aeromexico.tideveloper.models.Servicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping(value = "/soapData", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response getListServicios(){
        System.out.println("En lista servicios response body");
        List<Servicios> listaServicios = serviciosDAO.findAll();
        for(Servicios s:listaServicios){
            System.out.println(s);
        }
        
        Response r = new Response();
        r.setDraw(1);
        r.setRecordsFiltered(listaServicios.size());
        r.setData(listaServicios);
        
        return r;
    } 
    
    @RequestMapping(value = "/versiones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getServiciosVersiones(@PathVariable int id, Model model){
        System.out.println("En lista servicios response body");
        Servicios servicio = serviciosDAO.findById(id);
        model.addAttribute("serv", servicio);
        return "servicioVersiones";
    }
}
