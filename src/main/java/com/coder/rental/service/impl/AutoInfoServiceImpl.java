package com.coder.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.entity.AutoInfo;
import com.coder.rental.mapper.AutoInfoMapper;
import com.coder.rental.service.IAutoInfoService;
import com.coder.rental.service.IOssService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Service
public class AutoInfoServiceImpl extends ServiceImpl<AutoInfoMapper, AutoInfo> implements IAutoInfoService {
    @Resource
    private IOssService ossService;

    @Override
    public Page<AutoInfo> search(Page<AutoInfo> page, AutoInfo autoInfo) {
        return baseMapper.searchByPage(page,autoInfo);
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> list =
                Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        if (!list.isEmpty()){
            list.forEach(id->{
                AutoInfo autoInfo =baseMapper.selectById(id);
                if(autoInfo != null){
                    String pic = autoInfo.getPic();
                    if(pic != null){
                        ossService.delete(pic);
                    }
                }
            });
            return baseMapper.deleteBatchIds(list) > 0;
        }
        return false;
    }

    @Override
    public AutoInfo selectByAutoNum(String autoNum) {
        QueryWrapper<AutoInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("auto_num", autoNum);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<AutoInfo> toBeMaintain() {
        return baseMapper.toBeMaintain();
    }
}
