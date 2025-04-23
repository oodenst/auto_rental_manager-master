package com.coder.rental.security;//package com.coder.rental.security;

import com.coder.rental.entity.Permission;
import com.coder.rental.entity.User;
import com.coder.rental.service.IPermissionService;
import com.coder.rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Objects;

@Component
@CrossOrigin
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Resource
    private IPermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.selectByUsername(username);//到数据库中按
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //查询用户列表权限
        List<Permission> permissions = permissionService.selectPermissionListByUserId(user.getId());
        user.setPermissionList(permissions);
        //通过stream流处理，将权限对象转换为字符串对象
        List<String> list = permissions.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull)
                .toList();
        String[] array = list.toArray(new String[0]);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(array);
        user.setAuthorities(authorities);
        System.out.println(user);
        return user;
    }
}
