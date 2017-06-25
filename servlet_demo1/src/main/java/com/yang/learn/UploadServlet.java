package com.yang.learn;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by yz on 2017/6/25.
 */
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "upload";

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;//3MB

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;//40MB

    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;//50MB

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(req)){
            //则停止
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        //配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MEMORY_THRESHOLD);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);

        upload.setSizeMax(MAX_REQUEST_SIZE);

        upload.setHeaderEncoding("UTF-8");

        String uploadPath = req.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }

        try{
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0){
                for (FileItem item : formItems){
                    if (!item.isFormField()){
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        System.out.println(filePath);
                        item.write(storeFile);
                        req.setAttribute("message","文件上传成功!");
                    }
                }
            }
        }catch (Exception e){
            req.setAttribute("message","错误信息: " + e.getMessage());
        }

        req.getServletContext().getRequestDispatcher("/page/message.jsp").forward(req,resp);
    }
}
