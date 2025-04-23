package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role")
@Schema(name = "Role对象", description = "")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description ="角色id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description ="角色编码")
    private String roleCode;

    @Schema(description ="角色名称")
    private String roleName;

    @Schema(description ="创建人id")
    private Integer createrId;

    @Schema(description ="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description ="修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description ="备注")
    private String remark;

    @Schema(description ="是否删除")
    private Boolean deleted;
}
