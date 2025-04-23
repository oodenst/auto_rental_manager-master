package com.coder.rental.controller;//package com.coder.rental.controller;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.system.UserInfo;
import com.alibaba.excel.metadata.Holder;
import com.coder.rental.entity.Permission;
import com.coder.rental.entity.User;
import com.coder.rental.security.CustomerAuthenticationException;
import com.coder.rental.service.IUserService;
import com.coder.rental.utils.JwtUtils;
import com.coder.rental.utils.RedisUtils;
import com.coder.rental.utils.Result;
import com.coder.rental.utils.RouteTreeUtils;
import com.coder.rental.vo.RouteVO;
import com.coder.rental.vo.TokenVo;
import com.coder.rental.vo.UserInfoVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/rental/auth")
public class AuthController {
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private IUserService userService;
    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token == null){
            token = request.getParameter("token");
        }
        //从SecurityContextHolder上下文中获取Authentication
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)
                authentication.getPrincipal();
//从token中获取用户名
        String username = JwtUtils.parseToken(token).getClaim("username").toString();
        String newToken="";
        if (StrUtil.equals(username,userDetails.getUsername())){
            Map<String,Object> map=new HashMap<>();
            map.put("username",userDetails.getUsername());
            newToken= JwtUtils.createToken(map);
        }else{
            throw new CustomerAuthenticationException("token数据异常");
        }
        //获取本次token过期时间
        NumberWithFormat claim= (NumberWithFormat)
                JwtUtils.parseToken(newToken).getClaim(JWTPayload.EXPIRES_AT);
        DateTime dateTime = (DateTime) claim.convert(DateTime.class, claim);
        long expireTime=dateTime.getTime();
        //创建一个TokenVo的对象，将新生成的token和过期时间存入
        TokenVo tokenVo = new TokenVo();
        tokenVo.setToken(newToken).setExpireTime(expireTime);
        //清除原有token
        redisUtils.del("token:"+token);
        //将新的token存入redis
        long nowTime=DateTime.now().getTime();
        String newTokenKey="token:"+newToken;
        redisUtils.set(newTokenKey,newToken,(expireTime-nowTime)/1000);
        return Result.success(tokenVo).setMessage("刷新token成功");
    }
    /**
     * 获取当前用户的信息
     *
     * 该接口不需要参数，通过SecurityContextHolder上下文获取认证信息。
     * 如果认证信息存在，则根据用户ID查询用户角色，并将用户基本信息和角色信息封装成
     UserInfoVo返回。
     * 如果认证信息不存在，则返回失败结果，提示认证信息为空。
     *
     * @return Result 包含用户信息的Result对象，如果获取失败则包含错误信息
     */
    @GetMapping("/getInfo")
    public Result getUserInfo(){
// 从securityContextHolder上下文中获取认证信息
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null){
            return Result.fail().setMessage("认证信息为空");
        }
        User user = (User) authentication.getPrincipal();
//// 查询用户角色名称
//        List<String> list=userService.selectRoleName(user.getId());
//        Object[] array = list.toArray(); // 将角色名称列表转换为数组
        List<Permission> permissionList = user.getPermissionList();
        Object[] array = permissionList.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .toArray();
// 创建并填充用户信息视图对象
        UserInfoVo userInfoVo=new
                UserInfoVo(user.getId(),user.getUsername(),
                user.getAvatar(),user.getNickname(),array);
        return Result.success(userInfoVo).setMessage("获取用户信息成功");
    }
    @GetMapping("/menuList")
    public Result getMenuList(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        //获取用户的权限列表
        List<Permission> permissionList = user.getPermissionList();
        //获取用户的菜单
        //将permission_type为2的按钮移除，不需要生成对应的菜单
        permissionList.removeIf(permission -> Objects.equals(permission.getPermissionType(),2));
        List<RouteVO> routeVOS = RouteTreeUtils.buildRouteTree(permissionList, 0);
        return Result.success(routeVOS).setMessage("获取菜单列表成功");
    }
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = request.getHeader("token");
        if(StrUtil.isEmpty(token)){
            token = request.getParameter("token");
        }if(authentication != null){
            redisUtils.del("token:"+token);
            SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
            handler.logout(request, response, authentication);
            return Result.success().setMessage("退出成功");
        }
        return Result.fail().setMessage("退出失败");
    }
}
