package com.coder.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.Permission;
import com.coder.rental.entity.User;
import com.coder.rental.mapper.PermissionMapper;
import com.coder.rental.mapper.UserMapper;
import com.coder.rental.service.IPermissionService;
import com.coder.rental.utils.RouteTreeUtils;
import com.coder.rental.vo.RolePermissionVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Permission> selectPermissionListByUserId(Integer userId) {
        return baseMapper.selectPermissionListByUserId(userId);
    }

    @Override
    public List<Permission> selectList() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<Permission> permissions = baseMapper.selectList(queryWrapper);
        return RouteTreeUtils.buildMenuTree(permissions, 0);
    }

    /**
     * 选择权限树
     * 根据用户ID和角色ID获取该用户所拥有的权限树结构
     *
     * @param userId 用户ID，用于获取用户信息和权限列表
     * @param roleId 角色ID，用于获取角色的权限列表
     * @return RolePermissionVo 包含用户权限树和选中权限ID列表的对象
     */
    @Override
    public RolePermissionVo selectPermissionTree(Integer userId, Integer roleId) {
        // 根据用户ID查询用户信息
        User user = userMapper.selectById(userId);

        // 初始化权限列表
        List<Permission> list = null;

        // 如果用户存在且为管理员，获取所有权限
        if (user != null && user.getIsAdmin() == 1) {
            list = baseMapper.selectList(null);
        } else {
            // 否则，获取该用户所拥有的权限列表
            list = baseMapper.selectPermissionListByUserId(userId);
        }

        // 构建权限树结构
        List<Permission> permissions = RouteTreeUtils.buildMenuTree(list, 0);

        // 获取角色所拥有的权限列表
        List<Permission> rolePermissionsList = baseMapper.selectPermissionListByRoleId(roleId);

        // 创建新列表，用于存储用户权限与角色权限的交集
        List<Integer> ids = new ArrayList<>(list.stream().map(Permission::getId).toList());
        List<Integer> ids1 = rolePermissionsList.stream().map(Permission::getId).toList();
        ids.retainAll(ids1);


        // 将交集权限的ID转换为数组
        Object[] array = ids.toArray();

        // 创建RolePermissionVo对象，用于存储和返回权限信息
        RolePermissionVo rolePermissionVo = new RolePermissionVo();

        // 设置选中权限ID列表和权限树结构
        rolePermissionVo.setCheckedList(array).setPermissionList(permissions);

        // 返回RolePermissionVo对象
        return rolePermissionVo;
    }


    @Override
    public List<Permission> selectTree() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("permission_type", 2);
        queryWrapper.orderByAsc("order_num");
        List<Permission> permissions = baseMapper.selectList(queryWrapper);
        Permission permission = new Permission();
        permission.setId(0).setPid(-1).setPermissionLabel("根目录");
        permissions.add(permission);
        return RouteTreeUtils.buildMenuTree(permissions, -1);
    }

    @Override
    public boolean hasChildren(Integer id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);
        return baseMapper.selectCount(queryWrapper) > 0;
    }
}
