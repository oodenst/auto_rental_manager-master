package com.coder.rental.utils;

import com.coder.rental.entity.Permission;
import com.coder.rental.vo.RouteVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteTreeUtils {
    /**
     * 基于权限列表和父权限ID构建路由树。
     *
     * @param permissionList 权限列表，包含所有路由的权限信息。
     * @param pid 父权限ID，用于筛选特定父级下的路由。
     * @return 返回一个路由树的列表，每个路由包含路径、名称、组件等信息，以及可能的子路
    由。
     */
    public static List<RouteVO> buildRouteTree(List<Permission> permissionList, int pid) {
        List<RouteVO> routeVOList = new ArrayList<>();
        // 从权限列表中筛选出父ID为pid的权限，并为每个权限构建一个RouteVO实例
        Optional.ofNullable(permissionList).orElse(new ArrayList<>())
                .stream().filter(permission -> permission != null && permission.getPid() == pid)
                .forEach(permission -> {
                    RouteVO routeVO = new RouteVO();
                    routeVO.setPath(permission.getRoutePath());// 设置路由路径
                    routeVO.setName(permission.getRouteName());// 设置路由名称
                    // 判断是否为根路径菜单，并设置相应的组件和显示属性
                    if (permission.getPid() == 0) {
                        routeVO.setComponent("Layout");
                        routeVO.setAlwaysShow(true);
                    } else {
                        routeVO.setComponent(permission.getRouteUrl());
                        routeVO.setAlwaysShow(false);
                    }
                    // 设置路由的元信息，包括权限标签、图标和权限代码
                    routeVO.setMeta(routeVO.new
                            Meta(permission.getPermissionLabel(),
                            permission.getIcon(),
                            permission.getPermissionCode().split(",")));
                    // 递归构建该权限下的子路由树
                    List<RouteVO> children = buildRouteTree(permissionList, permission.getId());
                    routeVO.setChildren(children);
                    routeVOList.add(routeVO);
                });
        return routeVOList;
    }
    public static List<Permission> buildMenuTree(List<Permission> list,int
            pid){
        List<Permission> menuList = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .filter(permission -> permission != null &&
                        permission.getPid() == pid)
                .forEach(permission -> {
                    Permission menu = new Permission();
                    BeanUtils.copyProperties(permission,menu);
                    menu.setChildren(buildMenuTree(list,permission.getId()));
                    menuList.add(menu);
                });
        return menuList;
    }
}
