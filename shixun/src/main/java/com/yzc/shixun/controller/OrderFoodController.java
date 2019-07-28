package com.yzc.shixun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzc.shixun.model.OrderFoodInfo;
import com.yzc.shixun.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderFoodController {
    @Autowired
    OrderFoodService orderFoodService;

    @GetMapping("/getOrderFoodInfos")
    @ResponseBody
    public PageInfo getOrderFoodInfos(@RequestParam(value = "pn",defaultValue = "1")Integer pageNum, Integer orderId)
    {
        PageHelper.startPage(pageNum,5);
        List<OrderFoodInfo> orderFoodInfos = orderFoodService.selectByOrderId(orderId);
        PageInfo pageInfo = new PageInfo(orderFoodInfos,5);
        return  pageInfo;
    }
}
