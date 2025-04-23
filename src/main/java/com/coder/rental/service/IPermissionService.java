package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.Permission;
import com.coder.rental.vo.RolePermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);
    List<Permission> selectTree();
    boolean hasChildren(Integer id);
    List<Permission> selectList();
    RolePermissionVo selectPermissionTree(Integer userId, Integer roleId);
}
