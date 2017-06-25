package com.yang.learn;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yz on 2017/6/25.
 */
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取文件名
        String filename = req.getParameter("name");
        //防止文件名变乱码
        filename = new String(filename.getBytes("iso-8859-1"),"utf-8");

        System.out.println("文件名:" + filename);

        resp.setContentType(getServletContext().getMimeType(filename));
        resp.setHeader("Content-Disposition", "attachment;filename="+filename);

        ServletContext context = this.getServletContext();
        String fullFileName = context.getRealPath("/upload/" + filename);

        InputStream is = new FileInputStream(fullFileName);

        ServletOutputStream os = resp.getOutputStream();

        int len = -1;
        byte[] b = new byte[1024];
        while ((len = is.read(b)) != -1){
            os.write(b,0,len);
        }

        is.close();
        os.close();
    }
}
