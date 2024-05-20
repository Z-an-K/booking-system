package com.dxc.meetingroomreservationsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxc.meetingroomreservationsystem.pojo.Event;
import com.dxc.meetingroomreservationsystem.pojo.EventDto;
import com.dxc.meetingroomreservationsystem.pojo.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper extends BaseMapper<Event> {

    @Select("select te.*,tu.username ,tm.location ,tm.room_id,tm.color as background_color  from tb_user_book_event tube \n" +
            "\tleft join tb_users tu on tube.user_id =tu.id \n" +
            "\tleft join tb_event te on te.id = tube.event_id \n" +
            "\tleft join tb_meetingroom tm on tm.id = tube.room_id \n" +
            "\twhere te.`start` >= #{start} and te.`end` <= #{end}")
    List<EventDto> getEventsByRange(@Param("start") String start, @Param("end") String end);

    @Select({
            "select te.*,tu.username ,tm.location ,tm.room_id,tm.color as background_color  from tb_user_book_event tube \n" +
                    "\tleft join tb_users tu on tube.user_id =tu.id \n" +
                    "\tleft join tb_event te on te.id = tube.event_id \n" +
                    "\tleft join tb_meetingroom tm on tm.id = tube.room_id \n" +
                    "\twhere tu.id = #{userId} \n"+
                    "\tAND (IFNULL(#{location}, '') = '' OR tm.location = #{location}) " +
                    "\tAND (IFNULL(#{roomNumber}, '') = '' OR tm.room_id = #{roomNumber}) "
    })
    List<EventDto> getEventByUser(@Param("userId")int userId,@Param("location")String location,@Param("roomNumber")String roomNumber);
    @Insert("insert into tb_user_book_event (user_id,event_id,room_id) values(#{userId},#{eventId},#{roomId}) ")
    boolean saveRecord(@Param("userId") int userId, @Param("eventId") int eventId, @Param("roomId") int roomId);

    @Insert({
            "<script>",
            "insert into tb_user_book_event (user_id, event_id, room_id) values",
            "<foreach item='record' collection='list' separator=','>",
            "(#{record.userId}, #{record.eventId}, #{record.roomId})",
            "</foreach>",
            "</script>"
    })
    boolean saveRecords(@Param("list") List<Record> list);

    @Insert({
            "<script>",
            "INSERT INTO tb_event (title, `start`, `end`, details, recurrence, recurrence_pattern, `from`, `to`, week, weekth, create_by, create_time, update_by, update_time,group_id)",
            "VALUES ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.title}, #{item.start}, #{item.end}, #{item.details}, #{item.recurrence}, #{item.recurrencePattern}, #{item.from}, #{item.to}, #{item.week}, #{item.weekth}, #{item.createBy}, #{item.createTime}, #{item.updateBy}, #{item.updateTime},#{item.groupId})",
            "</foreach>",
            "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "list.id")
    int insertBatch(@Param("list") List<Event> list);

    @Select({
            "<script>",
            "SELECT  id",
            "FROM tb_event  WHERE  (group_id = #{groupId})",
            "</script>"
    })
    List<Integer> getEventIdByGroupId(String groupId);
    @Delete({
            "<script>",
            "DELETE FROM tb_user_book_event",
            "WHERE event_id IN",
            "<foreach item='item' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    int deleteByEventIds(List<Integer> list);


}
