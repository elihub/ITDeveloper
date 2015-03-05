package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ServiciosDAO;
import com.aeromexico.tideveloper.models.ajax.Response;
import com.aeromexico.tideveloper.models.Servicios;
import com.aeromexico.tideveloper.models.ajax.MenuItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    private List<Servicios> listaServicios= new ArrayList<>();
    private final RequestMappingHandlerMapping handlerMapping;
    
    @ModelAttribute("listaServicios")
    public List<Servicios> getPerson(){
        listaServicios = serviciosDAO.findAll();
        return listaServicios;
    }

    @Autowired
    public ServiciosController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping(value = "/soap", method = RequestMethod.GET)
    public String getServicioSoap(HttpServletRequest request,Model model) {
        System.out.println("En getServicioSOAP");
        Servicios servicio = serviciosDAO.findById(1);
        System.out.println(servicio);
        model.addAttribute("serv", servicio);
        
        Map<RequestMappingInfo,HandlerMethod> mapeo = this.handlerMapping.getHandlerMethods();
        Iterator<RequestMappingInfo> itM = mapeo.keySet().iterator();
        MenuItem item = new MenuItem();
        while(itM.hasNext()){
            RequestMappingInfo rmi = itM.next();
            String pattern = rmi.getPatternsCondition().getPatterns().toString().replace("[", "").replace("]", "");
            String[] niveles=pattern.split("/");
            for(String nivel:niveles){
                if(!nivel.trim().equals("")){
                    item.setNombre(nivel);
                    item.setActivo("in");
                }
            }
            System.out.println(rmi.getPatternsCondition().getPatterns());
            System.out.println(rmi.getProducesCondition().getProducibleMediaTypes());
        }
        model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());
        
        String path = request.getRequestURI().replace(request.getContextPath()+"/", "");
        String niveles[] = path.split("/");
        List<MenuItem> listItem=new ArrayList<>();
        for(String nivel:niveles){
            MenuItem mi= new MenuItem();
            mi.setNombre(nivel);
            mi.setActivo("in");
            listItem.add(mi);
        }
        
        model.addAttribute("menu", listItem);
        System.out.println(request.getContextPath());
        System.out.println(request.getPathInfo());
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        model.addAttribute("path", request.getPathInfo());
        return "soap";
    }

    @RequestMapping(value = "/soapData", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response getListServicios() {
        System.out.println("En lista servicios response body");
         
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
