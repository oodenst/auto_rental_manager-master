package com.coder.rental.utils;

import com.coder.rental.entity.Dept;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeptTreeUtils {
    public static List<Dept> buildTree(List<Dept> deptList,int pid){
        List<Dept> deptTree = new ArrayList<>();
        Optional.ofNullable(deptList).orElse(new ArrayList<>())
                .stream().filter(dept -> dept!=null && dept.getPid() == pid)
                .forEach(dept -> {
                    Dept dept1 = new Dept();
                    BeanUtils.copyProperties(dept, dept1);
                    dept1.setChildren(buildTree(deptList, dept.getId()));
                    deptTree.add(dept1);
                });
        return deptTree;
    }
}
