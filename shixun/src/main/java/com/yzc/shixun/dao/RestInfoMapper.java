package com.yzc.shixun.dao;


import com.yzc.shixun.dto.RestInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RestInfoMapper {

    @Select("select * from restinfo where user_id=#{id}")
    RestInfo selectByUserId(Integer id);

    @Select("select * from restinfo")
    List<RestInfo> selectList();

    @Update("update restinfo set praise_rate=#{f} where id=#{restId}")
    int UpdateP(@Param("f") String f,@Param("restId") Integer restId);

}
