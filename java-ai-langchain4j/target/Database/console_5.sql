CREATE TABLE `department_available_slots` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `department_name` varchar(100) NOT NULL COMMENT '科室名称',
  `date` date NOT NULL COMMENT '日期',
  `time_slot` varchar(50) NOT NULL COMMENT '时间段(如:上午/下午/晚上或具体时间范围)',
  `total_available_slots` int NOT NULL COMMENT '总可挂号数',
  `booked_slots` int NOT NULL DEFAULT '0' COMMENT '已预约数',
  `remaining_slots` int GENERATED ALWAYS AS (`total_available_slots` - `booked_slots`) STORED COMMENT '剩余可挂号数(计算字段)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_department_date_timeslot` (`department_name`, `date`, `time_slot`) COMMENT '科室-日期-时间段唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='科室可挂号数表';
CREATE TABLE appointment (
    username varchar(50),
    id_card varchar(50),
    department varchar(50),
    `date` varchar(50),
    `time` varchar(50),
    doctor_name varchar(50)
);