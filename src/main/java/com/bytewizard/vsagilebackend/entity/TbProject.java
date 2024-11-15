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
@TableName("tb_project")
public class TbProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("project_id")
    private Integer projectId;

    private String projectName;

    private Integer projectPmId;

    private String projectDesc;

    private LocalDateTime projectStartTime;

    private LocalDateTime projectEndTime;

    private Integer projectStatus;
}
