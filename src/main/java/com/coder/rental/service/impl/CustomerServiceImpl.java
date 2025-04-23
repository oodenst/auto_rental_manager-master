package com.coder.rental.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.Customer;
import com.coder.rental.mapper.CustomerMapper;
import com.coder.rental.service.ICustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public boolean delete(String ids) {
        List<Integer> list =
                Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        if (!list.isEmpty()) {
            return baseMapper.deleteBatchIds(list) > 0;
        }
        return false;
    }

    @Override
    public Page<Customer> searchByPage(Page<Customer> page, Customer
            customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(customer.getName()), "name", customer.getName());
        queryWrapper.like(ObjectUtil.isNotEmpty(customer.getTel()), "tel", customer.getTel());
        queryWrapper.like(ObjectUtil.isNotEmpty(customer.getIdNum()), "id_num", customer.getIdNum());
        queryWrapper.le(ObjectUtil.isNotEmpty(customer.getHighAge()), "age", customer.getHighAge());
        queryWrapper.ge(ObjectUtil.isNotEmpty(customer.getLowAge()), "age", customer.getLowAge());
        queryWrapper.eq(ObjectUtil.isNotEmpty(customer.getGender()), "gender", customer.getGender());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Customer selectCustomerByTel(Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(customer.getTel()),"tel", customer.getTel());
        return baseMapper.selectOne(queryWrapper);
    }
}
