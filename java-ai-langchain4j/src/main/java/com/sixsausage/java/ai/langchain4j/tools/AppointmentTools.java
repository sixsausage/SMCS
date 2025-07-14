package com.sixsausage.java.ai.langchain4j.tools;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sixsausage.java.ai.langchain4j.entity.Appointment;
import com.sixsausage.java.ai.langchain4j.entity.Doctor;
import com.sixsausage.java.ai.langchain4j.entity.Schedule;
import com.sixsausage.java.ai.langchain4j.service.AppointmentService;
import com.sixsausage.java.ai.langchain4j.service.ScheduleService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class AppointmentTools {

    @Autowired
    private AppointmentService appointmentService;

    @Tool(name="预约挂号", value = "根据参数，先执行工具方法queryDepartment查询是否可预约，并直接给用户回答是否可预约，并让用户确认所有预约信息，用户确认后再进行预约。如果用户没有提供具体的医生姓名，请从向量存储中找到一位医生。")
    public String bookAppointment(Appointment appointment){
        // 查找数据库中是否包含对应的预约记录
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if(appointmentDB == null){
            appointment.setId(null); // 防止大模型幻觉设置了id
            if(appointmentService.save(appointment)){
                return "预约成功，并返回预约详情";
            } else {
                return "预约失败";
            }
        }
        return "您在相同的科室和时间已有预约";
    }

    @Tool(name="取消预约挂号", value = "根据参数，查询预约是否存在，如果存在则删除预约记录并返回取消预约成功，否则返回取消预约失败")
    public String cancelAppointment(Appointment appointment){
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if(appointmentDB != null){
            // 删除预约记录
            if(appointmentService.removeById(appointmentDB.getId())){
                return "取消预约成功";
            } else {
                return "取消预约失败";
            }
        }
        // 取消失败
        return "您没有预约记录，请核对预约科室和时间";
    }

    @Tool(name = "查询是否有号源", value="根据科室名称，日期，时间和医生查询是否有号源，并返回给用户")
    public boolean queryDepartment(
            @P(value = "科室名称") String name,
            @P(value = "日期") String date,
            @P(value = "时间，可选值：上午、下午") String time,
            @P(value = "医生名称", required = false) String doctorName
    ) {
        System.out.println("查询是否有号源");
        System.out.println("科室名称：" + name);
        System.out.println("日期：" + date);
        System.out.println("时间：" + time);
        System.out.println("医生名称：" + doctorName);
        // TODO 维护医生的排班信息：
        // 如果没有指定医生名字，则根据其他条件查询是否有可以预约的医生（有返回true，否则返回false）；
        // 如果指定了医生名字，则判断医生是否有排班（没有排版返回false）
        // 如果有排班，则判断医生排班时间段是否已约满（约满返回false，有空闲时间返回true）
        return true;
    }

    @Tool(name = "查询预约信息", value="根据用户名或身份证号查询预约信息")
    public List<Appointment> queryAppointments(@P(value = "用户名", required = false) String username,
                                               @P(value = "身份证号", required = false) String idCard) {
        return appointmentService.getByUsernameOrIdCard(username, idCard);
    }

    @Tool(name = "查询医生信息", value="查询所有医生的信息")
    public List<Doctor> queryDoctors() {
        return appointmentService.getDoctors();
    }

    @Tool(name = "根据ID查询医生信息", value="根据医生ID查询医生信息")
    public Doctor queryDoctorById(@P(value = "医生ID") Long id) {
        return appointmentService.getDoctorById(id);
    }
    @Autowired
    private ScheduleService scheduleService;
    @Tool(name = "查询特定日期和时间段的排班信息", value="根据日期和时间段查询排班信息")
    public List<Schedule> querySchedule1(
            @P(value = "日期") String dateString,
            @P(value = "时间，可选值：上午、下午") String timeSlot
    ) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return scheduleService.getByDateAndTimeSlot(date, timeSlot);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}