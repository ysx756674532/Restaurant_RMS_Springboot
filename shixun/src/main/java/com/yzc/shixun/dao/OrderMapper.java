package com.yzc.shixun.dao;

import com.yzc.shixun.dto.OrderInfo;
import com.yzc.shixun.model.CustOrderInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

public interface OrderMapper {

    @Select("select orderinfo.id as orderid,rest_name,people_num,table_id,start_time,order_state,rest_address from restinfo,orderinfo where orderinfo.cust_id=#{custId} and orderinfo.rest_id=restinfo.id order by orderinfo.id desc")
    List<CustOrderInfo> selectByCustID(Integer custId);

    @Select("select orderinfo.id as orderid,rest_name,people_num,table_id,start_time,order_state,rest_address from restinfo,orderinfo where orderinfo.rest_id=#{restId} and orderinfo.rest_id=restinfo.id order by orderinfo.id desc")
    List<CustOrderInfo> selectByRestID(Integer restId);

    @Update("update orderinfo set order_state=#{state} where id=#{order}")
    int setState(@Param("state") String state,@Param("order") Integer order);


    @Insert("insert into orderinfo(cust_id,rest_id,people_num,table_id,start_time) values(#{custId},#{restId},#{peopleNum},#{tableId},#{startTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertOrder(OrderInfo orderInfo);


    @Select("select rest_id from orderinfo where id=#{id}")
    Integer selectRestIdByOrderId(Integer Id);

}
