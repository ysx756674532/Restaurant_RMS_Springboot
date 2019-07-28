package com.yzc.shixun.dao;

import com.yzc.shixun.dto.CommentInfo;
import com.yzc.shixun.model.CustCMInfos;
import com.yzc.shixun.model.RestCMInfos;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface CommentMapper {


    @InsertProvider(type = CommentSql.class,method = "add")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertComment(CommentInfo commentInfo);


    @Select("SELECT commentinfo.id,order_id,comment_info,comment_time,comment_type,rest_name from commentinfo,orderinfo,restinfo " +
            "where orderinfo.cust_id=#{custId} and orderinfo.rest_id=restinfo.id and commentinfo.order_id=orderinfo.id order by order_id")
    List<CustCMInfos> selectInfosCust(Integer custId);

    @Select("SELECT commentinfo.id,order_id,comment_info,comment_time,comment_type,cust_name from commentinfo,orderinfo,custinfo " +
            "where orderinfo.rest_id=#{restId} and orderinfo.cust_id=custinfo.id and commentinfo.order_id=orderinfo.id order by order_id")
    List<RestCMInfos> selectInfosRest(Integer restId);

    @Delete("delete ignore from commentinfo where id=#{comment}")
    int delete(Integer comment);

    @Select("select order_id from commentinfo where id=#{id}")
    int selectByCommentId(Integer id);


    class CommentSql
    {
        public String add(CommentInfo commentInfo)
        {
            return "insert into commentinfo(order_id,comment_type,comment_time,comment_info) values(" +
                    ""+commentInfo.getOrderId()+",'"+commentInfo.getCommentType()+"','"+new Timestamp(commentInfo.getCommentTime().getTime())+"','"+commentInfo.getCommentInfo()+"')";

        }
    }
}
