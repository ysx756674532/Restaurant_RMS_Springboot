package com.yzc.shixun.controller;

import com.yzc.shixun.dto.*;
import com.yzc.shixun.model.CustOrderInfo;
import com.yzc.shixun.result.ResultEnum;
import com.yzc.shixun.result.ResultInfo;
import com.yzc.shixun.result.ReturnResult;
import com.yzc.shixun.service.Custervice;
import com.yzc.shixun.service.OrderService;

import com.yzc.shixun.service.RestService;
import com.yzc.shixun.service.TableService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    Custervice custervice;

    @Autowired
    OrderService orderService;

    @Autowired
    RestService restService;

    @Autowired
    TableService tableService;
    @GetMapping("/restOrderInfos")
    public String showRestOrderInfos(Model model){

        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            RestInfo restInfo = restService.selectByUserId(userInfo.getId());

            List<CustOrderInfo> custOrderInfos = orderService.getRestOrderInfos(restInfo.getId());
            for(CustOrderInfo custOrderInfo:custOrderInfos)
            {
                if(custOrderInfo.getOrderState().equals("预定中"))
                {
                    Date date=new Date();

                    if((date.getTime()-custOrderInfo.getStartTime().getTime()) >= 7200000)
                    {
                        try {
                            orderService.setState(custOrderInfo.getOrderid(),"已完成");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        custOrderInfo.setOrderState("已完成");
                    }
                }
            }

            model.addAttribute("custOrderInfos",custOrderInfos);
        }
        return "restOrderInfos";
    }


    @GetMapping("/custOrderInfos")
    public String showCustOrderInfos(Model model){

        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            CustInfo custInfo = custervice.selectById(userInfo.getId());
            List<CustOrderInfo> custOrderInfos = orderService.getCustOrderInfos(custInfo.getId());
            for(CustOrderInfo custOrderInfo:custOrderInfos)
            {
                if(custOrderInfo.getOrderState().equals("预定中"))
                {
                    Date date=new Date();

                    if((date.getTime()-custOrderInfo.getStartTime().getTime()) >= 7200000)
                    {
                        try {
                            orderService.setState(custOrderInfo.getOrderid(),"已完成");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        custOrderInfo.setOrderState("已完成");
                    }
                }
            }

            model.addAttribute("custOrderInfos",custOrderInfos);
        }
        return "custOrderInfos";
    }


    @PostMapping("/addOrder")
    @ResponseBody
    public ReturnResult addOrder(String foodList, Integer restId, String date, Integer peopleNum)
    {
        DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(date);
        Date startTime;
        try {
            startTime=simpleDateFormat.parse(date);
            System.out.println(startTime.toString());
        } catch (ParseException e) {
           return new ReturnResult(new ResultInfo(ResultEnum.TIME_FORMAT_FAIL));
        }
        Integer tableId = tableService.getTableIdByTime(restId, startTime, peopleNum);
        if(tableId==-1)
        {
            return new ReturnResult(new ResultInfo(ResultEnum.ADD_ORDER_FAIL));
        }

        UserInfo userInfo = (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        CustInfo custInfo=custervice.selectById(userInfo.getId());
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setCustId(custInfo.getId());
        orderInfo.setPeopleNum(peopleNum);
        orderInfo.setRestId(restId);
        orderInfo.setTableId(tableId);
        orderInfo.setStartTime(startTime);

        JSONArray foodJson=JSONArray.fromObject(foodList);
        List<OrderFood> orderFoods=JSONArray.toList(foodJson, new OrderFood(), new JsonConfig());

        try {
            orderService.addOrder(orderInfo, orderFoods);
            return new ReturnResult(true,new ResultInfo(ResultEnum.ADD_ORDER_SUCCESS));
        } catch (Exception e) {
            return new ReturnResult(new ResultInfo(ResultEnum.SYSTEM_FAIL));
        }
    }

    @PostMapping("/cancelOrder")
    @ResponseBody
    public ReturnResult cancelOrder(Integer orderId)
    {
        try {
            orderService.cancelOrder(orderId);
            return new ReturnResult(true,new ResultInfo(ResultEnum.CANCEL_ORDER_SUCCESS));
        } catch (Exception e) {
            return new ReturnResult(new ResultInfo(ResultEnum.SYSTEM_FAIL));
        }
    }
}
