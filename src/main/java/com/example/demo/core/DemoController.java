package com.example.demo.core;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TODO zhangtao DemoController
 *
 * @author zhangtao
 * @since 2019-04-01 10:39
 */
@RestController
public class DemoController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam String filePath,
                      @RequestParam String sheetName,
                      @RequestParam String titleName) throws Exception {
        Boolean flag = true;
        try {
            File file = new File(filePath);
            Workbook workbook = handle(file.getName(), new FileInputStream(file), sheetName, titleName);
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename="+new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            OutputStream out = response.getOutputStream();
            workbook.write(out);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            return e.getMessage();
        }
        return flag ? "成功":"错误";
    }

    @GetMapping("/test")
    public String test() {
        return "策策策";
    }

    public static Workbook handle(String fileName, InputStream inputStream, String sheetName, String titleName) throws Exception {
        Workbook workbook = null;
        boolean version = true;
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);
        if(!extension.equalsIgnoreCase("xls")) {
            version = false;
        }

        if(version) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        Row firstRow = sheet.getRow(0);
        int flag = -1;
        for(int i = 0; i<firstRow.getLastCellNum(); i++) {
            if(firstRow.getCell(i) != null && firstRow.getCell(i).getStringCellValue() != null) {
                if(firstRow.getCell(i).getStringCellValue().trim().equals(titleName.trim())) {
                    flag = i;
                    break;
                }
            }
        }
        if(flag < 0) {
            throw new Exception("没有匹配到标题");
        }
        //开始执行修改
        for(int i=1; i<sheet.getLastRowNum(); i++) {
            Cell cell = sheet.getRow(i).getCell(flag);
            if(cell != null) {
                cell.setCellValue(formatting(cell.getStringCellValue()));
            }
        }
        return workbook;
    }

    public static String formatting(String test) {
        if(test == null || test.length() == 0) {
            return "";
        }
        String[] tests = test.split(",");
        String result = "";
        for (String str : tests) {
            if(str != null && str.length() > 0) {
                result += str+",";
            }
        }
        if(result.length() > 0) {
            return result.substring(0, result.length()-1);
        } else {
            return "";
        }
    }

}