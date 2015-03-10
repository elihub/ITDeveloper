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
import com.aeromexico.tideveloper.models.ApisVersionesResources;
import com.aeromexico.tideveloper.models.Usuarios;
import com.aeromexico.tideveloper.security.CustomUser;
import com.aeromexico.tideveloper.util.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Elida Carrillo
 */
@Controller
@RequestMapping("/apis/*")
public class ApiControllerAlta {

    @Autowired
    ApiDAO apiDao;

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getAltaApi(Model model) {
        Api api = new Api();
        ApisVersiones apiVersion = new ApisVersiones();
        ApisVersionesResources apisVersionesResources = new ApisVersionesResources();
        ApisDocs apiDocs = new ApisDocs();
        model.addAttribute("api", api);
        model.addAttribute("apiVersion", apiVersion);
        model.addAttribute("apisVersionesResources", apisVersionesResources);
        model.addAttribute("apiDocs", apiDocs);
        return "new";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String postAltaApi(@ModelAttribute("api") Api api,
            @ModelAttribute("apiVersion") ApisVersiones apisVersiones,
            @ModelAttribute("apisVersionesResources") ApisVersionesResources apisVersionesResources,
            @ModelAttribute("apiDocs") ApisDocs apiDocs,
            HttpServletRequest request,
            Model model) {

        //get the user
        Util.getIdUserLogged(request);
        api.setIdUsuario((Integer) request.getAttribute("idUsuario"));

        ResourceBundle properties = ResourceBundle.getBundle("TIDeveloper");
        String rootPath = properties.getString("RootPath");
        String nameFolder = api.getNombre();

        //get the files of resources      
        String message = "";
        System.out.println("tamaño del resources"+apisVersionesResources.getFiles().length);
        if (apisVersionesResources.getFiles().length > 0) {
            for (int i = 0; i < apisVersionesResources.getFiles().length; i++) {
                MultipartFile file = apisVersionesResources.getFiles()[i];
                String name = apisVersionesResources.getNombreResources()[i];
                try {
                    byte[] bytes = file.getBytes();

                    // Creating the directory to store file                     
                    File dir = new File(rootPath + nameFolder + "\\resources");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    // Create the file on server
                    String pathFile = dir.getAbsolutePath() + File.separator + name;
                    File serverFile = new File(pathFile);
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    System.out.println("Server File Location="
                            + serverFile.getAbsolutePath());

                    message = message + "You successfully uploaded file=" + name + "<br />";
                    ApisVersionesResources apiVR = new ApisVersionesResources(name, pathFile);
                    api.getVersiones().get(0).setResource(apiVR);
                    System.out.println(message);
                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            }
        }

        //get the files of docs
        if (apiDocs.getFilesDocs().length > 1) {
            System.out.println("files doc size" + apiDocs.getFilesDocs().length);
            System.out.println("files doc name size" + apiDocs.getNombreDocs().length);

            for (int i = 0; i < apiDocs.getFilesDocs().length; i++) {
                MultipartFile file = apiDocs.getFilesDocs()[i];
                String name = apiDocs.getNombreDocs()[i];
                String resumen=apiDocs.getResumenDocs()[i];

                try {
                    byte[] bytes = file.getBytes();

                    // Creating the directory to store file                    
                    File dir = new File(rootPath + nameFolder + "\\docs");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    // Create the file on server
                    String pathFile = dir.getAbsolutePath() + File.separator + name;
                    File serverFile = new File(pathFile);
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    System.out.println("Server File Location="
                            + serverFile.getAbsolutePath());

                    message = message + "You successfully uploaded file=" + name + "<br />";
                    apiDocs=new ApisDocs(name,resumen,pathFile);
                    List<ApisDocs> listApiDocs=new ArrayList<>();
                    listApiDocs.add(apiDocs);
                    api.setDocs(listApiDocs);
                    System.out.println(message);
                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            }
        }

        System.out.println("Api" + api.toString());
       // System.out.println("Api versones" + apisVersiones.toString());
        //System.out.println("Api versones resources" + apisVersionesResources.toString());
        //System.out.println("Api Docs" + apiDocs.toString());
        System.out.println("Estoy en alta de apis");

        return "apis";
    }

}
