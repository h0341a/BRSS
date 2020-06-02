package com.jycz.common.config.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetails;

    public WebSecurityConfig(MyUserDetailsService userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/books", "/register", "/*/save", "/swagger*//**", "/webjars*//**", "/v2/api-docs").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
                    httpServletResponse.getWriter().print(new ObjectMapper().writeValueAsString(Result.ofSuccess("登录成功")));
                    httpServletResponse.getWriter().flush();
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
                    httpServletResponse.getWriter().print(new ObjectMapper().writeValueAsString(Result.ofFail(ErrCodeEnum.PARAMETERS_VALIDATION_FAIL, "登录失败:" + e.getMessage())));
                    httpServletResponse.getWriter().flush();
                })
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
                    httpServletResponse.getWriter().print(new ObjectMapper().writeValueAsString(Result.ofSuccess("注销成功")));
                    httpServletResponse.getWriter().flush();
                })
                .and()
                .exceptionHandling().authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
            httpServletResponse.getWriter().print(new ObjectMapper().writeValueAsString(Result.ofFail(ErrCodeEnum.NO_AUTHORITY, "用户未登录!")));
            httpServletResponse.getWriter().flush();
        })
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
                    httpServletResponse.getWriter().print(new ObjectMapper().writeValueAsString(Result.ofFail(ErrCodeEnum.NO_AUTHORITY, "你没有足够的权限!")));
                    httpServletResponse.getWriter().flush();
                });
    }
}
