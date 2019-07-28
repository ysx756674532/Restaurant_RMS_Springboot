package com.yzc.shixun.controller;

import com.mysql.cj.xdevapi.Table;
import com.yzc.shixun.dto.*;
import com.yzc.shixun.model.RestCMInfos;
import com.yzc.shixun.result.ResultEnum;
import com.yzc.shixun.result.ResultInfo;
import com.yzc.shixun.result.ReturnResult;
import com.yzc.shixun.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class RestController {

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    UserService userService;

    @Autowired
    RestService restService;

    @Autowired
    CommentService commentService;


    @Autowired
    FoodService foodService;

    @Autowired
    TableService tableService;
    @GetMapping("/restIndex")
    public String showRestIndex(Model model)
    {
        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            RestInfo restInfo = restService.selectByUserId(userInfo.getId());
            model.addAttribute("restInfo",restInfo);
        }else {
            model.addAttribute("error","系统错误");
        }
        return "restIndex";
    }

    @GetMapping("/custRestInfos")
    public String showRestInfos(Model model)
    {
        model.addAttribute("restInfos",restService.getRestInfos());
        return "custRestInfos";
    }

    @GetMapping("/restInforById")
    public String showRestInfos(@RequestParam("restId") Integer id, Model model)
    {

        model.addAttribute("restId",id);
        model.addAttribute("foodInfos",restService.getFoodsById(id));
        return "custRestInfoById";
    }

    @GetMapping("/restFoodInfos")
    public String getFoodInfos(Model model)
    {
        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            RestInfo restInfo = restService.selectByUserId(userInfo.getId());
            List<FoodInfo> foodsById = foodService.getFoodsById(restInfo.getId());
            model.addAttribute("foodLists",foodsById);
            model.addAttribute("restId",restInfo.getId());
        }
        return "restFoodInfos";
    }

    @PostMapping("/deleteFood")
    @ResponseBody
    public ReturnResult deleteFoods(String foodId,Integer restId)
    {
        List<String> foodIds= Arrays.asList(foodId.split(","));

        try {
            foodService.deleteFoods(foodIds,restId);
            return new ReturnResult(true,new ResultInfo(ResultEnum.CANCEL_FOODINFOS_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnResult(new ResultInfo(ResultEnum.SYSTEM_FAIL));
        }
    }


    @PostMapping("/addFoodInfo")
    @ResponseBody
    public ReturnResult addFood(String foodName,String unitPrice,String category,String intro,Integer restId)
    {
        FoodInfo foodInfo=new FoodInfo();
        try{
            foodInfo.setFoodName(foodName);
            foodInfo.setFoodPrice(Integer.valueOf(unitPrice));
            foodInfo.setFoodIntro(intro);
            foodInfo.setFoodType(category);
            foodInfo.setRestId(restId);

            foodService.addFood(foodInfo);
            return new ReturnResult(true,new ResultInfo(ResultEnum.ADD_FOOD_SUCCESS));
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ReturnResult(new ResultInfo(ResultEnum.SYSTEM_FAIL));
        }
    }

    @GetMapping("/restTableInfo")
    public String getTableInfo(Model model)
    {
        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            RestInfo restInfo = restService.selectByUserId(userInfo.getId());
            List<TableInfo> list = tableService.getList(restInfo.getId());
            model.addAttribute("tableList",list);
            model.addAttribute("restId",restInfo.getId());
        }
        return "restTableInfo";
    }

    @PostMapping("/addTableInfo")
    @ResponseBody
    public ReturnResult addTable(Integer tableId,Integer peopleNum,String position,Integer restId)
    {

        TableInfo tableInfo=new TableInfo();
        tableInfo.setTableId(tableId);
        tableInfo.setTableCapa(peopleNum);
        tableInfo.setTablePosition(position);
        tableInfo.setRestId(restId);

        boolean b = tableService.addTable(tableInfo);
        if(b) return new ReturnResult(true,new ResultInfo(ResultEnum.ADD_TABLE_SUCCESS));
        else return new ReturnResult(new ResultInfo(ResultEnum.ADD_TABLE_FAIL));
    }

    @GetMapping("/getRestComments")
    public String getRestComments(Model model)
    {
        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            RestInfo restInfo = restService.selectByUserId(userInfo.getId());
            List<RestCMInfos> restCommentInfos = commentService.getRestCommentInfos(restInfo.getId());
            model.addAttribute("reseCommments",restCommentInfos);
        }
        return "restComments";
    }
}
