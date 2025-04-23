package com.coder.rental.controller;

import com.coder.rental.entity.Dept;
import com.coder.rental.service.IDeptService;
import com.coder.rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@RestController
@RequestMapping("/rental/dept")
public class DeptController {
    @Resource
    private IDeptService deptService;
    @PostMapping
    public Result<List<Dept>> list(@RequestBody Dept dept){
        return Result.success(deptService.select(dept));
    }
    @GetMapping
    public Result tree(){
        return Result.success(deptService.selectTree());
    }
    @PostMapping("save")
    public Result save(@RequestBody Dept dept){
        return deptService.save(dept)?Result.success():Result.fail();
    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        return deptService.updateById(dept)?Result.success():Result.fail();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return deptService.removeById(id)?Result.success():Result.fail();
    }
    @GetMapping("/{id}")
    public Result hasChildren(@PathVariable Integer id){
        return deptService.hasChildren(id)?Result.success().setMessage("有"):Result.success().setMessage("无");
    }
}
