package com.mxh1995.pro.poi;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
 
import java.io.*;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
 
/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 2011-10-10
 * Time: 16:10:29
 * To change this template use File | Settings | File Templates.
 */
public class PoiTest2 {
 
    /**
     * 只是一个demo，这里假设修改的值是String类型
     * @param exlFile
     * @param sheetIndex
     * @param col
     * @param row
     * @param value
     * @throws Exception
     */
    public static void updateExcel(File exlFile,int sheetIndex,int col,int row,String value)throws Exception{
        FileInputStream fis=new FileInputStream(exlFile);
        HSSFWorkbook workbook=new HSSFWorkbook(fis);
//        workbook.
        HSSFSheet sheet=workbook.getSheetAt(sheetIndex);
 
        HSSFRow r=sheet.getRow(row);
        HSSFCell cell=r.getCell(col);
//        int type=cell.getCellType();
        String str1=cell.getStringCellValue();
        //这里假设对应单元格原来的类型也是String类型
        cell.setCellValue(value);
        System.out.println("单元格原来值为"+str1);
        System.out.println("单元格值被更新为"+value);
 
        fis.close();//关闭文件输入流
 
        FileOutputStream fos=new FileOutputStream(exlFile);
        workbook.write(fos);
        fos.close();//关闭文件输出流
    }
 
 
    private String getCellValue(HSSFCell cell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }
 
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
 
//            下面改成你自己的xls文件进行测试，2003格式的，不能2007
            File file=new File("C:\\Users\\TDH\\Desktop\\PoiTest.xls");
 
         //下面尝试更改第一行第一列的单元格的值
            PoiTest2.updateExcel(file,0,0,0,"更改测试");
    }
}
