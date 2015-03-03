package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ServiciosDAO;
import com.aeromexico.tideveloper.models.ajax.Response;
import com.aeromexico.tideveloper.models.Servicios;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 * @author mperal01
 */
@Controller
@RequestMapping(value = "/servicios/*")
public class ServiciosController {

    @Autowired
    private ServiciosDAO serviciosDAO;

    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public ServiciosController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping(value = "/soap", method = RequestMethod.GET)
    public String getServicioSoap(Model model) {
        System.out.println("En getServicioSOAP");
        Servicios servicio = serviciosDAO.findById(1);
        System.out.println(servicio);
        model.addAttribute("serv", servicio);
        Map<RequestMappingInfo,HandlerMethod> mapeo = this.handlerMapping.getHandlerMethods();
        Iterator<RequestMappingInfo> itM = mapeo.keySet().iterator();
        List<String> menu=new ArrayList<>();
        while(itM.hasNext()){
            RequestMappingInfo rmi = itM.next();
            String pattern = rmi.getPatternsCondition().getPatterns().toString();
            if(pattern.contains("servicios")){
                menu.add(pattern);
            }
            System.out.println(rmi.getPatternsCondition().getPatterns());
            System.out.println(rmi.getProducesCondition().getProducibleMediaTypes());
        }
        model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());
        model.addAttribute("menu", menu);
        return "soap";
    }

    @RequestMapping(value = "/soapData", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response getListServicios() {
        System.out.println("En lista servicios response body");
        List<Servicios> listaServicios = serviciosDAO.findAll();
        for (Servicios s : listaServicios) {
            System.out.println(s);
        }

        Response r = new Response();
        r.setDraw(1);
        r.setRecordsFiltered(listaServicios.size());
        r.setData(listaServicios);

        return r;
    }

    @RequestMapping(value = "/versiones/{id}")
    public String getServiciosVersiones(@PathVariable int id, Model model) {
        System.out.println("En lista servicios response body");
        Servicios servicio = serviciosDAO.findById(id);
        model.addAttribute("serv", servicio);
        return "servicioVersiones";
    }
}
