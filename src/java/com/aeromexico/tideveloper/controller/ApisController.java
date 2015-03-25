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
import com.aeromexico.tideveloper.models.ajax.Response;
import com.aeromexico.tideveloper.util.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import org.springframework.web.multipart.MultipartFile;
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
    private List<Api> listaApis = new ArrayList<>();

    @ModelAttribute("listaApis")
    public List<Api> getApis() {
        listaApis = apiDao.findAll();
        return listaApis;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String getApi(Model model) {
        System.out.println("En getApis");
        return "apis";
    }

    @RequestMapping(value = "/apisJson", method = RequestMethod.GET)
    public @ResponseBody
    Response getAllApis() {
        List<Api> ListApi = apiDao.findAll();
        // Api api =apiDao.findById(1);
        Response responce = new Response();
        responce.setDraw(1);
        responce.setRecordsTotal(1);
        responce.setRecordsFiltered(1);
        responce.setData(ListApi);
        System.out.println("Recupera los datos del api: ");
        System.out.println(responce);
        return responce;
    }

    @RequestMapping(value = "view/{idApi}", method = RequestMethod.GET)
    public String getApiById(@PathVariable("idApi") int idApi, Model model) {
        Api api = apiDao.findById(idApi);
        model.addAttribute("api", api);
        return "apisVersiones";
    }

    @RequestMapping(value = "view/{idApi}", method = RequestMethod.POST)
    public String postApiById(@PathVariable("idApi") int idApi, Model model, @ModelAttribute("api") Api apiMod, HttpServletRequest request) {

        String resource = request.getParameter("resource");
        String docs = request.getParameter("documentos");

        if (resource != null) {
            for (ApisVersiones apiVersion : apiMod.getVersiones()) {
                Util.editResources(apiVersion.getResources(), apiMod.getId());
            }
        }
        if (docs != null) {
            for (ApisDocs apiDoc : apiMod.getDocs()) {
                Util.editDocs(apiDoc, apiMod.getId());
            }
        }

        apiDao.update(apiMod);
        model.addAttribute("api", apiMod);
        return "apisVersiones";
    }

    @RequestMapping(value = "view/delVersion", method = RequestMethod.GET)
    public RedirectView getDeleteResource(HttpServletRequest request, @RequestParam(value = "version") int posVersion, @ModelAttribute("api") Api apiMod) {
        System.out.println("Estamos en redireccion");

        apiMod.getVersiones().remove(posVersion);
        apiDao.update(apiMod);

        RedirectView rv = new RedirectView(request.getContextPath() + "/apis/view/" + apiMod.getId());
        rv.setExposeModelAttributes(false);
        return rv;
    }

    @RequestMapping(value = "view/downloads", method = RequestMethod.GET)
    public RedirectView getDeleteResource(HttpServletRequest request, @RequestParam(value = "version") int posVersion, @RequestParam(value = "resource") int posResource, @ModelAttribute("api") Api apiMod) {
        System.out.println("Estamos en redireccion");

        apiMod.getVersiones().get(posVersion).getResources().remove(posResource);
        apiDao.update(apiMod);

        RedirectView rv = new RedirectView(request.getContextPath() + "/apis/view/" + apiMod.getId());
        rv.setExposeModelAttributes(false);
        return rv;
    }

    @RequestMapping(value = "view/docs", method = RequestMethod.GET)
    public RedirectView getDeleteDocs(HttpServletRequest request, @RequestParam(value = "doc") int posDoc, @ModelAttribute("api") Api apiMod) {
        System.out.println("Estamos en redireccion");

        apiMod.getDocs().remove(posDoc);
        apiDao.update(apiMod);

        RedirectView rv = new RedirectView(request.getContextPath() + "/apis/view/" + apiMod.getId());
        rv.setExposeModelAttributes(false);
        return rv;
    }

    @RequestMapping(value = "view/downloadFile", method = RequestMethod.GET)
    public ModelAndView getDownloadResource(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "version") int posVersion, @RequestParam(value = "resource") int posResource, @ModelAttribute("api") Api apiMod) {
        System.out.println("Estamos en redireccion");
        ServletContext context = request.getServletContext();

        String rutaFile = apiMod.getVersiones().get(posVersion).getResources().get(posResource).getDirResource();
        String nombreArchivo = rutaFile.substring(rutaFile.lastIndexOf("\\"));
        File downloadFile = new File(rutaFile);
        try {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "view/downloadDocs", method = RequestMethod.GET)
    public ModelAndView getDownloadResource(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "version") int posVersion, @ModelAttribute("api") Api apiMod) {
        System.out.println("Estamos en redireccion");
        ServletContext context = request.getServletContext();

        String rutaFile = apiMod.getDocs().get(posVersion).getDirDoc();
        String nombreArchivo = rutaFile.substring(rutaFile.lastIndexOf("\\"));
        File downloadFile = new File(rutaFile);
        try {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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
                        ApisVersionesResources apiVR = new ApisVersionesResources(name, "resources\\" + nameFile);
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
                    String name = "";
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

                        ApisDocs apiDoc = new ApisDocs(name, resumen, "docs\\" + nameFile);
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
                File dir2 = new File(rootPath + id);
                if (dir.isDirectory()) {
                    dir.renameTo(dir2);
                    System.out.println("cambia el nombre de la carpeta " + dir2);
                }
                if (api.getVersiones().get(0).getResources() != null) {
                    for (ApisVersionesResources apiVR : api.getVersiones().get(0).getResources()) {
                        apiVR.setDirResource(dir2 + File.separator + apiVR.getDirResource());
                    }
                }
                dir = new File(rootPath + nameFolder);
                dir2 = new File(rootPath + id);
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
