package com.coder.rental.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.AutoInfo;
import com.coder.rental.entity.Order;
import com.coder.rental.mapper.AutoInfoMapper;
import com.coder.rental.mapper.OrderMapper;
import com.coder.rental.service.IOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private AutoInfoMapper autoInfoMapper;


    @Override
    public Page<Order> search(Page<Order> page, Order order) {
        return baseMapper.search(page,order);
    }

    @Override
    public boolean insert(Order order) {
        //将当前车辆的状态，由未租改成已租
        Integer autoId = order.getAutoId();
        AutoInfo autoInfo = autoInfoMapper.selectById(autoId);
        autoInfo.setStatus(1);
        autoInfoMapper.updateById(autoInfo);
        //生成订单编号
        String number = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
        order.setOrderNum(number);
        order.setRentalTime(LocalDateTime.now());
        return baseMapper.insert(order) > 0;
    }

    @Override
    public Page<Order> searchUnfinished(Page<Order> page, Order order) {
        return baseMapper.searchUnfinished(page, order);
    }

    @Override
    public boolean returnAuto(Order order, Integer maintainMileage) {
        //修改车辆状态
        try {
            Integer autoId = order.getAutoId();
            AutoInfo autoInfo = autoInfoMapper.selectById(autoId);
            autoInfo.setStatus(0);
            //增加车辆行驶里程
            autoInfo.setMileage(order.getReturnMileage());
            //修改应保次数
            autoInfo.setExpectedNum(autoInfo.getMileage() / maintainMileage);
            autoInfoMapper.updateById(autoInfo);
            //update更新订单
            baseMapper.updateById(order);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("归还失败");
        }
    }

    @Override
    public long countViolation(Integer autoId) {
        return baseMapper.countViolation(autoId);
    }
}
