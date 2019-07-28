package com.yzc.shixun.dao;

import com.yzc.shixun.dto.CustInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface CustInfoMapper {

    @Insert("insert into custinfo(cust_name,cust_phonenum,cust_flavor,cust_address,user_id) values(#{custName},#{custPhonenum},#{custFlavor},#{custAddress},#{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertCustInfo(CustInfo custInfo);

    @Select("select * from custinfo where user_id=#{id}")
    CustInfo selectByUserId(Integer id);
}
