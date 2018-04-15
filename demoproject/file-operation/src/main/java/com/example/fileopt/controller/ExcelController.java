package com.example.fileopt.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.fileopt.entity.table.User;
import com.example.fileopt.service.IUserService;
import com.example.fileopt.util.ExcelUtils;
import com.example.fileopt.util.ExportHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/file")
public class ExcelController {

    @Autowired
    private IUserService userService;

    @RequestMapping("excelPage")
    public ModelAndView excelPage(User user) {
        return new ModelAndView("excel");
    }

    @RequestMapping(value = "importExcelDate")
    public void queryExcelDate(DefaultMultipartHttpServletRequest multipartRequest,
                               HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        MultipartFile multipartFile = multipartRequest.getFile("excelFile");
        PrintWriter out = response.getWriter();
        List<String[]> paramList = new ArrayList<String[]>();
        if (multipartFile != null && !multipartFile.isEmpty()) {
            if (multipartFile.getSize() > 5000000) {
                out.write("上传失败：文件大小不能超过5M");
                return;
            }
            ExcelUtils excelData = new ExcelUtils(multipartFile.getInputStream());
            paramList = excelData.getDatasFromSheet(0, true);
            // 删除第一行 (标题栏)
            paramList.remove(0);
            for (String[] obj : paramList) {
                System.out.println(obj[1]);
            }
            out.write("上传成功");
        }
    }

    @RequestMapping("dataStatus")
    public void skuDataStatus(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> msgMap = new HashMap<String, String>();
        msgMap.put("msg", "");
        /*if("".equals("N")){
			msgMap.put("msg", "");
		}else{
			msgMap.put("msg", "后台有数据正在同步，请稍后导出...");
		}*/
        PrintWriter out = null;
        try {
            JSONObject jsonArray = JSONObject.fromObject(msgMap);
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.write(jsonArray.toString());
        } catch (Exception e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @ResponseBody
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            List<User> listUser = userService.list(new User());
            List<Object[]> list = new ArrayList<Object[]>();
            for (User u : listUser) {
                Object[] obj = new Object[4];
                obj[0] = u.getName();
                obj[1] = u.getPassword();
                obj[2] = u.getEnabledFlag();
                obj[3] = u.getCreateTime();
                list.add(obj);
            }
            ExportHelper exportHelper = new ExportHelper();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sheetName = sdf.format(new Date());
            String fileRealPath = request.getSession().getServletContext().getRealPath("template/");
            // 模板路径
            String templatePath = fileRealPath + "/exportExcel.xls";
            // {(开始行),(总列数)}
            int[] paras = {1, 4};
            // 数值列 {0,1,2,3,....}
            int[] numCol = {};
            // "合计"标题 {(开始列),(结束列)}
            //int[] amountCol = null;
            // 指定索引值(0,3)....
            Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
            indexMap.put(0, 0);
            indexMap.put(1, 1);
            indexMap.put(2, 2);
            indexMap.put(3, 3);
            exportHelper.doExportLocalAsync(list,  templatePath, 
            		"exportExcel.xls", sheetName, paras, numCol) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
