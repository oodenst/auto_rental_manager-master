package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IRoleService extends IService<Role> {
    Page<Role> selectList(Page<Role> page, Role role);
    boolean hasUser(Integer id);
    boolean delete(String ids);
    public boolean assignPermission(Integer roleId,List<Integer> permissionIds);
    List<Integer> selectRoleIdByUserId(Integer userId);
}
