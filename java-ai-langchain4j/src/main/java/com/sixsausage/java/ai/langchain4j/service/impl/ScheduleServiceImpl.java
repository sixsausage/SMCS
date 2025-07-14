package com.sixsausage.java.ai.langchain4j.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sixsausage.java.ai.langchain4j.entity.Schedule;
import com.sixsausage.java.ai.langchain4j.mapper.DoctorMapper;
import com.sixsausage.java.ai.langchain4j.mapper.ScheduleMapper;
import com.sixsausage.java.ai.langchain4j.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 根据日期和时间段查询可用的排班信息
     * @param date
     * @param timeSlot
     * @return
     */
    @Override
    public List<Schedule> getByDateAndTimeSlot(Date date, String timeSlot) {
        return baseMapper.selectByDateAndTimeSlot(date, timeSlot);
    }

    /**
     * 根据医生ID、日期和时间段查询具体的排班信息
     * @param doctorId
     * @param date
     * @param timeSlot
     * @return
     */
    @Override
    public Schedule getByDoctorIdAndDateAndTimeSlot(Long doctorId, Date date, String timeSlot) {
        return baseMapper.selectByDoctorIdAndDateAndTimeSlot(doctorId, date, timeSlot);
    }
}