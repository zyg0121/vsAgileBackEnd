package com.bytewizard.vsagilebackend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    private String taskPos;

    private Integer taskPriority;

    private LocalDateTime taskStartTime;

    private LocalDateTime taskEndTime;

    private String taskDesc;

    private Integer taskStatus;
}
