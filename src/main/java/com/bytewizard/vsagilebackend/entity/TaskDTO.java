package com.bytewizard.vsagilebackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {
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
