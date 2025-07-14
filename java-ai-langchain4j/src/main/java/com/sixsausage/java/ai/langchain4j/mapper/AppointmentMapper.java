package com.sixsausage.java.ai.langchain4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sixsausage.java.ai.langchain4j.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
    @Select("SELECT a.*, d.id AS doctor_id, d.name AS doctor_name, d.position AS doctor_position, d.specialty AS doctor_specialty, d.description AS doctor_description " +
            "FROM appointment a " +
            "LEFT JOIN doctor d ON a.doctor_id = d.id " +
            "WHERE a.username = #{username} OR a.id_card = #{idCard}")
    List<Appointment> selectByUserOrIdCard(@Param("username") String username, @Param("idCard") String idCard);
}