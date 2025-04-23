package com.coder.rental.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoBrand;
import com.coder.rental.service.IAutoBrandService;
import com.coder.rental.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
@RequestMapping("/rental/autoBrand")
@Tag(name="autoBrand",description = "autoBrand")
public class AutoBrandController {
    @Resource
    private IAutoBrandService autoBrandService;
    @PostMapping("/{start}/{size}")
    @Operation(summary = "分页获取信息",description = "分页获取信息")
    @Parameter(name = "token",  in = ParameterIn.HEADER,required = true)
    public Result search(@PathVariable int start, @PathVariable int size, @RequestBody AutoBrand  autoBrand){
        Page<AutoBrand> page =new Page<>(start,size);
        return Result.success().setData(autoBrandService.serachByPage(page,autoBrand));
    }
    @PostMapping
    @Parameter(name = "token",  in = ParameterIn.HEADER,required = true)
    public Result save(@RequestBody AutoBrand autoBrand){
        return autoBrandService.save(autoBrand) ? Result.success():Result.fail();
    }
    @PutMapping
    @Parameter(name = "token",  in = ParameterIn.HEADER,required = true)
    public Result update(@RequestBody AutoBrand autoBrand){
        return autoBrandService.updateById(autoBrand) ? Result.success():Result.fail();
    }
    @DeleteMapping("/{ids}")
    @Parameter(name = "token",  in = ParameterIn.HEADER,required = true)
    public Result delete(@PathVariable String ids){
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        return autoBrandService.removeByIds(list) ? Result.success() : Result.fail();
    }
    @GetMapping("/{makerId}")
    @Parameter(name = "token",  in = ParameterIn.HEADER,required = true)
    public Result getById(@PathVariable Integer makerId){
        return Result.success().setData(autoBrandService.selectByMakerId(makerId));
    }
    @GetMapping("exportExcel")
    public void export(HttpServletResponse response){
        List<AutoBrand> list = autoBrandService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("brandName","品牌名称");
        writer.addHeaderAlias("deleted","是否删除");
        writer.write(list,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");
        String fileName= URLEncoder.encode( "汽车品牌", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        try {
            writer.flush(response.getOutputStream(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            writer.close();
        }
    }

}
