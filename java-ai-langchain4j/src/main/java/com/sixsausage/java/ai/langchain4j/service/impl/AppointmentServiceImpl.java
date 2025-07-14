package com.sixsausage.java.ai.langchain4j.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sixsausage.java.ai.langchain4j.entity.Appointment;
import com.sixsausage.java.ai.langchain4j.entity.Doctor;
import com.sixsausage.java.ai.langchain4j.mapper.AppointmentMapper;
import com.sixsausage.java.ai.langchain4j.mapper.DoctorMapper;
import com.sixsausage.java.ai.langchain4j.mapper.ScheduleMapper;
import com.sixsausage.java.ai.langchain4j.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
/**
* 查询订单是否存在
* @param appointment
* @return
*/
    @Override
    public Appointment getOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
        queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
        queryWrapper.eq(Appointment::getDepartment, appointment.getDepartment());
        queryWrapper.eq(Appointment::getDate, appointment.getDate());
        queryWrapper.eq(Appointment::getTime, appointment.getTime());
        Appointment appointmentDB = baseMapper.selectOne(queryWrapper);
        return appointmentDB;
    }

    /**
     * 根据用户名或身份证号查询预约信息
     * @param username
     * @param idCard
     * @return
     */
    @Override
    public List<Appointment> getByUsernameOrIdCard(String username, String idCard) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            queryWrapper.eq(Appointment::getUsername, username);
        }
        if (idCard != null && !idCard.isEmpty()) {
            queryWrapper.or().eq(Appointment::getIdCard, idCard);
        }
        return list(queryWrapper);
    }
    @Autowired
    private DoctorMapper doctorMapper;
    /**
     * 获取所有医生信息
     * @return
     */
    @Override
    public List<Doctor> getDoctors() {
        return doctorMapper.selectList(null);
    }

    /**
     * 根据ID获取医生信息
     * @param id
     * @return
     */
    @Override
    public Doctor getDoctorById(Long id) {
        return doctorMapper.selectById(id);
    }
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Override
    public Doctor queryDoctorByName(String name) {
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doctor::getName, name);
        return doctorMapper.selectOne(queryWrapper);
    }

    /**
     * 根据科室查询医生信息
     * @param department
     * @return
     */
    @Override
    public List<Doctor> queryDoctorsByDepartment(String department) {
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Doctor::getDescription, department);
        return doctorMapper.selectList(queryWrapper);
    }
}