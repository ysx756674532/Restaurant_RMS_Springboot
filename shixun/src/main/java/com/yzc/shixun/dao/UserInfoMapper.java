package com.yzc.shixun.dao;

import com.yzc.shixun.dto.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper {

    @Insert("insert ignore into userinfo(user_name,user_image,user_type,password,email) values(#{userName},#{userImage},#{userType},#{password},#{email})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertUserInfo(UserInfo userInfo);

    @Select("select * from userinfo where user_name=#{name} and password=#{password}")
    UserInfo selectByPasswrod(@Param("name") String name,@Param("password") String password);

    @Select("select * from userinfo where user_name=#{name} and user_type=#{userType}")
    UserInfo selectByUserName(@Param("name") String name,@Param("userType") String userType);

    @Select("select * from userinfo where id=#{id} and user_type=#{userType}")
    UserInfo selectByUserId(@Param("id") Integer id,@Param("userType") String userType);

    @Select("Select * from userinfo where email=#{email}")
    UserInfo selectByEmail(String email);
}
