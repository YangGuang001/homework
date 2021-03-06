package com.yang.springmvc.view;

import com.yang.springmvc.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by yz on 2017/10/11.
 */
public class ExeclView extends AbstractExcelView {
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = "用户列表excel.xls";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "inline; filename="+new String(fileName.getBytes(),"iso8859-1"));
        OutputStream outputStream = response.getOutputStream();

        List<User> userList = (List<User>) model.get("userList");
        // 产生Excel表头
        HSSFSheet sheet = workbook.createSheet("基本信息");
        HSSFRow header = sheet.createRow(0);
        // 产生标题列
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("名字");
        header.createCell(2).setCellValue("年龄");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));
        int rowNumber = 1;
        for (User user : userList) {
            HSSFRow row = sheet.createRow(rowNumber++);
            // 产生标题列
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getAge());
        }
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
