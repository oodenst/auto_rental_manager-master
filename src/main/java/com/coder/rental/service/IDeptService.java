package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.Dept;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IDeptService extends IService<Dept> {
    List<Dept> select(Dept dept);
    List<Dept> selectTree();

    boolean hasChildren(Integer id);
}
