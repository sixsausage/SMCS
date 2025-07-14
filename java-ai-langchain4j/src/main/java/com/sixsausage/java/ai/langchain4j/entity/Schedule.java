package com.sixsausage.java.ai.langchain4j.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long doctorId;
    private Date date;
    private String timeSlot; // 上午或下午
    private Boolean isAvailable;

    @TableField(exist = false)
    private Doctor doctor;

}