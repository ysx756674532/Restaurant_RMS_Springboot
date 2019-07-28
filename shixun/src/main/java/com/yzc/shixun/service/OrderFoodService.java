package com.yzc.shixun.service;

import com.yzc.shixun.dao.OrderFoodMapper;
import com.yzc.shixun.model.OrderFoodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodService {

    @Autowired
    OrderFoodMapper orderFoodMapper;

    public List<OrderFoodInfo> selectByOrderId(Integer orderid)
    {
        return orderFoodMapper.selectByOrderId(orderid);
    }

}
