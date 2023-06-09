package com.tourplanner.demo.mapper;

import com.tourplanner.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `User` WHERE ID = #{ID}")
    User findByID(@Param("ID") Long ID);

    @Select("SELECT * FROM `User`")
    List<User> findAll();
}
