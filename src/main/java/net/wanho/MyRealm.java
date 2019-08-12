package net.wanho;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by Administrator on 2019/7/30.
 */
public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "MyRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return true;
    }

//    认证
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        if (!"zhang".equals(username)){
            throw new UnknownAccountException("账号错误");
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException("密码错误111");
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
