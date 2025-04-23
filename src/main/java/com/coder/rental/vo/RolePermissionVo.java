package com.coder.rental.vo;

import com.coder.rental.entity.Permission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RolePermissionVo {
    //原有的权限id数组
    private Object[] checkedList;
    private List<Permission> permissionList;
}