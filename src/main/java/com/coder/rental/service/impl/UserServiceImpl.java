package com.coder.rental.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.User;
import com.coder.rental.entity.UserRole;
import com.coder.rental.mapper.UserMapper;
import com.coder.rental.mapper.UserRoleMapper;
import com.coder.rental.service.IOssService;
import com.coder.rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private IOssService ossService;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public User selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> selectRoleName(int id) {
        return baseMapper.selectRoleNameByUserId(id);
    }

    @Override
    public Page<User> searchByPage(Page<User> page, User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(user.getDeptId() != null && user.getDeptId() == 0){
            return baseMapper.selectPage(page,null);
        }
        queryWrapper.eq(ObjectUtil.isNotEmpty(user.getDeptId()),"dept_id", user.getDeptId())
                .like(StrUtil.isNotBlank(user.getUsername()),"username", user.getUsername())
                .like(StrUtil.isNotBlank(user.getRealname()),"realname", user.getRealname())
                .like(StrUtil.isNotBlank(user.getNickname()),"nickname", user.getNickname())
                .like(StrUtil.isNotBlank(user.getPhone()),"phone", user.getPhone())
                .like(StrUtil.isNotBlank(user.getEmail()),"email", user.getEmail());
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        try {
            if(!list.isEmpty()){
                list.forEach(id->{
                    User user = baseMapper.selectById(id);
                    if (user.getIsAdmin() != null && user.getIsAdmin() != 1) {
                    //删除时，要同时删除用户的头像
                        if (user.getAvatar()!=null){
                            ossService.delete(user.getAvatar());
                        }
                    //删除用户
                        baseMapper.deleteById(id);
                    //删除用户和角色关联表数据
                        userRoleMapper.delete(
                                new QueryWrapper<UserRole>().eq("user_id", id));
                    }
                });
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException("删除失败\n"+e.getMessage());
        }
    }
    @Override
    public boolean bindRole(Integer userId, List<Integer> list) {
//先删除所有当前用户和角色的关联
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId));
//重新插入用户和角色关联
        if (!list.isEmpty()){
            list.forEach(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoleMapper.insert(userRole);
            });
            return true;
        }
        return false;
    }
}
