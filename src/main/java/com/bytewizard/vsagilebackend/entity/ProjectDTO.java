package com.bytewizard.vsagilebackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjectDTO {
    private String projectName;

    private Integer projectPmId;

    private String projectDesc;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime projectStartTime;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime projectEndTime;

    private Integer projectStatus;
}
