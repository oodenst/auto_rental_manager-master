package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("sys_user_role")
@Schema(name = "UserRole对象", description = "")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description ="用户id")
    private Integer userId;

    @Schema(description ="角色id")
    private Integer roleId;
}
