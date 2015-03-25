package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.AreaDAO;
import com.aeromexico.tideveloper.dao.FuncionDAO;
import com.aeromexico.tideveloper.dao.SoapDAO;
import com.aeromexico.tideveloper.models.Area;
import com.aeromexico.tideveloper.models.Funcion;
import com.aeromexico.tideveloper.models.ajax.Response;
import com.aeromexico.tideveloper.models.Soap;
import com.aeromexico.tideveloper.models.SoapDocs;
import com.aeromexico.tideveloper.models.SoapVersiones;
import com.aeromexico.tideveloper.models.SoapVersionesResources;
import com.aeromexico.tideveloper.models.ajax.MenuItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 * @author mperal01
 */
@Controller
@RequestMapping(value = "/servicios/*")
@SessionAttributes("soap")
public class SoapController {

    @Autowired
    private SoapDAO soapDAO;
    @Autowired
    private AreaDAO areaDAO;
    @Autowired
    private FuncionDAO funcionDAO;
    
    private List<Soap> listaSoap= new ArrayList<>();
    private final RequestMappingHandlerMapping handlerMapping;
    private List<Area> listArea=new ArrayList<>();
    private List<Funcion>listFunciones=new ArrayList<>();
    
    
    @ModelAttribute("listaServicios")
    public List<Soap> getPerson(){
        listaSoap = soapDAO.findAll();
        return listaSoap;
    }
    @ModelAttribute("catArea")
    public void getAreas(){
        this.listArea=areaDAO.findAll();
    }
    
    @ModelAttribute("catFuncion")
    public void getFunciones(){
        listFunciones=funcionDAO.findAll();
    }

    @Autowired
    public SoapController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping(value = "/soap", method = RequestMethod.GET)
    public String getSoap(HttpServletRequest request,Model model) {
        System.out.println("En getServicioSOAP");
        Soap servicio = soapDAO.findById(1);
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
    Response getListSoap() {
        System.out.println("En lista servicios response body");
         
        for (Soap s : listaSoap) {
            System.out.println(s);
        }

        Response r = new Response();
        r.setDraw(1);
        r.setRecordsFiltered(listaSoap.size());
        r.setData(listaSoap);

        return r;
    }

    @RequestMapping(value = "/soap/{id}")
    public String getSoapVersiones(@PathVariable int id, Model model) {
        System.out.println("En lista servicios response body");
        Soap servicio = soapDAO.findById(id);
        model.addAttribute("soap", servicio);
        return "soapVersiones";
    }
    
    @RequestMapping(value = "/soap/{id}", method = RequestMethod.POST)
    public String postSoapVersiones(@PathVariable int id, Model model, @ModelAttribute("soap") Soap soapMod, HttpServletRequest request) {
        String resource = request.getParameter("resource");
        String docs = request.getParameter("documentos");

        /*VALIDAR SI SE VA A NECESITAR
        if (resource != null) {
            for (ApisVersiones apiVersion : apiMod.getVersiones()) {
                Util.editResources(apiVersion.getResources(), apiMod.getId());
            }
        }*/
        
        /*if (docs != null) {
            for (SoapDocs soapDoc : soapMod.getDocs()) {
                Util.editDocs(soapDoc, soapMod.getId());
            }
        }*/

        soapDAO.update(soapMod);
        model.addAttribute("soap", soapMod);
        return "soapVersiones";
    }
    
    @RequestMapping(value = "newSoap", method = RequestMethod.GET)
    public String getAltaApi(Model model) {
        Soap soap = new Soap();
        SoapVersiones soapVersion = new SoapVersiones();
        SoapVersionesResources soapVersionesResources = new SoapVersionesResources();
        SoapDocs soapDocs = new SoapDocs();
        model.addAttribute("soap", soap);
        model.addAttribute("soapVersion", soapVersion);
        model.addAttribute("soapVersionesResources", soapVersionesResources);
        model.addAttribute("soapDocs", soapDocs);
        return "newSoap";
    }
    
    
}
