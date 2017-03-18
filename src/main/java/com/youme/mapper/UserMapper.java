package com.youme.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.youme.pojo.UserInfo;

public interface UserMapper {

	@Select("SELECT id,account,name FROM user_info WHERE account=#{account} AND password=#{password}")
	UserInfo login(@Param("account") String account, @Param("password") String password);
}
