package com.yzc.shixun.dao;

import com.yzc.shixun.dto.OrderFood;
import com.yzc.shixun.model.OrderFoodInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderFoodMapper {

    @Select("select food_name,food_price,remark from orderfood,foodinfo where order_id=#{orderId} and food_id=foodinfo.id")
    List<OrderFoodInfo> selectByOrderId(Integer orderId);

    @Insert("insert into orderfood(order_id,food_id,remark) values(#{orderId},#{foodId},#{remark})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertFood(OrderFood orderFood);

    @Delete("delete from orderfood where order_id=#{orderId}")
    int deleteFoodsByOrderId(Integer orderId);

    @Delete("delete from orderfood where food_id=#{foodId}")
    int deleteFoodsByFoodId(Integer foodId);
}
