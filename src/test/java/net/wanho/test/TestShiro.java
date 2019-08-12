package net.wanho.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2019/7/30.
 */
public class TestShiro {
    @Test
    public void login(){
//        获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:myRealm.ini");
//        工厂创建实例
        SecurityManager securityManager = factory.getInstance();
//        将securityManager绑定给SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
//        获取subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
//        当前用户是否登陆成功 返回true或false
        System.out.println(subject.isAuthenticated());

//        登出
        subject.logout();
    }

    @Test
    public void shiroRoles(){
//        获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
//        工厂创建实例
        SecurityManager securityManager = factory.getInstance();
//        将securityManager绑定给SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
//        获取subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try {
            subject.login(token);
//            System.out.println(subject.hasRole("admin"));
//            System.out.println(subject.hasAllRoles(Arrays.asList("admin","manager","ss")));
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin","manager","aa"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }


        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
//        当前用户是否登陆成功 返回true或false
//        System.out.println(subject.isAuthenticated());

//        登出
        subject.logout();
    }

    @Test
    public void shiroPermission(){
//        获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
//        工厂创建实例
        SecurityManager securityManager = factory.getInstance();
//        将securityManager绑定给SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
//        获取subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try {
            subject.login(token);
            boolean[] permitted = subject.isPermitted("student:add", "student:delete");
            for (boolean b : permitted) {
                System.out.println(b);
            }
            System.out.println(subject.isPermitted("student:delete"));
            System.out.println(subject.isPermittedAll("student:add", "student:delete"));

        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
//        当前用户是否登陆成功 返回true或false
//        System.out.println(subject.isAuthenticated());

//        登出
        subject.logout();
    }


    @Test
    public void shiroPermissionRealm(){
//        获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permissions-realm.ini");
//        工厂创建实例
        SecurityManager securityManager = factory.getInstance();
//        将securityManager绑定给SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
//        获取subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try {
            subject.login(token);
            System.out.println(subject.hasAllRoles(Arrays.asList("admin","manager")));
            boolean[] permitted = subject.isPermitted("student:add", "student:select");
            for (boolean b : permitted) {
                System.out.println(b);
            }
            System.out.println(subject.isPermitted("student:delete"));
            System.out.println(subject.isPermittedAll("student:select", "student:delete"));

        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
//        当前用户是否登陆成功 返回true或false
//        System.out.println(subject.isAuthenticated());

//        登出
        subject.logout();
    }

}
