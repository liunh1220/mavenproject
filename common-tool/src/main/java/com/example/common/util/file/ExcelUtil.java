package com.example.common.util.file;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.lang.reflect.Field;  
import java.lang.reflect.Method;  
import java.lang.reflect.Type;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFFont;  
import org.apache.poi.hssf.usermodel.HSSFRichTextString;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.util.HSSFColor;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.util.CellRangeAddress;

import com.example.common.annotation.ExcelAnnotation;  
  
public class ExcelUtil<T> {  
    /** 
     * 从excel导入,返回一个list集合 
     *  
     * @author  
     * @param file 
     *            导入的excel文件 
     * @param pattern 
     * @return 
     * */  
    Class<T> clazz;  
  
    public ExcelUtil(Class<T> clazz) {  
        this.clazz = clazz;  
    }  
  
    public Collection<T> importExcel(File file, String... pattern)  
            throws Exception {  
        Collection<T> dist = new ArrayList<T>();  
        try {  
            /** 
             * 类反射得到调用方法 
             */  
            // 得到目标目标类的所有的字段列表  
            Field filed[] = clazz.getDeclaredFields();  
            // 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中  
            Map fieldmap = new HashMap();  
            // 循环读取所有字段  
            for (int i = 0; i < filed.length; i++) {  
                Field f = filed[i];  
                // 得到单个字段上的Annotation  
                ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);  
                // 如果标识了Annotationd的话  
                if (exa != null) {  
                    // 构造设置了Annotation的字段的Setter方法  
                    String fieldname = f.getName();  
                    String setMethodName = "set"  
                            + fieldname.substring(0, 1).toUpperCase()  
                            + fieldname.substring(1);  
                    // 构造调用的method，  
                    Method setMethod = clazz.getMethod(setMethodName,  
                            new Class[] { f.getType() });  
                    // 将这个method以Annotaion的名字为key来存入。  
                    fieldmap.put(exa.exportName(), setMethod);  
                }  
            }  
            /** 
             * excel的解析开始 
             */  
            // 将传入的File构造为FileInputStream;  
            FileInputStream in = new FileInputStream(file);  
            // // 得到工作表  
            HSSFWorkbook book = new HSSFWorkbook(in);  
            // // 得到第一页  
            HSSFSheet sheet = book.getSheetAt(0);  
            // // 得到第一面的所有行  
            Iterator<Row> row = sheet.rowIterator();  
  
            /** 
             * 标题解析 
             */  
            // 得到第一行，也就是标题行  
            Row title = row.next();  
            // 得到第一行的所有列  
            Iterator<Cell> cellTitle = title.cellIterator();  
            // 将标题的文字内容放入到一个map中。  
            Map titlemap = new HashMap();  
            // 从标题第一列开始  
            int i = 0;  
            // 循环标题所有的列  
            while (cellTitle.hasNext()) {  
                Cell cell = cellTitle.next();  
                String value = cell.getStringCellValue();  
                // 还是把表头trim一下  
                value = value.trim();  
                titlemap.put(i, value);  
                i = i + 1;  
            }  
            /** 
             * 解析内容行 
             */  
            // 用来格式化日期的DateFormat  
            SimpleDateFormat sf;  
            if (pattern.length < 1) {  
                sf = new SimpleDateFormat("yyyy-MM-dd");  
            } else  
                sf = new SimpleDateFormat(pattern[0]);  
            while (row.hasNext()) {  
                // 标题下的第一行  
                Row rown = row.next();  
  
                // 行的所有列  
                Iterator<Cell> cellbody = rown.cellIterator();  
                // 得到传入类的实例  
                T tObject = clazz.newInstance();  
                int k = 0;  
                // 遍历一行的列  
                while (cellbody.hasNext()) {  
                    Cell cell = cellbody.next();  
                    // 这里得到此列的对应的标题  
                    String titleString = (String) titlemap.get(k);  
                    // 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值  
                    if (fieldmap.containsKey(titleString)) {  
                        Method setMethod = (Method) fieldmap.get(titleString);  
                        // 得到setter方法的参数  
                        Type[] ts = setMethod.getGenericParameterTypes();  
                        // 只要一个参数  
                        String xclass = ts[0].toString();  
                        // 判断参数类型  
                        try {  
                            switch (cell.getCellType()) {  
                            case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
                                if (xclass.equals("class java.lang.String")) {  
                                    if ((cell.getNumericCellValue() + "")  
                                            .indexOf(".") > 0) {  
                                        setMethod  
                                                .invoke(tObject,  
                                                        (cell.getNumericCellValue() + "")  
                                                                .substring(  
                                                                        0,  
                                                                        (cell.getNumericCellValue() + "")  
                                                                                .lastIndexOf(".")));  
                                    }  
                                } else if (xclass  
                                        .equals("class java.lang.Integer")) {  
                                    setMethod.invoke(tObject,  
                                            (int) cell.getNumericCellValue());  
                                } else if (xclass.equals("int")) {  
                                    setMethod.invoke(tObject,  
                                            (int) cell.getNumericCellValue());  
                                }  
                                break;  
                            case HSSFCell.CELL_TYPE_STRING: // 字符串  
                                if (xclass.equals("class java.lang.Integer")) {  
                                    setMethod.invoke(tObject,  
                                            Integer.parseInt(cell  
                                                    .getStringCellValue()));  
                                } else if (xclass  
                                        .equals("class java.lang.String")) {  
                                    setMethod.invoke(tObject, cell  
                                            .getStringCellValue().trim());  
                                } else if (xclass.equals("int")) {  
                                    int temp = Integer.parseInt(cell  
                                            .getStringCellValue());  
                                    setMethod.invoke(tObject, temp);  
                                }  
                                break;  
                            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                                Boolean boolname = true;  
                                if (cell.getStringCellValue().equals("否")) {  
                                    boolname = false;  
                                }  
                                setMethod.invoke(tObject, boolname);  
                                break;  
                            case HSSFCell.CELL_TYPE_FORMULA: // 公式  
                                System.out.print(cell.getCellFormula() + "   ");  
                                break;  
                            case HSSFCell.CELL_TYPE_BLANK: // 空值  
                                System.out.println(" ");  
                                break;  
                            case HSSFCell.CELL_TYPE_ERROR: // 故障  
                                System.out.println(" ");  
                                break;  
                            default:  
                                System.out.print("未知类型   ");  
                                break;  
                            }  
                        } catch (Exception e) {// 转换出错  
                            e.printStackTrace();  
  
                        }  
                    }  
                    // 下一列  
                    k = k + 1;  
                }  
                dist.add(tObject);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            // 将异常抛出去  
            throw e;  
        }  
        return dist;  
    }  
  
