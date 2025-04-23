package com.coder.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.rental.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> selectRoleNameByUserId(Integer userId);
}
