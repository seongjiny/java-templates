package com.dev.security.mapper;

import com.dev.security.domain.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM app_users WHERE id = #{id}")
    UserDto findByUserId(String userId);

    @Select("SELECT * FROM app_users")
    List<UserDto> findAll();

    @Insert("INSERT INTO users (user_id, user_name, password) VALUES (#{user_id}, #{user_name}, #{password})")
    int save(UserDto user);
}
