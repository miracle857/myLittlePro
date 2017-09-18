package com.mxh1995.pro.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PoiTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\TDH\\Desktop\\PoiTest.xls")));
		HSSFSheet sheet = null;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheet = workbook.getSheetAt(i);
			for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
				HSSFRow row = sheet.getRow(j);
				for (int k = 0; k < row.getLastCellNum(); k++) {
					System.out.print(row.getCell(k) + "\t");
				}
				System.out.println();
			}
		}
	}




	public void test2() throws Exception {
		File file = new File("C:\\Users\\TDH\\Desktop\\PoiTest.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		
		//处理后缀为  xls
		//HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		//HSSFSheet sheet = null;
		
		//处理后缀为 xlsx  可以写个if统一管理
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = null;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
			sheet = workbook.getSheetAt(i);
			// i==2 处理第三张表
			if (i == 2) {
				for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {// 获取每行
					Row row = sheet.getRow(j);
					for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {// 获取每个单元格
						// k:列
						// 对列头名字 为“4”的，下属列 全部修改为6
						if (sheet.getRow(0).getCell(k).toString().equals("4")) {
							//不操作 首列
							if(j != 0){
								row.getCell(k).setCellValue("7");
							}
						}
						// 获取全部单元格
						System.out.print(row.getCell(k) + "\t");  
					}
				}
			}
		}
		fileInputStream.close(); // 关闭文件输入流
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.flush();
		fos.close();// 关闭文件输出流
	}


	public void test3() throws Exception {
		File file = new File("C:\\Users\\TDH\\Desktop\\test.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		String fileName = file.getName();
		Workbook workbook = null;
		if (fileName.toLowerCase().endsWith(EXCEL_2003)) {
			workbook = new HSSFWorkbook();
		}
		if (fileName.toLowerCase().endsWith(EXCEL_2007)) {
			workbook = new XSSFWorkbook();
		}
		Sheet sheet = null;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
			sheet = workbook.getSheetAt(i);
			// i==2 处理第三张表
			if (i == 2) {
				loop: for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {// 获取每行
					Row row = sheet.getRow(j);
					for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {// 获取每个单元格
						// 处理第4 第10 列数据
						if (k == 3 || k == 10) {
							if (sheet.getRow(0).getCell(k).toString().equals("是否存在")) {
								System.out.print(sheet.getRow(0).getCell(k));
								row.getCell(k).setCellValue("是");
							}
							System.out.print(row.getCell(k) + "\t");
						}
					}
					System.out.println("---Sheet表" + i + "处理完毕---");
					if (j == 8) {
						break loop;
					}
				}
			}

		}
		fileInputStream.close();
		FileOutputStream fos = new FileOutputStream(file);
		// workbook.write(fos);
		fos.flush();
		fos.close();// 关闭文件输出流
	}

	private static String EXCEL_2003 = ".xls";
	private static String EXCEL_2007 = ".xlsx";



}
