package com.coder.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.RentalType;
import com.coder.rental.service.IRentalTypeService;
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
@RequestMapping("/rental/rentalType")
public class RentalTypeController {
    @Resource
    private IRentalTypeService rentalTypeService;
    @PostMapping("{start}/{size}")
    public Result search(@PathVariable int start, @PathVariable int size,
                         @RequestBody RentalType rentalType){
        Page<RentalType> page=new Page<>(start,size);
        return
                Result.success(rentalTypeService.searchByPage(page,rentalType));
    }
    @PostMapping
    public Result save(@RequestBody RentalType rentalType){
        return rentalTypeService.save(rentalType)?
                Result.success():Result.fail();
    }
    @PutMapping
    public Result update(@RequestBody RentalType rentalType){
        return rentalTypeService.updateById(rentalType)?
                Result.success():Result.fail();
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){
        return rentalTypeService.delete(ids)?Result.success():Result.fail();
    }
    @GetMapping
    public Result selectAll(){
        return Result.success(rentalTypeService.list());
    }
}
