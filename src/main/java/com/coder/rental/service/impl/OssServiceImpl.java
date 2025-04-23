package com.coder.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.coder.rental.config.OSSConfig;
import com.coder.rental.service.IOssService;
import com.coder.rental.utils.FileUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class OssServiceImpl implements IOssService {
    @Resource
    private OSSConfig ossConfig;

    @Override
    public String upload(MultipartFile file) {
        //要上传文件的原始名
        String originalFilename = file.getOriginalFilename();
//获取新的文件名
        String fileName = FileUtils.getFileName(originalFilename);
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret()
        );
        try {
            ossClient.putObject(ossConfig.getBucketName(),fileName,file.getInputStream());
            return "https://"+ossConfig.getBucketName()+"."+ossConfig.getEndpoint()+"/"+fileName;
        } catch (IOException e) {
            throw new RuntimeException("上传失败\n"+e.getMessage());
        }finally {
            if(ossClient != null){
                ossClient.shutdown();
            }
        }
    }

    @Override
    public boolean delete(String url) {
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret()
        );
        String host = "https://" + ossConfig.getBucketName() + "." +
                ossConfig.getEndpoint() + "/";
        String objectName = StrUtil.removePrefix(url, host);
        try {
            ossClient.deleteObject(ossConfig.getBucketName(),objectName);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("删除失败\n"+e.getMessage());
        }finally {
            if(ossClient != null){
                ossClient.shutdown();
            }
        }
    }
}
