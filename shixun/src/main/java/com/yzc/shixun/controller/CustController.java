package com.yzc.shixun.controller;

import com.yzc.shixun.dto.CustInfo;
import com.yzc.shixun.dto.UserInfo;
import com.yzc.shixun.service.Custervice;
import com.yzc.shixun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustController {

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    UserService userService;

    @Autowired
    Custervice custervice;

    @GetMapping("/custIndex")
    public String showCustIndex(Model model)
    {
        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            CustInfo custInfo = custervice.selectById(userInfo.getId());
            model.addAttribute("custInfo",custInfo);
        }else {
            model.addAttribute("error","系统错误");
        }
        return "custIndex";
    }
}