    // 格式化日期  
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
  
    /** 
     * 导出到excel中去, 
     *  
     * @author Administrator 
     * @param title 
     *            excel的工作表名 
     * @param fileName 
     *            excel 文件名 
     * @param dateset 
     *            导出的数据集合 
     * @param out 
     *            输出流 
     * @throws IOException 
     */  
    @SuppressWarnings({ "static-access" })  
    public void exportExcel(String title, String fileName, List<T> dataset, String totalMoney,  
            HttpServletResponse response) throws IOException {  
        OutputStream out = response.getOutputStream();// 取得输出流  
        response.reset();// 清空输出流  
        response.setContentType("application/ms-excel;charset=GB2312");  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(fileName.getBytes("gb2312"), "iso8859-1") + ".xls");  
        // 声明一个工作薄  
        try {  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            // 首先检查数据看是否是正确的  
            if (dataset == null || dataset.size() == 0 || title == null  
                    || out == null) {  
                throw new Exception("传入的数据不对！");  
            }  
            // 取得实际泛型类  
            T ts = (T) dataset.get(0);  
            Class<?> tCls = ts.getClass();  
            // 生成一个表格  
            HSSFSheet sheet = workbook.createSheet(title);  
            // 设置表格默认列宽度为15个字节  
            sheet.setDefaultColumnWidth(15);  
            // 生成一个样式  
            HSSFCellStyle style = workbook.createCellStyle();  
            // 设置标题样式  
            style = this.setHeadStyle(workbook, style);  
  
            // 得到所有字段  
  
            Field filed[] = ts.getClass().getDeclaredFields();  
            // 标题  
            List<String> exportfieldtile = new ArrayList<String>();  
            // 导出的字段的get方法  
            List<Method> methodObj = new ArrayList<Method>();  
            // 遍历整个filed  
            for (int i = 0; i < filed.length; i++) {  
                Field f = filed[i];  
                ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);  
                // 如果设置了annottion  
                if (exa != null) {  
                    String exprot = exa.exportName();  
                    // 添加到标题  
                    exportfieldtile.add(exprot);  
                    // 添加到需要导出的字段的方法  
                    String fieldname = f.getName();  
                    String getMethodName = "get"  
                            + fieldname.substring(0, 1).toUpperCase()  
                            + fieldname.substring(1);  
  
                    Method getMethod = tCls.getMethod(getMethodName,  
                            new Class[] {});  
  
                    methodObj.add(getMethod);  
                }  
            }  
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, exportfieldtile  
                    .size() - 1));  
            HSSFRow titleRow = sheet.createRow(0);  
            HSSFCell titleCell = titleRow.createCell(0);  
            titleCell.setCellValue(title);  
            titleCell.setCellStyle(this.setTitleStyle(workbook,  
                    workbook.createCellStyle()));  
            // 产生表格标题行  
            HSSFRow row = sheet.createRow(1);  
            for (int i = 0; i < exportfieldtile.size(); i++) {  
                HSSFCell cell = row.createCell(i);  
                cell.setCellStyle(style);  
                HSSFRichTextString text = new HSSFRichTextString(  
                        exportfieldtile.get(i));  
                cell.setCellValue(text);  
            }  
  
            int index = 1;  
  
            // 循环整个list  
            for (int j = 0; j < dataset.size(); j++) {  
                // 从第二行开始写，第一行是标题  
                T t = (T) dataset.get(j);  
                row = sheet.createRow(index + 1);  
                for (int k = 0; k < methodObj.size(); k++) {  
                    HSSFCell cell = row.createCell(k);  
                    Method getMethod = methodObj.get(k);  
                    Object value = getMethod.invoke(t, new Object[] {});  
                    String textValue = getValue(value);  
                    cell.setCellValue(textValue);  
                }  
                index++;  
            }  
              
            //总的项目笔数  
            HSSFRow totalNumRow = sheet.createRow(index + 2);  
            HSSFCell totalNumCellTitle = totalNumRow.createCell(0);  
            totalNumCellTitle.setCellStyle(setStatStyle(workbook, workbook.createCellStyle()));  
            totalNumCellTitle.setCellValue("项目笔数");  
            HSSFCell totalNumCell = totalNumRow.createCell(1);  
            totalNumCell.setCellStyle(setStatStyle(workbook, workbook.createCellStyle()));  
            totalNumCell.setCellValue(dataset.size());  
              
            //总金额  
            HSSFRow totalMoneyRow = sheet.createRow(index + 3);  
            HSSFCell totalMoneyCellTitle = totalMoneyRow.createCell(0);  
            totalMoneyCellTitle.setCellStyle(setStatStyle(workbook, workbook.createCellStyle()));  
            totalMoneyCellTitle.setCellValue("总金额");  
            HSSFCell totalMoneyCell = totalMoneyRow.createCell(1);  
            totalMoneyCell.setCellStyle(setStatStyle(workbook, workbook.createCellStyle()));  
            totalMoneyCell.setCellValue(totalMoney + "万元");  
            workbook.write(out);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    public String getValue(Object value) {  
        String textValue = "";  
        if (value == null)  
            return textValue;  
  
        if (value instanceof Boolean) {  
            boolean bValue = (Boolean) value;  
            textValue = "是";  
            if (!bValue) {  
                textValue = "否";  
            }  
        } else if (value instanceof Date) {  
            Date date = (Date) value;  
  
            textValue = sdf.format(date);  
        } else  
            textValue = value.toString();  
  
        return textValue;  
    }  
  
    /** 
     * 初始化导出的excel标题的样式 
     * */  
    public static HSSFCellStyle setTitleStyle(HSSFWorkbook workbook,  
            HSSFCellStyle style) {  
  
        style.setFillForegroundColor(HSSFColor.WHITE.index);    
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);    
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);    
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);    
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        // 生成字体    
        HSSFFont font = workbook.createFont();    
        font.setColor(HSSFColor.BLACK.index);    
        font.setFontHeightInPoints((short) 16);    
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    
        // 把字体应用到当前的样样式    
        style.setFont(font);    
        return style;  
  
    }  
      
    /** 
     * 统计部分的样式 
     * @param workbook 
     * @param style 
     * @return 
     */  
    public static HSSFCellStyle setStatStyle(HSSFWorkbook workbook,  
            HSSFCellStyle style) {  
          
        style.setFillForegroundColor(HSSFColor.WHITE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_NONE);  
        style.setBorderLeft(HSSFCellStyle.BORDER_NONE);  
        style.setBorderRight(HSSFCellStyle.BORDER_NONE);  
        style.setBorderTop(HSSFCellStyle.BORDER_NONE);  
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);  
        // 生成字体  
        HSSFFont font = workbook.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样样式  
        style.setFont(font);  
        return style;  
          
    }  
  
    /** 
     * 初始化导出的excel样式 
     * */  
    public static HSSFCellStyle setHeadStyle(HSSFWorkbook workbook,  
            HSSFCellStyle style) {  
  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);    
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);    
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);    
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);    
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        // 生成字体    
        HSSFFont font = workbook.createFont();    
        font.setColor(HSSFColor.VIOLET.index);    
        font.setFontHeightInPoints((short) 12);    
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    
        // 把字体应用到当前的样样式    
        style.setFont(font);    
        return style;    
  
    }  
  
    public static HSSFCellStyle setbodyStyle(HSSFWorkbook workbook,  
            HSSFCellStyle style2) {  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);    
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);    
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);    
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);    
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);    
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    
        // 生成字体    
        HSSFFont font2 = workbook.createFont();    
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);    
        // 把字体应用到当前的样样式    
        style2.setFont(font2);    
        return style2;  
    }  
}  

