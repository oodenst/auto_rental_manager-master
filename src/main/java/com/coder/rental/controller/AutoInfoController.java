package com.coder.rental.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoInfo;
import com.coder.rental.service.IAutoInfoService;
import com.coder.rental.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/rental/autoInfo")
@Tag(name="autoInfo",description = "autoInfo")
public class AutoInfoController {
    @Value("${auto.info.maintain-mileage}")
    private Integer maintainMileage;
    @Resource
    private IAutoInfoService autoInfoService;
    @PostMapping("/{start}/{size}")
    @Operation(description = "分页获取信息")
    public Result search(@PathVariable Integer start, @PathVariable Integer size, @RequestBody AutoInfo autoInfo){
        return Result.success(autoInfoService.search(new Page<>(start,size),autoInfo));
    }
    @PostMapping
    public Result save(@RequestBody AutoInfo autoInfo){
//设置一下应保次数和实保次数
        int i = autoInfo.getMileage() / maintainMileage;
        autoInfo.setExpectedNum(i);
        autoInfo.setActualNum(i);
        return autoInfoService.save(autoInfo)?
                Result.success():Result.fail();
    }
    @PutMapping
    public Result update(@RequestBody AutoInfo autoInfo){
        int i = autoInfo.getMileage() / maintainMileage;
        autoInfo.setExpectedNum(i);
        autoInfo.setActualNum(i);
        return autoInfoService.updateById(autoInfo)?
                Result.success():Result.fail();
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){
        return Result.success(autoInfoService.delete(ids));
    }
    @PostMapping("/exist")
    public Result exist(@RequestBody AutoInfo autoInfo){
        AutoInfo info = autoInfoService.selectByAutoNum(autoInfo.getAutoNum());
        return ObjectUtil.isNotEmpty(info)?
                Result.success().setMessage("have"):Result.success().setMessage("none");
    }
    @GetMapping("/toBeMaintain")
    public Result toBeMaintain(){
        return Result.success(autoInfoService.toBeMaintain());
    }
}
