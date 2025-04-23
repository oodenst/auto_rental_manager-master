package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.entity.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
public interface IOrderService extends IService<Order> {

    Page<Order> search(Page<Order> page, Order order);

    boolean insert(Order order);

    Page<Order> searchUnfinished(Page<Order> page, Order order);

    boolean returnAuto(Order order, Integer maintainMileage);

    long countViolation(Integer autoId);
}
