package com.example.common.util.file;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVWriter;

public class CsvZipUtil {
	
	
	public static void importMemberWriteCsv(List<String[]> list, HttpServletResponse response, String filename, String zipName, String titles) {
		OutputStream out = null;
		ZipOutputStream zipOutput = null;
		OutputStreamWriter outq = null;
		try {
			// 定义输出类型(下载)
			response.setContentType("application/csv;charset=gb18030");
			// 定义输出文件头
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(zipName.getBytes("gbk"), "ISO8859-1"));
			out = response.getOutputStream();

			zipOutput = new ZipOutputStream(out);
			zipOutput.putNextEntry(new ZipEntry(filename));
			outq = new OutputStreamWriter(zipOutput, "gb2312");

			CSVWriter writer = new CSVWriter(outq, ',');
			String[] title = titles.split(",");
			writer.writeNext(title);
			for (String[] skuStr : list) {
				writer.writeNext(skuStr);
			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zipOutput != null) {
					zipOutput.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		if (outq != null) {
			try {
				outq.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
