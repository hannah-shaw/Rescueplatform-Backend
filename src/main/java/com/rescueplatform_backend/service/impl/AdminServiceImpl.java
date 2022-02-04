package com.rescueplatform_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rescueplatform_backend.config.security.component.JwtTokenUtils;
import com.rescueplatform_backend.entity.Admin;
import com.rescueplatform_backend.entity.AdminRole;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.mapper.AdminMapper;
import com.rescueplatform_backend.mapper.AdminRoleMapper;
import com.rescueplatform_backend.mapper.RoleMapper;
import com.rescueplatform_backend.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;//配置了扫描包，实际上已经注入了 但是还是爆红，不管

    @Autowired
    private UserDetailsService userDetailsService;

    //密码加密
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    //将配置文件中存的值取过来
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password,  String code,HttpServletRequest request) {
        //获取验证码
        String defaultKaptcha = (String) request.getSession().getAttribute("captcha");
        System.out.println("登录验证的验证码：" + code);
        if (StringUtils.isEmpty(code) || !defaultKaptcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码不正确，请重新输入！");
        }
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password,
                userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确!");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员!");
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        //使用MybatisPlus的单个查询方法，selectOne 用.eq 比较方法在匹配查询条件
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    }

}
