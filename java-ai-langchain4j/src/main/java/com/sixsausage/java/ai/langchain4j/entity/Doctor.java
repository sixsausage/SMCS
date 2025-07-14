package com.sixsausage.java.ai.langchain4j.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String position;
    private String specialty;
    private String description;
}