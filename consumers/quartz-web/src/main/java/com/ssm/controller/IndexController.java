package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.base.BaseController;

@Controller
public class IndexController extends BaseController
{

    @RequestMapping("index")
    public String index(ModelMap map) throws Exception
    {
        return "main";
    }
    
}
