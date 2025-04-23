package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "User对象", description = "")
public class User implements Serializable, UserDetails{

    private static final long serialVersionUID = 1L;

    @Schema(description ="用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description ="用户账户")
    private String username;

    @Schema(description ="用户密码")
    private String password;

    @Schema(description ="账户是否过期")
    private boolean isAccountNonExpired = true;

    @Schema(description ="账户是否被锁定")
    private boolean isAccountNonLocked = true;

    @Schema(description ="密码是否过期")
    private boolean isCredentialsNonExpired = true;

    @Schema(description ="账户是否可用")
    private boolean isEnabled = true;

    @Schema(description ="用户真实姓名")
    private String realname;

    @Schema(description ="用户昵称")
    private String nickname;

    @Schema(description ="所属部门id")
    private Integer deptId;

    @Schema(description ="所属部门名称")
    private String deptName;

    @Schema(description ="性别")
    private Integer gender;

    @Schema(description ="联系电话")
    private String phone;

    @Schema(description ="邮箱")
    private String email;

    @Schema(description ="头像")
    private String avatar;

    @Schema(description ="是否管理员")
    private Integer isAdmin;

    @Schema(description ="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description ="修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description ="是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @TableField(exist = false)
    private List<Permission> permissionList;

}
