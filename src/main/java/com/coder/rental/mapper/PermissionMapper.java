package com.coder.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.rental.entity.Permission;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);

    List<Permission> selectPermissionListByRoleId(Integer roleId);
}
