package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("sys_role_permission")
@Tag(name = "RolePermission对象", description = "")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name="角色id")
    private Integer roleId;

    @Schema(name="权限资源id")
    private Integer permissionId;
}
