package net.wanho;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public class MyRealm2 extends AuthorizingRealm {

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

//        给当前对象赋予角色和权限
        List<String> roles = Arrays.asList("admin","manager");
        List<String> permissions = Arrays.asList("student:add","student:delete");


        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        赋予角色
        simpleAuthorizationInfo.addRoles(roles);
//        赋予权限
        simpleAuthorizationInfo.addStringPermissions(permissions);


        return simpleAuthorizationInfo;
    }

//    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        if (!"zhang".equals(username)){
            throw new UnknownAccountException("账号错误");
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException("密码错误");
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
