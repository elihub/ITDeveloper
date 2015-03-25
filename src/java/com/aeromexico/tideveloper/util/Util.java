/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.util;

import com.aeromexico.tideveloper.models.ApisDocs;
import com.aeromexico.tideveloper.models.ApisVersionesResources;
import com.aeromexico.tideveloper.security.CustomUser;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Elida Carrillo
 */
public class Util {

    public static void getIdUserLogged(HttpServletRequest request) {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer idUsuario = Long.valueOf(user.getId()).intValue();
        request.setAttribute("idUsuario", idUsuario);
    }

    public static void editResources(List<ApisVersionesResources> apisVersionesResources, int idApi) {
        ResourceBundle properties = ResourceBundle.getBundle("TIDeveloper");
        String rootPath = properties.getString("ApisPath");
        String nameFolder = idApi + "\\resources";

        for (ApisVersionesResources versionResource : apisVersionesResources) {
            if (versionResource.getDirResource() == null || versionResource.getDirResource().equals("")) {
                for (int i = 0; i < versionResource.getFiles().length; i++) {
                    MultipartFile file = versionResource.getFiles()[i];
                    versionResource.setDirResource(uploadFile(file, rootPath, nameFolder));
                }
            } else if (versionResource.getFiles() != null && versionResource.getFiles().length > 0) {
                for (int i = 0; i < versionResource.getFiles().length; i++) {
                    MultipartFile file = versionResource.getFiles()[i];
                    versionResource.setDirResource(uploadFile(file, rootPath, nameFolder));
                }
            }
        }
    }

    public static void editDocs(ApisDocs apiDoc, int idApi) {
        ResourceBundle properties = ResourceBundle.getBundle("TIDeveloper");
        String rootPath = properties.getString("ApisPath");
        String nameFolder = idApi + "\\docs";

        if ((apiDoc.getDirDoc() == null || apiDoc.getDirDoc().equals("")) && apiDoc.getFilesDocs() != null) {
            for (int i = 0; i < apiDoc.getFilesDocs().length; i++) {
                MultipartFile file = apiDoc.getFilesDocs()[i];
                apiDoc.setDirDoc(uploadFile(file, rootPath, nameFolder));
            }
        } else if (apiDoc.getFilesDocs() != null && apiDoc.getFilesDocs().length > 0) {
            for (int i = 0; i < apiDoc.getFilesDocs().length; i++) {
                MultipartFile file = apiDoc.getFilesDocs()[i];
                apiDoc.setDirDoc(uploadFile(file, rootPath, nameFolder));
            }
        }
    }

    private static String uploadFile(MultipartFile file, String rootPath, String folderFinal) {
        
        if (!file.isEmpty()) {
            System.out.println("name file:" + file.getOriginalFilename());
            String extencionFile = "." + FilenameUtils.getExtension(file.getOriginalFilename());
            String name = file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file                     
                File dir = new File(rootPath + folderFinal);
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

                System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                //message = message + "You successfully uploaded file=" + name + extencionFile + "<br />";
                return serverFile.getAbsolutePath();
                //System.out.println(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return "";
    }
}
