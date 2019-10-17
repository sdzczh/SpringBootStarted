package com.zh.module.controller;

import com.zh.module.service.TestService;
import com.zh.module.utils.DateUtils;
import com.zh.module.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhaohe
 * @create: 2019-10-17 18:53
 */
@Controller
@RequestMapping(value = "")
public class TestController {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "")
    public String index(){
        return "success-------" + DateUtils.getCurrentDateStr() + "---------" + testService.getName(1);
    }
    @RequestMapping(value = "index")
    public String index1(Model model){
        model.addAttribute("test", "test");
        return "index";
    }
}
