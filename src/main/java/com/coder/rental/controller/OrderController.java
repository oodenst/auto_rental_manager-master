package com.coder.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Order;
import com.coder.rental.service.IOrderService;
import com.coder.rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/rental/order")
public class OrderController {
    @Resource
    private IOrderService orderService;
    @Value("${auto.info.maintain-mileage}")
    private Integer maintainMileage;
    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable int start, @PathVariable int size, @RequestBody Order order){
        Page<Order> page = new Page<>(start,size);
        return Result.success(orderService.search(page,order));
    }

    @PostMapping
    public Result save(@RequestBody Order order){
        return orderService.insert(order)?Result.success():Result.fail();
    }
    @PostMapping("/unfinished/{start}/{size}")
    public Result searchUnfinished(@PathVariable int start,@PathVariable int size,
                                   @RequestBody Order order){
        Page<Order> page = new Page<>(start,size);
        return Result.success(orderService.searchUnfinished(page,order));
    }
    @PutMapping
    public Result update(@RequestBody Order order){
        return orderService.returnAuto(order,maintainMileage)?Result.success():Result.fail();
    }
    @GetMapping("/countViolation/{autoId}")
    public Result countViolation(@PathVariable Integer autoId){
        long count=orderService.countViolation(autoId);
        return Result.success(count);
    }
    @GetMapping("/doReturnDeposit/{orderId}")
    public Result doReturnDeposit(@PathVariable Integer orderId){
        Order order = orderService.getById(orderId);
        order.setDepositReturn(1);
        return orderService.updateById(order)?Result.success():Result.fail();
    }

}
