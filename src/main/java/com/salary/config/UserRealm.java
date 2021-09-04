package com.salary.config;

import com.salary.entity.User;
import com.salary.service.MenuService;
import com.salary.service.RoleService;
import com.salary.service.UserService;
import com.salary.util.DigestPass;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author 庞海
 */
public class UserRealm extends AuthorizingRealm
{

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        System.out.println("进入授权...");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> perms = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 管理员拥有所有权限
        if (user.getUser_id().intValue()==1)
        {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            roles = roleService.selectRoleKeys(user.getUser_id());
            perms = menuService.selectPermsByUserId(user.getUser_id());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(perms);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        User user = (User) userService.getOneUser(username).get("data");
        if(user==null){
            throw new AuthenticationException();
        }
        String upPassword = String.valueOf(upToken.getPassword());
        DigestPass dp=new DigestPass();  //MD5摘要算法
        String password = dp.getDigestString( upPassword+user.getSalt()); //盐加密
        upToken.setPassword(password.toCharArray());
        return new SimpleAuthenticationInfo(user,user.getPassword(),username);
    }

    /**
     * 清理指定用户授权信息缓存
     */
    public void clearCachedAuthorizationInfo(Object principal)
    {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        this.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清理所有用户授权信息缓存
     */
    public void clearAllCachedAuthorizationInfo()
    {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null)
        {
            for (Object key : cache.keys())
            {
                cache.remove(key);
            }
        }
    }

}
