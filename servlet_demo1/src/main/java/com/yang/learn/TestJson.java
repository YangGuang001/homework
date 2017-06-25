package com.yang.learn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

/**
 * Created by yz on 2017/6/25.
 */
public class TestJson extends HttpServlet {
    private static Logger logger = Logger.getLogger(TestJson.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("get begin!!!!");
        response.setCharacterEncoding("UTF-8");
        //json在这里存放的是数组信息
        JSONObject json=new JSONObject();
        JSONArray array=new JSONArray();

        //两个数据
        JSONObject temp1=new JSONObject();
        JSONObject temp2=new JSONObject();

        try {
            //第一个name和sex
            temp1.put("id", 1);
            temp1.put("name", "张三");
            temp1.put("age", "23");
            array.put(0,temp1);

            //第二个name和sex
            temp2.put("id", 2);
            temp2.put("name", "李四");
            temp2.put("age", "33");
            array.put(1,temp2);

            //添加到json中
            json.put("people", array);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //向前台的页面输出结果
        PrintWriter out=response.getWriter();
        out.println(json);
        out.close();

        logger.info("get end!!!!");
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text");
        PrintWriter out = response.getWriter();
        out.flush();
        out.close();
    }
}
