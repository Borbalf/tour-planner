package com.tourplanner.demo.mapper;

import com.tourplanner.demo.model.Itinerary;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ItineraryMapper {

    @Select("SELECT * FROM Itinerary WHERE ID = #{ID}")
    Itinerary findByID(@Param("ID") Long ID);

    @Select("SELECT * FROM Itinerary")
    List<Itinerary> findAll();

    @Insert("INSERT INTO Itinerary (userID, statusID, description, startDate, endDate) VALUES ( #{userID} , #{statusID} , #{description} , #{startDate} , #{endDate} )")
    int insertItinerary(Long ID, Long userID, Long statusID, String description, Date startDate, Date endDate);

    @Update("UPDATE Itinerary SET userID = #{userID} , statusID = #{statusID} , description = #{description} , startDate = #{startDate} , endDate = #{endDate} WHERE ID = #{ID} ")
    int updateItinerary(Long ID, Long userID, Long statusID, String description, Date startDate, Date endDate);

    @Delete("DELETE FROM Itinerary WHERE ID = #{ID}")
    int deleteItinerary(Long ID);

    @Select("SELECT ID FROM Itinerary ORDER BY ID DESC LIMIT 1")
    Long findLatestID();
}
