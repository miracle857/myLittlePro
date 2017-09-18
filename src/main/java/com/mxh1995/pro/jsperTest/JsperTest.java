package com.mxh1995.pro.jsperTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;

import com.mxh1995.pro.test.Student;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;


public class JsperTest{

	public static void main(String[] args) {
		try {
			new JsperTest().executeExport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeExport() throws Exception {
		
		List<Student> result = new ArrayList<Student>();
		Student s1 = new Student(1, "name1", 12);
		Student s2 = new Student(2, "name2", 22);
		Student s3 = new Student(3, "name3", 32);
		Student s4 = new Student(4, "name4", 42);
		result.add(s4);
		result.add(s3);
		result.add(s2);
		result.add(s1);
		File reportFile = new File("F:\\report1.jasper");   
		if (!reportFile.exists()){  
		   throw new Exception("传入的模板文件不存在!");
		}
		InputStream is = new FileInputStream(reportFile);
		Map parameters = new HashMap();// 参数设定
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(result);
		JasperPrint jasperPrint = JasperFillManager.fillReport(is, parameters, ds);
		/**
		 * 导出文件存放路径
		 */
		String dateTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
		File destFile = new File("student" + dateTime + ".xls");
		/**
		 * 设置输入输出流--执行导出
		 */
		JRXlsExporter exporter = new JRXlsExporter(); // Excel
		executeExport(exporter, jasperPrint, destFile);
	}

	

	private void executeExport(JRXlsExporter exporter, JasperPrint jasperPrint, File destFile)
			throws JRException {
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(false);
		configuration.setDetectCellType(true);// 检查单元格格式
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}



}
