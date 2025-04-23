package com.coder.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Customer;
import com.coder.rental.service.ICustomerService;
import com.coder.rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@RestController
@RequestMapping("/rental/customer")
public class CustomerController {
    @Resource
    private ICustomerService customerService;
    @PostMapping
    public Result save(@RequestBody Customer customer) {
        return customerService.save(customer) ? Result.success().setData(customer.getId()) :
                Result.fail();
    }
    @PutMapping
    public Result update(@RequestBody Customer customer) {
        return customerService.updateById(customer) ? Result.success() :
                Result.fail();
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        return customerService.delete(ids) ? Result.success() :
                Result.fail();
    }
    @PostMapping("{start}/{size}")
    public Result search(@PathVariable int start, @PathVariable int size,
                         @RequestBody Customer customer){
        Page<Customer> page = new Page<>(start, size);
        return Result.success(customerService.searchByPage(page, customer));
    }
    @PostMapping("selectCustomerByTel")
    public Result selectCustomerByTel(@RequestBody Customer customer){
        return Result.success(customerService.selectCustomerByTel(customer));
    }
}