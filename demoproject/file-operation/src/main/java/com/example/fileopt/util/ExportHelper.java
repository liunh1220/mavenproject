package com.example.fileopt.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.example.fileopt.entity.vo.ExportParamsVo;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 导出excel
 */
public class ExportHelper {

	private static final Logger logger = Logger.getLogger(ExportHelper.class);

	/**
	 * 
	 * 方法描述：excel导出(POI)
	 * 
	 * @param templatePath
	 *            模板路径
	 * @param fileName
	 *            文件名
	 * @param sheetName
	 * @param list
	 * @param listAmount
	 * @param paras
	 *            {(开始行),(列数)}
	 * @param numCol
	 *            数值列 {1,2,3,....}
	 * @param amountCol
	 *            "合计"标题 {(开始列),(结束列)}
	 * @param indexMap
	 *            指定索引值(0,3)....
	 * @param bIndex
	 *            是否有序号
	 * @param response
	 */
	@Deprecated
	public void doExport(List list, String templatePath, String fileName, String sheetName, Object[] listAmount, int[] paras, int[] numCol, int[] amountCol,
			Map<Integer, Integer> indexMap, boolean bIndex, HttpServletResponse response) {

		FileInputStream fileInputStream = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		OutputStream outputStream = null;
		try {

			fileInputStream = new FileInputStream(new File(templatePath));
			workbook = new HSSFWorkbook(fileInputStream);
			List<List<Object>> batchList = getListPage(list, 60000);
			if (null != batchList && batchList.size() > 0) {
				for (int i = 0; i < batchList.size(); i++) {
					List subList = batchList.get(i);
					workbook.setSheetName(i, sheetName + "_" + (i + 1));
					sheet = workbook.getSheetAt(i);
					writeExcel(subList, sheet, listAmount, paras, numCol, amountCol, indexMap, bIndex);
				}
			}
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(fileName);
		} finally {
			try {
				if (null != outputStream) {
					outputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if (null != fileInputStream) {
					fileInputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 写入excel(指定索引值写入列)(POI)
	 * 
	 * @param list
	 *            结果集
	 * @param sheet
	 * @param listAmount
	 *            合计结果集
	 * @param paras
	 *            {(开始行),(总列数)}
	 * @param numCol
	 *            数值列 {1,2,3,....}
	 * @param amountCol
	 *            "合计"标题 {(开始列),(结束列数)}
	 * @param indexMap
	 *            指定索引值(0,3)....
	 * @throws Exception
	 */
	@Deprecated
	public void writeExcel(List list, HSSFSheet sheet, Object[] listAmount, int[] paras, int[] numCol, int[] amountCol, Map<Integer, Integer> indexMap, boolean bIndex) {
		int row = paras[0];
		int column = paras[1];
		try {
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					HSSFRow hssfRow = ((HSSFSheet) sheet).createRow(row);

					if (bIndex) {
						for (int n = 0; n < column; n++) {
							Integer objIndex = indexMap.get(n);
							if (n == 0) {
								HSSFCell cell = hssfRow.createCell((short) n);
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								cell.setCellValue(i + 1);
							} else {
								if (isNumber(obj[objIndex])) {
									HSSFCell cell = hssfRow.createCell((short) n);
									cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
									cell.setCellValue(Double.parseDouble(obj[objIndex] == null ? "0" : obj[objIndex].toString()));
								} else if (!isNumber(obj[objIndex])) {
									HSSFCell cell = hssfRow.createCell((short) n);
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									cell.setCellValue(obj[objIndex] == null ? "" : obj[objIndex].toString());
								}
							}
						}
					} else {
						for (int n = 0; n < column; n++) {
							Integer objIndex = indexMap.get(n);
							if (isNumber(obj[objIndex])) {
								HSSFCell cell = hssfRow.createCell((short) n);
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								cell.setCellValue(Double.parseDouble(obj[objIndex] == null ? "0" : obj[objIndex].toString()));
							} else if (!isNumber(obj[objIndex])) {
								HSSFCell cell = hssfRow.createCell((short) n);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(obj[objIndex] == null ? "" : obj[objIndex].toString());
							}
						}
					}
					row++;
				}
				if (null != listAmount && listAmount.length > 0 && null != amountCol) {
					int startCol = amountCol[0];
					int endCol = amountCol[1];

					HSSFRow hssfRow = ((HSSFSheet) sheet).createRow(row);
					HSSFCell cell = hssfRow.createCell((short) startCol);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					Region region = new Region(row, (short) startCol, row, (short) endCol);
					sheet.addMergedRegion(region);
					cell.setCellValue("合计");
					for (int i = 0; i < listAmount.length; i++) {

						HSSFCell amountCell = hssfRow.createCell((short) row);
						amountCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						amountCell.setCellValue(Double.parseDouble(listAmount[i] == null ? "0" : listAmount[i].toString()));
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	public boolean compareInt(int[] intArray, int initInt) {
		boolean ck = false;
		if (null != intArray) {
			for (int i = 0; i < intArray.length; i++) {
				if (initInt == intArray[i]) {
					ck = true;
					break;
				}
			}
		}
		return ck;
	}

	public boolean isNumber(Object obj) {
		if (null == obj) {
			return false;
		}
		try {
			Integer i = (Integer) obj;
			return true;
		} catch (Exception e) {

		}
		// Pattern pattern1 = Pattern.compile("^\\d+$|-\\d+$"); // 就是判断是否为整数
		Pattern pattern2 = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");// 判断是否为double
		return pattern2.matcher(obj.toString()).matches();
	}

	/**
	 * 导出excel
	 * 
	 * @param filePath
	 * @param fileName
	 * @param response
	 */
	public void export(String filePath, String fileName, HttpServletResponse response) {
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		BufferedOutputStream bufferedOutputStream = null;

		long start = System.currentTimeMillis();

		int contentLength = 0;

		try {

			File file = new File(filePath);

			if (file.exists() && file.isFile()) {

				byte[] buf = new byte[1024];
				int len = 0;

				contentLength = (int) file.length();

				response.reset();
				response.setContentType("application/x-msdownload");
				response.setContentLength(contentLength); // 弹出下载框显示进度
				response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName).getBytes("GBK"), "ISO8859-1"));

				fileInputStream = new FileInputStream(file);
				bufferedInputStream = new BufferedInputStream(fileInputStream);
				outputStream = response.getOutputStream();
				bufferedOutputStream = new BufferedOutputStream(outputStream);

				while ((len = bufferedInputStream.read(buf)) != -1) {
					bufferedOutputStream.write(buf, 0, len);
				}
				bufferedOutputStream.flush();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(fileName);

			long exportTime = System.currentTimeMillis() - start;
			logger.error("export:" + exportTime + "ms," + contentLength + "byte");

		} finally {
			try {
				if (null != bufferedOutputStream) {
					bufferedOutputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			try {
				if (null != outputStream) {
					outputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			try {
				if (null != bufferedInputStream) {
					bufferedInputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			try {
				if (null != fileInputStream) {
					fileInputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 组合List字段
	 * 
	 * @param list
	 * @param mixIndexMap
	 *            组合索引[(0,(2,4)),(1,(3,5))]:第0列是0,2,4列合并，第1列是1,3,5列合并
	 * @return
	 */
	public List<Object> mixField(List<Object> list, Map<Integer, Integer[]> mixIndexMap) {
		List<Object> listTmp = new ArrayList<Object>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				for (int n = 0; n < obj.length; n++) {
					if (null != mixIndexMap.get(n)) {
						Integer[] mixIndex = mixIndexMap.get(n);
						String mixer = obj[n] == null ? "" : obj[n].toString();
						String mixTarget = "";
						for (int k = 0; k < mixIndex.length; k++) {
							mixTarget += obj[mixIndex[k]] == null ? "" : obj[mixIndex[k]].toString();
						}
						obj[n] = mixer + mixTarget;
					}
				}
				listTmp.add(obj);
			}
		}
		return listTmp;
	}

	/**
	 * 
	 * @description LIST分页
	 * @param list
	 * @param pageSize
	 * @return
	 */
	@Deprecated
	public List<List<Object>> getListPage(List<Object> list, Integer pageSize) {
		List<List<Object>> batchList = new ArrayList<List<Object>>();
		// 总记录数
		Integer rowCount = 0;
		if (null != list && list.size() > 0) {
			rowCount = list.size();
		}
		// 记录分页数
		Integer listPage = 0;
		if (rowCount % pageSize == 0) {
			listPage = rowCount / pageSize;
		} else {
			listPage = rowCount / pageSize + 1;
		}
		Integer pageNo = 0;
		// 数据分页
		for (int n = 0; n < listPage; n++) {
			pageNo = n + 1;
			// 每页开始行
			Integer startIndex = pageNo * pageSize - pageSize;
			// 每页结束行
			Integer endIndex = 0;
			if (startIndex + pageSize > rowCount) {
				endIndex = rowCount;
			} else {
				endIndex = startIndex + pageSize;
			}
			// 子Map
			List<Object> tmpObjsList = new ArrayList<Object>();
			// 划分list
			for (int i = startIndex; i < endIndex; i++) {
				tmpObjsList.add(list.get(i));
			}
			batchList.add(tmpObjsList);
		}
		return batchList;
	}

	/**
	 * 
	 * @description excel导出(POI)
	 * @param list
	 * @param templatePath
	 *            模板路径
	 * @param fileName
	 *            文件名
	 * @param sheetName
	 * @param paras
	 *            {(开始行),(列数)}
	 * @param numCol
	 *            数值列 {1,2,3,....}
	 * @param response
	 */
	@Deprecated
	public void doExportZip(List<Object[]> list, String templatePath, String fileName, String sheetName, int[] paras, int[] numCol, HttpServletResponse response) {

//		FileInputStream fileInputStream = null;
//		HSSFWorkbook workbook = null;
//		HSSFSheet sheet = null;
//		OutputStream outputStream = null;
//		ZipOutputStream zipOutput = null;
//
//		try {
//
//			fileInputStream = new FileInputStream(new File(templatePath));
//			workbook = new HSSFWorkbook(fileInputStream);
//			List<List<Object[]>> batchList = getListPageForExport(list, 60000);
//			if (null != batchList && batchList.size() > 0) {
//				for (int i = 0; i < batchList.size(); i++) {
//					List<Object[]> subList = batchList.get(i);
//					workbook.setSheetName(i, sheetName + "_" + (i + 1));
//					sheet = workbook.getSheetAt(i);
//					writeExcelSimple(subList, sheet, paras, numCol);
//				}
//			}
//			response.reset();
//			response.setContentType("application/x-msdownload");
//			response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName).getBytes("GBK"), "ISO8859-1") + ".zip");
//			outputStream = response.getOutputStream();
//			zipOutput = new ZipOutputStream(outputStream);
//			zipOutput.putNextEntry(new ZipEntry("exportData.xls"));
//			zipOutput.setEncoding("GBK");
//			workbook.write(zipOutput);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			logger.error(fileName);
//		} finally {
//			try {
//				if (zipOutput != null) {
//
//					zipOutput.close();
//
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//			try {
//				if (null != outputStream) {
//					outputStream.close();
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//			try {
//				if (null != fileInputStream) {
//					fileInputStream.close();
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//		}
	}

	@Deprecated
	public void doExportLocalZip(List<Object[]> list, String templatePath, String fileName, String sheetName, int[] paras, int[] numCol, HttpServletResponse response) {

//		FileInputStream fileInputStream = null;
//		HSSFWorkbook workbook = null;
//		HSSFSheet sheet = null;
//		FileOutputStream fileOutputStream = null;
//		ZipOutputStream zipOutput = null;
//
//		File tempFile = new File(templatePath);
//
//		long currentTime = System.currentTimeMillis();
//
//		String tempFileName = fileName + "_" + currentTime + ".zip";// 导出临时文件名
//
//		String exportPath = tempFile.getParent() + "\\" + tempFileName;// 导出路径
//
//		try {
//
//			fileInputStream = new FileInputStream(new File(templatePath));
//			workbook = new HSSFWorkbook(fileInputStream);
//			List<List<Object[]>> batchList = getListPageForExport(list, 60000);
//			if (null != batchList && batchList.size() > 0) {
//				for (int i = 0; i < batchList.size(); i++) {
//					List<Object[]> subList = batchList.get(i);
//					workbook.setSheetName(i, sheetName + "_" + (i + 1));
//					sheet = workbook.getSheetAt(i);
//					writeExcelSimple(subList, sheet, paras, numCol);
//				}
//			}
//			response.reset();
//			response.setContentType("application/x-msdownload");
//			response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName).getBytes("GBK"), "ISO8859-1") + ".zip");
//			fileOutputStream = new FileOutputStream(exportPath);
//			zipOutput = new ZipOutputStream(fileOutputStream);
//			zipOutput.putNextEntry(new ZipEntry("exportData.xls"));
//			zipOutput.setEncoding("GBK");
//			workbook.write(zipOutput);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			logger.error(fileName);
//		} finally {
//			try {
//				if (zipOutput != null) {
//
//					zipOutput.close();
//
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//			try {
//				if (null != fileOutputStream) {
//					fileOutputStream.close();
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//			try {
//				if (null != fileInputStream) {
//					fileInputStream.close();
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//		}
//		// 导出
//		export(exportPath, fileName + ".zip", response);
//
//		delFile(exportPath);// 删除文件

	}

	@Deprecated
	public void doExportLocal(List<Object[]> list, String templatePath, String fileName, String sheetName, int[] paras, int[] numCol, HttpServletResponse response) {

		FileInputStream fileInputStream = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		FileOutputStream fileOutputStream = null;

		File tempFile = new File(templatePath);

		long currentTime = System.currentTimeMillis();

		String tempFileName = fileName + "_" + currentTime + ".xls";// 导出临时文件名

		String exportPath = tempFile.getParent() + "\\" + tempFileName;// 导出路径

		try {

			fileInputStream = new FileInputStream(new File(templatePath));
			workbook = new HSSFWorkbook(fileInputStream);
			List<List<Object[]>> batchList = getListPageForExport(list, 60000);
			if (null != batchList && batchList.size() > 0) {
				for (int i = 0; i < batchList.size(); i++) {
					List<Object[]> subList = batchList.get(i);
					workbook.setSheetName(i, sheetName + "_" + (i + 1));
					sheet = workbook.getSheetAt(i);
					writeExcelSimple(subList, sheet, paras, numCol);
				}
			}
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName).getBytes("GBK"), "ISO8859-1") + ".zip");
			fileOutputStream = new FileOutputStream(exportPath);
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(fileName);
		} finally {
			try {
				if (null != fileOutputStream) {
					fileOutputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if (null != fileInputStream) {
					fileInputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		// 导出
		export(exportPath, fileName + ".xls", response);

		delFile(exportPath);// 删除文件

	}

	/**
	 * 
	 * @description 写入excel(POI)
	 * @param list
	 *            结果集
	 * @param sheet
	 * @param paras
	 *            {(开始行),(总列数)}
	 * @param numCol
	 *            数值列 {1,2,3,....}
	 */
	public void writeExcelSimple(List<Object[]> list, HSSFSheet sheet, int[] paras, int[] numCol) {
		int row = paras[0];
		int column = paras[1];
		try {
			if (CollectionUtils.isNotEmpty(list)) {

				HSSFRow hssfRow = null;

				HSSFCell cell = null;

				for (Object[] objs : list) {

					hssfRow = sheet.createRow(row);

					for (int n = 0; n < column; n++) {
						if (isNumber(objs[n])) {
							cell = hssfRow.createCell(n);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.parseDouble(ObjectUtils.defaultIfNull(objs[n], "0.0").toString()));
						} else {
							cell = hssfRow.createCell(n);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(ObjectUtils.defaultIfNull(objs[n], "").toString());
						}
					}
					row++;
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	/**
	 * 
	 * @description LIST分页
	 * @param list
	 * @param pageSize
	 * @return
	 */
	public List<List<Object[]>> getListPageForExport(List<Object[]> list, Integer pageSize) {
		List<List<Object[]>> batchList = new ArrayList<List<Object[]>>();
		// 总记录数
		Integer rowCount = 0;
		if (null != list && list.size() > 0) {
			rowCount = list.size();
		}
		// 记录分页数
		Integer listPage = 0;
		if (rowCount % pageSize == 0) {
			listPage = rowCount / pageSize;
		} else {
			listPage = rowCount / pageSize + 1;
		}
		Integer pageNo = 0;
		// 数据分页
		for (int n = 0; n < listPage; n++) {
			pageNo = n + 1;
			// 每页开始行
			Integer startIndex = pageNo * pageSize - pageSize;
			// 每页结束行
			Integer endIndex = 0;
			if (startIndex + pageSize > rowCount) {
				endIndex = rowCount;
			} else {
				endIndex = startIndex + pageSize;
			}
			// 子Map
			List<Object[]> tmpObjsList = new ArrayList<Object[]>();
			// 划分list
			for (int i = startIndex; i < endIndex; i++) {
				tmpObjsList.add(list.get(i));
			}
			batchList.add(tmpObjsList);
		}
		return batchList;
	}

	/**
	 * 
	 * @description 删除文件
	 * @param filePath
	 */
	public void delFile(String filePath) {

		File file = new File(filePath);

		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * 
	 * @description 导出EXCEL(异步)
	 * @param list
	 * @param templatePath
	 *            模板路径
	 * @param fileName
	 *            文件名
	 * @param sheetName
	 * @param paras
	 *            {(开始行),(列数)}
	 * @param numCol
	 *            数值列 {1,2,3,....}
	 * @return
	 */
	public ResultMsg doExportLocalAsync(List<Object[]> list, String templatePath, String fileName, String sheetName, int[] paras, int[] numCol) throws Exception {

		ResultMsg resultMsg = new ResultMsg();

		resultMsg.setSuccess(false);

		FileInputStream fileInputStream = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		FileOutputStream fileOutputStream = null;

		File tempFile = new File(templatePath);

		long currentTime = System.currentTimeMillis();

	//	String tempFileName = fileName + currentTime + ".xls";// 导出临时文件名
		String tempFileName = fileName;
		String exportPath = tempFile.getParent() + File.separator + tempFileName;// 导出路径

		try {

			fileInputStream = new FileInputStream(new File(templatePath));
			workbook = new HSSFWorkbook(fileInputStream);
			List<List<Object[]>> batchList = getListPageForExport(list, 60000);
			if (null != batchList && batchList.size() > 0) {
				for (int i = 0; i < batchList.size(); i++) {
					List<Object[]> subList = batchList.get(i);
					workbook.setSheetName(i, sheetName + "_" + (i + 1));
					sheet = workbook.getSheetAt(i);
					writeExcelSimple(subList, sheet, paras, numCol);
				}
			}
			fileOutputStream = new FileOutputStream(exportPath);
			workbook.write(fileOutputStream);

			resultMsg.setSuccess(true);
		} catch (Exception e) {

			resultMsg.setSuccess(false);
			resultMsg.setMsg("导出失败！");

			logger.error(e.getMessage(), e);

			throw e;
		} finally {
			try {
				if (null != fileOutputStream) {
					fileOutputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
			try {
				if (null != fileInputStream) {
					fileInputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
		resultMsg.setReCode(exportPath);
		resultMsg.setIdCode(fileName + ".xls");

		return resultMsg;

	}

	/**
	 * 
	 * @description 导出EXCEL(异步)
	 * @param list
	 * @param templatePath
	 *            模板路径
	 * @param fileName
	 *            文件名
	 * @param sheetName
	 * @param paras
	 *            {(开始行),(列数)}
	 * @param numCol
	 *            数值列 {1,2,3,....}
	 * @return
	 */
	public ResultMsg doExportLocalZipAsync(List<Object[]> list, String templatePath, String fileName, String sheetName, int[] paras, int[] numCol) throws Exception {

//		ResultMsg resultMsg = new ResultMsg();
//
//		resultMsg.setSuccess(false);
//
//		FileInputStream fileInputStream = null;
//		HSSFWorkbook workbook = null;
//		HSSFSheet sheet = null;
//		FileOutputStream fileOutputStream = null;
//		ZipOutputStream zipOutput = null;
//
//		File tempFile = new File(templatePath);
//
//		long currentTime = System.currentTimeMillis();
//
//		String tempFileName = fileName + "_" + currentTime + ".xls";// 导出临时文件名
//
//		String exportPath = tempFile.getParent() + File.separator + tempFileName;// 导出路径
//
//		try {
//
//			fileInputStream = new FileInputStream(new File(templatePath));
//			workbook = new HSSFWorkbook(fileInputStream);
//			List<List<Object[]>> batchList = getListPageForExport(list, 60000);
//			if (null != batchList && batchList.size() > 0) {
//				for (int i = 0; i < batchList.size(); i++) {
//					List<Object[]> subList = batchList.get(i);
//					workbook.setSheetName(i, sheetName + "_" + (i + 1));
//					sheet = workbook.getSheetAt(i);
//					writeExcelSimple(subList, sheet, paras, numCol);
//				}
//			}
//			fileOutputStream = new FileOutputStream(exportPath);
//			zipOutput = new ZipOutputStream(fileOutputStream);
//			zipOutput.putNextEntry(new ZipEntry("exportData.xls"));
//			zipOutput.setEncoding("GBK");
//
//			workbook.write(zipOutput);
//
//			resultMsg.setSuccess(true);
//
//		} catch (Exception e) {
//
//			resultMsg.setSuccess(false);
//			resultMsg.setMsg("导出失败！");
//
//			logger.error(e.getMessage(), e);
//
//			throw e;
//		} finally {
//			try {
//				if (null != zipOutput) {
//
//					zipOutput.close();
//
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//			try {
//				if (null != fileOutputStream) {
//					fileOutputStream.close();
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//				throw e;
//			}
//			try {
//				if (null != fileInputStream) {
//					fileInputStream.close();
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//				throw e;
//			}
//		}
//		resultMsg.setReCode(exportPath);
//		resultMsg.setIdCode(fileName + ".zip");
//
//		return resultMsg;
		return null;

	}

	/**
	 * 
	 * @description 打印发票
	 * @param params
	 *            参数
	 * @param templatePath
	 *            模板路径
	 * @param fileName
	 *            名称
	 * @param response
	 * @throws Exception
	 */
	public static void doExportPDF(Map<Object, Object> params, String templatePath, String fileName, HttpServletResponse response) throws Exception {

		JDBCUtils utils = JDBCUtils.getInstance();

		Connection conn = null;

		OutputStream outputStream = null;

		try {
			conn = utils.getConnectionForSalve();

//			byte[] bytes = JasperRunManager.runReportToPdf(templatePath, params, conn);
//
//			response.reset();
//			response.setContentType("application/pdf");
//			response.setContentLength(bytes.length);
//			response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName + ".pdf").getBytes("GBK"), "ISO8859-1"));
//
//			outputStream = response.getOutputStream();
//			outputStream.write(bytes, 0, bytes.length);
//			outputStream.flush();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			try {
				if (null != outputStream) {
					outputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
		}

	}

	/**
	 * 
	 * @description 打印发票(批量)
	 * @param exportParamsVoList
	 *            参数
	 * @param fileName
	 *            名称
	 * @param response
	 * @throws Exception
	 */
	public static void doExportCollectionPDF(List<ExportParamsVo> exportParamsVoList, String fileName, HttpServletResponse response) throws Exception {

		JDBCUtils utils = JDBCUtils.getInstance();

		Connection conn = null;

		OutputStream outputStream = null;

		try {
//			conn = utils.getConnectionForSalve();
//
//			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
//
//			for (ExportParamsVo exportParamsVo : exportParamsVoList) {
//				String templatePath = exportParamsVo.getTemplatePath();
//				Map<Object, Object> params = exportParamsVo.getParams();
//
//				JasperPrint jasperPrint = JasperFillManager.fillReport(templatePath, params, conn);
//
//				jasperPrintList.add(jasperPrint);
//			}
//
//			response.reset();
//			response.setContentType("application/pdf");
//			response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName + ".pdf").getBytes("GBK"), "ISO8859-1"));
//
//			outputStream = response.getOutputStream();
//
//			JRPdfExporter exporter = new JRPdfExporter();
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//			exporter.exportReport();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			try {
				if (null != outputStream) {
					outputStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
		}

	}

}
