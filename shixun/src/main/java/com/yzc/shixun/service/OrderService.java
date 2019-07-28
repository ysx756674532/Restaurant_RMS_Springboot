package com.yzc.shixun.service;

import com.yzc.shixun.dao.OrderFoodMapper;
import com.yzc.shixun.dao.OrderMapper;
import com.yzc.shixun.dto.OrderFood;
import com.yzc.shixun.dto.OrderInfo;
import com.yzc.shixun.model.CustOrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderFoodMapper orderFoodMapper;

    public List<CustOrderInfo> getCustOrderInfos(Integer custId)
    {
        List<CustOrderInfo> custOrderInfos = orderMapper.selectByCustID(custId);
        return custOrderInfos;
    }

    public List<CustOrderInfo> getRestOrderInfos(Integer restId)
    {
        List<CustOrderInfo> custOrderInfos = orderMapper.selectByRestID(restId);
        return custOrderInfos;
    }

    public boolean setState(Integer orderId,String state) throws SQLException
    {
        int i = orderMapper.setState(state, orderId);
        if(i>0)return true;
        else return false;
    }

    @Transactional
    public void addOrder(OrderInfo orderInfo, List<OrderFood> orderFoods) throws Exception {
        int orderResult = orderMapper.insertOrder(orderInfo);
        if(orderResult<=0)throw new Exception();
        for(OrderFood orderFood:orderFoods)
        {
            orderFood.setOrderId(orderInfo.getId());
            int foodResult = orderFoodMapper.insertFood(orderFood);
            if(foodResult<=0)
            {
                throw new Exception();
            }
        }
    }

    @Transactional
    public boolean cancelOrder(Integer orderId) throws Exception {
        boolean isCancel = setState(orderId, "已取消");
        if(!isCancel)throw new Exception();
        orderFoodMapper.deleteFoodsByOrderId(orderId);
        return true;
    }

    public Integer getRestById(Integer id)
    {
        return orderMapper.selectRestIdByOrderId(id);
    }
}
