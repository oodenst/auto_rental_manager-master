package com.coder.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.Role;
import com.coder.rental.entity.RolePermission;
import com.coder.rental.entity.User;
import com.coder.rental.entity.UserRole;
import com.coder.rental.mapper.RoleMapper;
import com.coder.rental.mapper.RolePermissionMapper;
import com.coder.rental.mapper.UserMapper;
import com.coder.rental.mapper.UserRoleMapper;
import com.coder.rental.service.IRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 分页查询角色列表。
     *
     * @param page 分页参数，包含当前页和每页数量。
     * @param role 查询条件，可包含角色名和创建者ID。
     * @return 返回角色的分页查询结果。
     */
    @Override
    public Page<Role> selectList(Page<Role> page, Role role) {
        // 创建查询条件包装器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
// 如果角色名不为空，则添加角色名的模糊查询条件
        queryWrapper.like(StrUtil.isNotEmpty(role.getRoleName()),
                "role_name", role.getRoleName());
// 按创建时间升序排序
        queryWrapper.orderByAsc("create_time");
// 获取创建者的ID
        Integer userId = role.getCreaterId();
// 根据ID查询用户信息
        User user = userMapper.selectById(userId);
// 如果用户存在且不是管理员，则限制查询结果为该用户创建的角色
        if (user != null && user.getIsAdmin() != 1) {//1是管理员
            queryWrapper.eq("creater_id", userId);
        }
// 执行分页查询并返回结果
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean hasUser(Integer id) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        return userRoleMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        if (!list.isEmpty()) {
            for (Integer id : list) {
                if (!hasUser(id)) {
                    rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", id));
                    baseMapper.deleteById(id);
                }
            }
        }
        return true;
    }

    @Override
    public boolean assignPermission(Integer roleId, List<Integer> permissionIds) {
        //把原来关联的菜单删除
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        rolePermissionMapper.delete(queryWrapper);

        //重新添加新的关联
        try {
            if (!permissionIds.isEmpty()) {
                for (Integer permissionId : permissionIds) {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(permissionId);
                    rolePermissionMapper.insert(rolePermission);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<Integer> selectRoleIdByUserId(Integer userId) {

        return baseMapper.selectRoleIdByUserId(userId);
    }


}
