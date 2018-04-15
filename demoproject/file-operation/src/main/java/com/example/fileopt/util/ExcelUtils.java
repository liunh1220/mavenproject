package com.example.fileopt.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * EXCEL文档解析工具类
 * 该工具能将EXCEL文档中的表解析为由JAVA基础类构成的数据集合
 * 整个EXCEL表由多个行组成,每行用一个LIST表示.
 * EXCEL中的行由一个LIST表示,各列的数据索引从0开始一一对齐存放在这个LIST中; 多个行构成整个表,由一个LIST存放多个行.
 */
public class ExcelUtils {

	private Logger logger = Logger.getLogger(ExcelUtils.class);

	private HSSFWorkbook workbook;

	/**
	 * 文件输入流方式获取文件
	 *
	 * @param excelFile
	 * @throws IOException
	 */
	public ExcelUtils(File excelFile) throws IOException {
		workbook = new HSSFWorkbook(new FileInputStream(excelFile));
	}

	/**
	 * 纯输入流方式获取文件
	 *
	 * @param is
	 * @throws IOException
	 */
	public ExcelUtils(InputStream is) throws IOException {
		workbook = new HSSFWorkbook(is);
	}

	/**
	 * 获得表中的数据
	 *
	 * @param sheetNum 表格索引 从0开始
	 * @param isAddId  是否自动增加id
	 * @return
	 * @throws IOException
	 */
	public List<String[]> getDatasFromSheet(int sheetNum, boolean isAddId) throws IOException {
		// 表格数据对象
		List<String[]> resultList = new ArrayList<>();
		// 获得指定的表
		HSSFSheet sheet = workbook.getSheetAt(sheetNum);
		// 获得数据总行数
		int rowCount = sheet.getLastRowNum();
		logger.info("excel表总行数: " + rowCount);
		if (rowCount < 1) {
			return null;
		}
		// 逐行读取数据
		for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
			// 获得行对象
			HSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			// 本行单元格的个数
			int columnCount = row.getLastCellNum();
			if (isAddId) {
				columnCount = row.getLastCellNum() + 1;
			}
			String[] rowData = getRowDatas(row, columnCount, isAddId);
			if (rowData != null) {
				resultList.add(rowData);
			}
		}
		return resultList;
	}

	/**
	 * 获得表中的数据
	 *
	 * @param sheetNum  表格索引 从0开始
	 * @param columnNum 每行数据个数
	 * @param isAddId   是否自动增加id
	 * @return
	 * @throws IOException
	 */
	public List<String[]> getDatasFromSheet(int sheetNum, int columnNum, boolean isAddId) throws IOException {
		// 表格数据对象
		List<String[]> resultList = new ArrayList<>();
		// 获得指定的表
		HSSFSheet sheet = workbook.getSheetAt(sheetNum);
		// 获得数据总行数
		int rowCount = sheet.getLastRowNum();
		logger.info("excel表总行数: " + rowCount);
		if (rowCount < 1) {
			return null;
		}
		if (isAddId) {
			columnNum++;
		}
		// 逐行读取数据
		for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
			// 获得行对象
			HSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			String[] rowData = getRowDatas(row, columnNum, isAddId);
			if (rowData != null) {
				resultList.add(rowData);
			}
		}
		return resultList;
	}

	/**
	 * 获得表中的数据
	 *
	 * @param sheetNumber 表格索引 从0开始
	 * @param flag        flag=0 不增加id,flag=1自动增加id
	 * @return
	 * @throws IOException
	 */
	public List<Object[]> getNewDatasInSheet(int sheetNumber, int flag) throws IOException {
		List<Object[]> result = new ArrayList<Object[]>();
		// 获得指定的表
		HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
		// 获得数据总行数
		int rowCount = sheet.getLastRowNum();
		//int rowCount=this.getRightRows(sheetAs);
		int sjrsCols = 0;
		logger.info("found excel rows count: " + rowCount);
		if (rowCount < 1) {
			return null;
		}
		// 逐行读取数据
		for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
			// 获得行对象
			HSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				// 获得本行中单元格的个数
				int columnCount = 0;
				if (flag == 0) {
					columnCount = row.getLastCellNum();
				} else {
					columnCount = row.getLastCellNum() + 1;
				}
				Object[] rowData = new Object[columnCount];
				// 获得本行中各单元格中的数据
				if (flag == 0) {
					for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
						HSSFCell cell = row.getCell(columnIndex);
						// 获得指定单元格中数据
						Object cellStr = this.getCellString(cell);
						if (cellStr != null) {
							if (cellStr.toString().trim().length() > 0) {
								rowData[columnIndex] = cellStr.toString().trim();
								sjrsCols++;
							}
						}
					}
				} else {
					for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
						if (columnIndex == 0) {
							rowData[0] = KeyGenerator.getUUID();
						} else {
							// 获得指定单元格中数据
							HSSFCell cell = row.getCell(columnIndex - 1);
							Object cellStr = this.getCellString(cell);
							if (cellStr != null) {
								if (cellStr.toString().trim().length() > 0) {
									rowData[columnIndex] = cellStr.toString().trim();
									sjrsCols++;
								}
							}
						}
					}
				}
				if (sjrsCols != 0) {
					result.add(rowData);
				}
				sjrsCols = 0;
			}
		}
		return result;
	}

	/**
	 * 获取单行单元格数据
	 * @param row
	 * @param columnNum
	 * @param isAddId
	 * @return
	 */
	private String[] getRowDatas(HSSFRow row, int columnNum, boolean isAddId) {
		String[] rowData = new String[columnNum];
		boolean hasData = false;
		for (int i = 0; i < columnNum; i++) {
			int index = i;
			if (isAddId) {
				index = i - 1;
				if (i == 0) {
					rowData[0] = KeyGenerator.getUUID();
				}
			}
			HSSFCell cell = row.getCell(index);
			// 获得指定单元格中数据
			String cellStr = this.getCellString(cell);
			if (StringUtils.isNotEmpty(cellStr)) {
				rowData[i] = cellStr;
				hasData = true;
			}
		}
		if (hasData) {
			return rowData;
		} else {
			return null;
		}
	}

	/**
	 * 获得单元格中的内容
	 *
	 * @param cell 单元格对象
	 * @return
	 */
	protected String getCellString(HSSFCell cell) {
		String result = null;
		if (cell != null) {
			int cellType = cell.getCellType();
			switch (cellType) {
				case HSSFCell.CELL_TYPE_STRING:
					result = cell.getRichStringCellValue().getString();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					DecimalFormat df = new DecimalFormat("#");
					result = df.format(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					result = cell.getCellFormula();
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					result = null;
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					result = cell.getBooleanCellValue() ? "true" : "false";
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					result = null;
					break;
			}
			if (result != null) {
				result = result.trim();
			}
		}
		return result;
	}

}
