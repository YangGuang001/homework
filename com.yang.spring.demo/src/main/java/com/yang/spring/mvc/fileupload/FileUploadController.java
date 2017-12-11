package com.yang.spring.mvc.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileModel fileModel = new FileModel();
        ModelAndView modelAndView = new ModelAndView("fileUpload", "command", fileModel);
        return modelAndView;
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileUpload(@Validated FileModel fileModel, BindingResult result, ModelMap modelMap) throws IOException{
        if (result.hasErrors()) {
            System.out.printf("validated errors");
            return "fileUpload";
        } else {
            System.out.printf("Fetching file");
            MultipartFile multipartFile = fileModel.getFile();
            String upLoadPath = context.getRealPath("") + File.separator + "temp" + File.separator;

            FileCopyUtils.copy(fileModel.getFile().getBytes(), new File(upLoadPath+fileModel.getFile().getOriginalFilename()));
            String fileName = multipartFile.getOriginalFilename();
            modelMap.addAttribute("fileName", fileName);
            return "success";
        }

    }
}
