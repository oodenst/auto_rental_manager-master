package com.coder.rental.controller;

import com.coder.rental.service.IOssService;
import com.coder.rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rental/oss")
public class OSSController {
    @Resource
    private IOssService ossService;
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        return Result.success(ossService.upload(file));
    }
    @DeleteMapping("/delete")
    public Result delete(String url){
        return Result.success(ossService.delete(url));
    }
}
