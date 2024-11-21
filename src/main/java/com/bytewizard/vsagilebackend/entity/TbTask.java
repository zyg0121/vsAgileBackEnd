package com.bytewizard.vsagilebackend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author byteWizard
 * @since 2024-11-15
 */
@Getter
@Setter
@TableName("tb_task")
public class TbTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("task_id")
    private Integer taskId;

    private String taskTitle;

    private Integer taskProjectId;

    private Integer taskUserId;

    private String taskPriority;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime taskStartTime;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime taskEndTime;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime taskPreEndTime;

    private String taskDesc;

    private Integer taskStatus;
}
