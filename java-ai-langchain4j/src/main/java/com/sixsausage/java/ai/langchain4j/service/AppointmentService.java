package com.sixsausage.java.ai.langchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sixsausage.java.ai.langchain4j.entity.Appointment;
import com.sixsausage.java.ai.langchain4j.entity.Doctor;

import java.util.List;

public interface AppointmentService extends IService<Appointment> {
        Appointment getOne(Appointment appointment);
        List<Appointment> getByUsernameOrIdCard(String username, String idCard);
        List<Doctor> getDoctors();
        Doctor getDoctorById(Long id);
        Doctor queryDoctorByName(String name);
        List<Doctor> queryDoctorsByDepartment(String department);
}