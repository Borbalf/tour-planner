package com.tourplanner.demo.mapper;

import com.tourplanner.demo.model.City;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CityMapper {

    @Select("SELECT * FROM City WHERE ID = #{ID}")
    City findByID(@Param("ID") Long ID);

    @Select("SELECT * FROM City WHERE name = #{name}")
    City findByName(@Param("name") String name);

    @Select("SELECT * FROM City")
    List<City> findAll();

    @Insert("INSERT INTO City (name, latitude, longitude, altitude) VALUES ( #{name} , #{latitude} , #{longitude} , #{altitude} )")
    int insertCity(String name, BigDecimal latitude, BigDecimal longitude, BigDecimal altitude);

    @Update("UPDATE City SET name = #{name} , latitude = #{latitude} , longitude = #{longitude} , altitude = #{altitude} WHERE ID = #{ID} ")
    int updateCity(String name, BigDecimal latitude, BigDecimal longitude, BigDecimal altitude, Long ID);

    @Delete("DEKETE FROM City WHERE ID = #{ID}")
    int deleteCity(Long ID);
}
