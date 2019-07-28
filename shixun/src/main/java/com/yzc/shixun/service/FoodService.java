package com.yzc.shixun.service;

import com.yzc.shixun.dao.FoodInfoMapper;
import com.yzc.shixun.dao.OrderFoodMapper;
import com.yzc.shixun.dto.FoodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodInfoMapper foodInfoMapper;

    @Autowired
    OrderFoodMapper orderFoodMapper;

    public List<FoodInfo> getFoodsById(Integer id)
    {
        return foodInfoMapper.selectById(id);
    }

    @Transactional
    public boolean deleteFoods(List<String> foodIds,Integer restId)throws Exception
    {
        for(String id:foodIds){
            int delete = foodInfoMapper.delete(Integer.valueOf(id), restId);
            if(delete<=0)throw new Exception();
            int i = orderFoodMapper.deleteFoodsByFoodId(Integer.valueOf(id));
        }
        return true;
    }

    public boolean addFood(FoodInfo foodInfo)
    {
        int insert = foodInfoMapper.insert(foodInfo);
        if(insert>0)return true;
        else return false;
    }
}
