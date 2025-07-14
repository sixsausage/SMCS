package com.sixsausage.java.ai.langchain4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sixsausage.java.ai.langchain4j.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Select("SELECT s.*, d.id AS doctor_id, d.name AS doctor_name, d.position AS doctor_position, d.specialty AS doctor_specialty, d.description AS doctor_description " +
            "FROM schedule s " +
            "LEFT JOIN doctor d ON s.doctor_id = d.id " +
            "WHERE s.date = #{date} AND s.time_slot = #{timeSlot}")
    List<Schedule> selectByDateAndTimeSlot(@Param("date") Date date, @Param("timeSlot") String timeSlot);

    @Select("SELECT s.*, d.id AS doctor_id, d.name AS doctor_name, d.position AS doctor_position, d.specialty AS doctor_specialty, d.description AS doctor_description " +
            "FROM schedule s " +
            "LEFT JOIN doctor d ON s.doctor_id = d.id " +
            "WHERE s.doctor_id = #{doctorId} AND s.date = #{date} AND s.time_slot = #{timeSlot}")
    Schedule selectByDoctorIdAndDateAndTimeSlot(@Param("doctorId") Long doctorId, @Param("date") Date date, @Param("timeSlot") String timeSlot);
}