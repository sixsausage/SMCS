package com.sixsausage.java.ai.langchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sixsausage.java.ai.langchain4j.entity.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleService extends IService<Schedule> {
    List<Schedule> getByDateAndTimeSlot(Date date, String timeSlot);
    Schedule getByDoctorIdAndDateAndTimeSlot(Long doctorId, Date date, String timeSlot);
}