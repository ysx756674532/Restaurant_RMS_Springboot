package com.yzc.shixun.dao;

import com.yzc.shixun.dto.TableInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface TableMapper {

    @Select("select * from tableinfo where rest_id=1 and table_id not in (select table_id from orderinfo where order_state='预定中' and (#{time} between TIMESTAMPADD(HOUR,-2,start_time) and TIMESTAMPADD(HOUR,2,start_time))) order by table_capa")
    List<TableInfo> selectListByTime(@Param("restId") Integer restId,@Param("time") Timestamp time);


    @Select("select * from tableinfo where rest_id=#{restId}")
    List<TableInfo> selectListById(Integer restId);

    @Insert("insert ignore into tableinfo(table_id,table_capa,table_position,rest_id) values(#{tableId},#{tableCapa},#{tablePosition},#{restId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertTableInfo(TableInfo tableInfo);
}
