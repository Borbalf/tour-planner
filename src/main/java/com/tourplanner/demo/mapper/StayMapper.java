package com.tourplanner.demo.mapper;

import com.tourplanner.demo.model.Stay;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface StayMapper {

    @Select("SELECT * FROM Stay WHERE ID = #{ID}")
    Stay findByID(@Param("ID") Long ID);

    @Select("SELECT * FROM Stay")
    List<Stay> findAll();

    @Select("SELECT * FROM Stay WHERE cityID = #{cityID} ")
    List<Stay> findAllByCityID(@Param("cityID") Long cityID);

    @Select("SELECT * FROM Stay WHERE itineraryID = #{itineraryID} ")
    List<Stay> findAllByItineraryID(@Param("itineraryID") Long itineraryID);

    @Insert("INSERT INTO Stay (itineraryID, cityID, description, stayDate) VALUES ( #{itineraryID} , #{cityID} , #{description} , #{stayDate} )")
    int insertStay(Long itineraryID, Long cityID, String description, Date stayDate);

    @Update("UPDATE Stay SET itineraryID = #{itineraryID} , cityID = #{cityID} , description = #{description} , stayDate = #{stayDate} WHERE ID = #{ID} ")
    int updateStay(Long ID, Long itineraryID, Long cityID, String description, Date stayDate);

    @Delete("DELETE FROM Stay WHERE ID = #{ID}")
    int deleteStay(Long ID);

    @Delete("DELETE FROM Stay WHERE itineraryID = #{itineraryID}")
    int deleteStayByItineraryID(@Param("itineraryID") Long itineraryID);

    @Select("SELECT ID FROM Stay ORDER BY ID DESC LIMIT 1")
    Long findLatestID();
}
