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
import com.aeromexico.tideveloper.models.ajax.JsonResponse;
import com.aeromexico.tideveloper.util.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private List<Api> listaApis = new ArrayList<>();

    @ModelAttribute("listaServicios")
    public List<Api> getApis() {
        listaApis = apiDao.findAll();
        return listaApis;
    }

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

        List<ApisVersiones> listApiVersiones = new ArrayList<>();
        listApiVersiones.add(apisVersiones);
        api.setVersiones(listApiVersiones);
        List<ApisVersionesResources> listApisVersionesResources = new ArrayList<>();
        List<ApisDocs> listApisDocs = new ArrayList<>();

        //get the user
        Util.getIdUserLogged(request);
        api.setIdUsuario((Integer) request.getAttribute("idUsuario"));
        api.setFechaCreacion(new Date());
        api.setFechaModificacion(new Date());

        ResourceBundle properties = ResourceBundle.getBundle("TIDeveloper");
        String rootPath = properties.getString("RootPath");
        String nameFolder = api.getNombre();
        //String rootPath + nameFolder + "\\resources;

        //get the files of resources      
        String message = "";
        System.out.println("tamaño del resources" + apisVersionesResources.getFiles().length);
        if (apisVersionesResources.getFiles().length >= 1) {
            for (int i = 0; i < apisVersionesResources.getFiles().length; i++) {
                MultipartFile file = apisVersionesResources.getFiles()[i];
                if (!file.isEmpty()) {
                    String name = "";
                    try {
                        System.out.println("name file:" + file.getOriginalFilename());
                        //String extencionFile = "." + FilenameUtils.getExtension(file.getOriginalFilename());
                        //String name = apisVersionesResources.getNombreResources()[i] + extencionFile;                    
                        name = apisVersionesResources.getNombreResources()[i];
                        String nameFile = file.getOriginalFilename();
                        byte[] bytes = file.getBytes();

                        // Creating the directory to store file                     
                        File dir = new File(rootPath + nameFolder + "\\resources");
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        // Create the file on server
                        String pathFile = dir.getAbsolutePath() + File.separator + nameFile;
                        File serverFile = new File(pathFile);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        stream.close();

                        System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                        message = message + "You successfully uploaded file=" + name + "<br />";
                        ApisVersionesResources apiVR = new ApisVersionesResources(name,"resources\\"+ nameFile);
                        listApisVersionesResources.add(apiVR);

                        System.out.println(message);
                    } catch (Exception e) {
                        return "You failed to upload " + name + " => " + e.getMessage();
                    }
                }
            }
            api.getVersiones().get(0).setResources(listApisVersionesResources);
        }

        //api.getVersiones().get(0).setResources(listApisVersionesResources);
        //get the files of docs
        if (apiDocs.getFilesDocs().length >= 1) {
            for (int i = 0; i < apiDocs.getFilesDocs().length; i++) {
                MultipartFile file = apiDocs.getFilesDocs()[i];
                if (!file.isEmpty()) {
                    String name ="";
                    try {
                        name = apiDocs.getNombreDocs()[i];
                        String nameFile = file.getOriginalFilename();
                        String resumen = null;
                        try {
                            resumen = apiDocs.getResumenDocs()[i];
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

                        ApisDocs apiDoc = new ApisDocs(name, resumen, "docs\\"+nameFile);
                        listApisDocs.add(apiDoc);

                        System.out.println(message);
                    } catch (Exception e) {
                        return "You failed to upload " + name + " => " + e.getMessage();
                    }
                }
            }
        }
        api.setDocs(listApisDocs);
        System.out.println("Api" + api.toString());
        System.out.println("Estoy en alta de apis");

        try {
            int id = apiDao.save(api);
            if (id != 0) {
                System.out.println("success insert");
                //Actualizamos la ruta del api de versiones resources
                File dir = new File(rootPath + nameFolder);
                File dir2 = new File(rootPath + id );
                if (dir.isDirectory()) {
                    dir.renameTo(dir2);
                    System.out.println("cambia el nombre de la carpeta " + dir2);
                }
                if (api.getVersiones().get(0).getResources() != null) {
                    for (ApisVersionesResources apiVR : api.getVersiones().get(0).getResources()) {
                        apiVR.setDirResource(dir2 + File.separator + apiVR.getDirResource());
                    }
                }
                dir = new File(rootPath + nameFolder );
                dir2 = new File(rootPath + id );
                if (dir.isDirectory()) {
                    System.out.println("cambia el nombre de la carpeta " + dir2);
                    dir.renameTo(dir2);
                }
                if (api.getDocs() != null) {
                    for (ApisDocs apiDoc : api.getDocs()) {
                        apiDoc.setDirDoc(dir2 + File.separator + apiDoc.getDirDoc());
                    }
                }

            }
            System.out.println("actualiza las rutas del api");
            apiDao.update(api);

        } catch (Exception e) {
            return "fail upload contact the administrator of the page";
        }

        return "apis";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public @ResponseBody
    JsonResponse getDelete(HttpServletRequest request, Model model/*,@PathVariable("idApi") int idAPi*/) {
        // public RedirectView getDelete(HttpServletRequest request, Model model/*,@PathVariable("idApi") int idAPi*/) {
        //RedirectView rv = new RedirectView(request.getContextPath() + "/apis/view");
        JsonResponse jResponse = new JsonResponse();
        String idAPi = request.getParameter("idApi");
        System.out.println(idAPi);
        Api api = apiDao.findById(Integer.valueOf(idAPi));
        //Api api = new Api();
        api.setId(Integer.valueOf(idAPi));
        try {
            apiDao.delete(api);
            jResponse.setStatus("SUCCESS");
            System.out.println("elimino el api con id" + idAPi);
        } catch (Exception e) {
            jResponse.setStatus("FAIL");
        }

        // rv.setExposeModelAttributes(false);
        //return rv;}
        return jResponse;

    }

}
