package com.tourplanner.demo.mapper;

import com.tourplanner.demo.model.WeatherCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WeatherConditionMapper {

    @Select("SELECT * FROM WeatherCondition")
    List<WeatherCondition> findAll();

    @Select("SELECT * FROM WeatherCondition WHERE ID = #{ID}")
    WeatherCondition findByID(@Param("ID") Long ID);

    @Select("SELECT * FROM WeatherCondition WHERE stayID = #{stayID} ")
    WeatherCondition findByStayID(@Param("stayID") Long stayID);

    @Insert("INSERT INTO WeatherCondition (stayID, temperature, humidity, skyConditionID) VALUES ( #{stayID} , #{temperature} , #{humidity} , #{skyConditionID} )")
    int insertWeatherCondition(Long stayID, Double temperature, Double humidity, Long skyConditionID);

    @Delete("DELETE FROM WeatherCondition WHERE ID = #{ID}")
    int deleteWeatherCondition(Long ID);

    @Delete("DELETE FROM WeatherCondition WHERE stayID = #{stayID}")
    int deleteWeatherConditionByStayID(@Param("stayID") Long stayID);
}
