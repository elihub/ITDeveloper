/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.controller;

import com.aeromexico.tideveloper.dao.ApiDAO;
import com.aeromexico.tideveloper.models.Api;
import com.aeromexico.tideveloper.models.ApisDocs;
import com.aeromexico.tideveloper.models.ApisVersiones;
import com.aeromexico.tideveloper.models.ajax.Response;
import com.aeromexico.tideveloper.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.ModelAndView;
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
    public String postApiById(@PathVariable("idApi") int idApi, Model model
            , @ModelAttribute("api") Api apiMod, HttpServletRequest request){
        
        String resource = request.getParameter("resource");
        String docs = request.getParameter("documentos");
        
        if(resource != null){
            for(ApisVersiones apiVersion: apiMod.getVersiones()){
                Util.editResources(apiVersion.getResources(), apiMod.getId());
            }
        }
        if(docs != null){
            for(ApisDocs apiDoc: apiMod.getDocs()){
                Util.editDocs(apiDoc, apiMod.getId());
            }
        }
        
        apiDao.update(apiMod);
        model.addAttribute("api",apiMod);
        return "apisVersiones";
    }
    
    @RequestMapping(value="view/delVersion", method = RequestMethod.GET )  
    public RedirectView getDeleteResource(HttpServletRequest request, @RequestParam(value = "version") int posVersion
            , @ModelAttribute("api") Api apiMod){
        System.out.println("Estamos en redireccion");
        
        apiMod.getVersiones().remove(posVersion);
        apiDao.update(apiMod);
        
        RedirectView rv = new RedirectView(request.getContextPath()+"/apis/view/" + apiMod.getId());
	rv.setExposeModelAttributes(false);
	return rv;
    }
    
    @RequestMapping(value="view/downloads", method = RequestMethod.GET )  
    public RedirectView getDeleteResource(HttpServletRequest request, @RequestParam(value = "version") int posVersion
            , @RequestParam(value = "resource") int posResource, @ModelAttribute("api") Api apiMod){
        System.out.println("Estamos en redireccion");
        
        apiMod.getVersiones().get(posVersion).getResources().remove(posResource);
        apiDao.update(apiMod);
        
        RedirectView rv = new RedirectView(request.getContextPath()+"/apis/view/" + apiMod.getId());
	rv.setExposeModelAttributes(false);
	return rv;
    }
    
    @RequestMapping(value="view/docs", method = RequestMethod.GET )  
    public RedirectView getDeleteDocs(HttpServletRequest request, @RequestParam(value = "doc") int posDoc
            , @ModelAttribute("api") Api apiMod){
        System.out.println("Estamos en redireccion");
        
        apiMod.getDocs().remove(posDoc);
        apiDao.update(apiMod);
        
        RedirectView rv = new RedirectView(request.getContextPath()+"/apis/view/" + apiMod.getId());
	rv.setExposeModelAttributes(false);
	return rv;
    }
    
    @RequestMapping(value="view/downloadFile", method = RequestMethod.GET )  
    public ModelAndView getDownloadResource(HttpServletRequest request
            , HttpServletResponse response
            , @RequestParam(value = "version") int posVersion
            , @RequestParam(value = "resource") int posResource, @ModelAttribute("api") Api apiMod){
        System.out.println("Estamos en redireccion");
        ServletContext context = request.getServletContext();
        
        String rutaFile = apiMod.getVersiones().get(posVersion).getResources().get(posResource).getDirResource();
        String nombreArchivo = rutaFile.substring(rutaFile.lastIndexOf("\\"));
        File downloadFile = new File(rutaFile);
        try{
            FileInputStream inputStream = new FileInputStream(downloadFile);
        
            // get MIME type of the file
            String mimeType = context.getMimeType(rutaFile);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + nombreArchivo + "\"");
             
            //IOUtils.copy(is, response.getOutputStream());
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
	
	return null;
    }
}
