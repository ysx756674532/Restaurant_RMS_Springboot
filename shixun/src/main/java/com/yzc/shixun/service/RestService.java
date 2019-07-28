package com.yzc.shixun.service;

import com.yzc.shixun.dao.RestInfoMapper;
import com.yzc.shixun.dto.FoodInfo;
import com.yzc.shixun.dto.RestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestService {

    @Autowired
    RestInfoMapper restInfoMapper;

    @Autowired
    FoodService foodService;

    @Autowired
    CommentService commentService;

    public RestInfo selectByUserId(Integer userId)
    {
       return restInfoMapper.selectByUserId(userId);
    }

    public List<RestInfo> getRestInfos()
    {
        List<RestInfo> restInfos = restInfoMapper.selectList();
        return restInfos;
    }

    public List<FoodInfo> getFoodsById(Integer id)
    {
        List<FoodInfo> foodsById = foodService.getFoodsById(id);
        return foodsById;
    }
}
