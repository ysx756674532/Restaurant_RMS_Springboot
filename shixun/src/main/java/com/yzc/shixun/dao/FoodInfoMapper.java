package com.yzc.shixun.dao;

import com.yzc.shixun.dto.FoodInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FoodInfoMapper {

    @Select("select * from foodinfo where rest_id=#{id}")
    List<FoodInfo> selectById(Integer id);

    @Delete("delete from foodinfo where id=#{id} and rest_id=#{restId}")
    int delete(@Param("id") Integer id,@Param("restId") Integer restId);

    @Insert("insert into foodinfo(food_name,food_price,food_type,food_intro,rest_id) values(#{foodName},#{foodPrice},#{foodType},#{foodIntro},#{restId})")
    int insert(FoodInfo foodInfo);
}
