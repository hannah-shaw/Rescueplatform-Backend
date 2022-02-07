package com.rescueplatform_backend.config.security.component;


import com.rescueplatform_backend.entity.Menu;
import com.rescueplatform_backend.entity.Role;
import com.rescueplatform_backend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求url分析请求所需的角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //根据角色去查询所有的菜单
        List<Menu> menus = menuService.getMenuRoleList();
        for (Menu menu : menus){
            //判断请求 url 与菜单角色是否匹配
            if (antPathMatcher.match(menu.getUrl(),requestUrl)){
                String[] strings = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(strings);
            }
        }
        //没匹配的 url 默认登录就可以访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
