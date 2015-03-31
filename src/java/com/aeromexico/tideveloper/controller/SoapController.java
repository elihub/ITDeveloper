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
import com.aeromexico.tideveloper.util.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import org.springframework.web.multipart.MultipartFile;
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

    private List<Soap> listaSoap = new ArrayList<>();
    private final RequestMappingHandlerMapping handlerMapping;
    private List<Area> listArea = new ArrayList<>();
    private List<Funcion> listFunciones = new ArrayList<>();

    @ModelAttribute("listaServicios")
    public List<Soap> getPerson() {
        listaSoap = soapDAO.findAll();
        return listaSoap;
    }

    @ModelAttribute("catArea")
    public List<Area> getAreas() {
        listArea = areaDAO.findAll();
        return listArea;
    }

    @ModelAttribute("catFuncion")
    public List<Funcion> getFunciones() {
        listFunciones = funcionDAO.findAll();
        return listFunciones;
    }

    @Autowired
    public SoapController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping(value = "/soap", method = RequestMethod.GET)
    public String getSoap(HttpServletRequest request, Model model) {
        System.out.println("En getServicioSOAP");
        Soap servicio = soapDAO.findById(1);
        System.out.println(servicio);
        model.addAttribute("serv", servicio);

        Map<RequestMappingInfo, HandlerMethod> mapeo = this.handlerMapping.getHandlerMethods();
        Iterator<RequestMappingInfo> itM = mapeo.keySet().iterator();
        MenuItem item = new MenuItem();
        while (itM.hasNext()) {
            RequestMappingInfo rmi = itM.next();
            String pattern = rmi.getPatternsCondition().getPatterns().toString().replace("[", "").replace("]", "");
            String[] niveles = pattern.split("/");
            for (String nivel : niveles) {
                if (!nivel.trim().equals("")) {
                    item.setNombre(nivel);
                    item.setActivo("in");
                }
            }
            System.out.println(rmi.getPatternsCondition().getPatterns());
            System.out.println(rmi.getProducesCondition().getProducibleMediaTypes());
        }
        model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());

        String path = request.getRequestURI().replace(request.getContextPath() + "/", "");
        String niveles[] = path.split("/");
        List<MenuItem> listItem = new ArrayList<>();
        for (String nivel : niveles) {
            MenuItem mi = new MenuItem();
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
    public String getAltaSOAP(Model model) {
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

    @RequestMapping(value = "newSoap", method = RequestMethod.POST)
    public String postAltaSOAP(@ModelAttribute("soap") Soap soap,
            @ModelAttribute("soapVersion") SoapVersiones soapVersiones,
            @ModelAttribute("soapVersionesResources") SoapVersionesResources soapVersionesResources,
            @ModelAttribute("soapDocs") SoapDocs soapDocs,
            HttpServletRequest request,
            Model model) {

        for (Area area : listArea) {
            if (area.getId() == Integer.valueOf(request.getParameter("soapArea"))) {
                soap.setArea(area);
            }
        }
        for (Funcion funcion : listFunciones) {
            if (funcion.getId() == Integer.valueOf(request.getParameter("soapFuncion"))) {
                soap.setFuncion(funcion);
            }
        }

        List<SoapVersiones> listSoapVersiones = new ArrayList<>();
        listSoapVersiones.add(soapVersiones);
        soap.setVersiones(listSoapVersiones);
        List<SoapVersionesResources> listSoapVersionesResources = new ArrayList<>();
        List<SoapDocs> listApisDocs = new ArrayList<>();

        //get the user
        Util.getIdUserLogged(request);
        soap.setUsuarioCreacion((Integer) request.getAttribute("idUsuario"));
        soap.setFechaCreacion(new Date());
        soap.setFechaModificacion(new Date());
        ResourceBundle properties = ResourceBundle.getBundle("TIDeveloper");
        String rootPath = properties.getString("SoapPath");
        String nameFolder = soap.getNombre();

        //get the files of resources      
        String message = "";
        System.out.println("tamaño del resources" + soapVersionesResources.getFiles().length);
        if (soapVersionesResources.getFiles().length >= 1) {
            for (int i = 0; i < soapVersionesResources.getFiles().length; i++) {
                String URL = soapVersionesResources.getFiles()[i];
                if (!URL.equals("")) {
                    String name = "";
                    try {
                        name = soapVersionesResources.getNombreResources()[i];
                        SoapVersionesResources soapVR = new SoapVersionesResources(name, URL);
                        listSoapVersionesResources.add(soapVR);
                        System.out.println(message);
                    } catch (Exception e) {

                    }
                }
            }
        }
        soap.getVersiones().get(0).setResources(listSoapVersionesResources);
        //get the files of docs
        if (soapDocs.getFilesDocs().length >= 1) {
            for (int i = 0; i < soapDocs.getFilesDocs().length; i++) {
                MultipartFile file = soapDocs.getFilesDocs()[i];
                if (!file.isEmpty()) {
                    String name = "";
                    try {
                        name = soapDocs.getNombreDocs()[i];
                        String nameFile = file.getOriginalFilename();
                        String resumen = null;
                        try {
                            resumen = soapDocs.getResumenDocs()[i];
                        } catch (Exception e) {
                        }
                        byte[] bytes = file.getBytes();

                        // Creating the directory to store file                    
                        File dir = new File(rootPath + nameFolder + "\\docs");
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        // Create the file on server
                        String pathFile = dir.getAbsolutePath() + File.separator + nameFile;
                        File serverFile = new File(pathFile);
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                        stream.write(bytes);
                        stream.close();

                        System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                        message = message + "You successfully uploaded docs=" + nameFile + "<br />";
                        resumen=resumen.replace("<", "&lt;"); 
                        resumen=resumen.replace(">", "&gt;"); 
                        SoapDocs apiDoc = new SoapDocs(name, resumen, "docs\\" + nameFile);
                        listApisDocs.add(apiDoc);

                        System.out.println(message);
                    } catch (Exception e) {
                        return "You failed to upload " + name + " => " + e.getMessage();
                    }
                }
            }
        }
        soap.setDocs(listApisDocs);
        System.out.println("SOAP: " + soap.toString());
        System.out.println("Estoy en alta de Soap");

        try {
            int id = soapDAO.save(soap);
            if (id != 0) {
                System.out.println("success insert");
                //Actualizamos la ruta del api de versiones resources
                File dir = new File(rootPath + nameFolder);
                File dir2 = new File(rootPath + id);
                if (dir.isDirectory()) {
                    dir.renameTo(dir2);
                    System.out.println("cambia el nombre de la carpeta " + dir2);
                }
                if (soap.getDocs() != null) {
                    for (SoapDocs soapDoc : soap.getDocs()) {
                        soapDoc.setDirDoc(dir2 + File.separator + soapDoc.getDirDoc());
                    }
                }
            }
            System.out.println("actualiza las rutas del api");
            soapDAO.update(soap);

        } catch (Exception e) {
            return "fail upload contact the administrator of the page";
        }
        return "soap";
    }
}
