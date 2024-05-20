package com.DIY.Detissue.config;

import com.DIY.Detissue.filter.JwtFilter;
import com.DIY.Detissue.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //    Khai báo chuẩn mã hóa Bcrypt và lưu trữ trên IOC
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAuthenProvider customAuthenProvider;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // cấu hình lại cors cho các req sử dụng headers Authorization
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); //cho phép tất cả các domain gọi đến
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));//cho phép các phương thức gọi đến
        configuration.setAllowedHeaders(Arrays.asList("*"));//cho phép tất cả các header gọi đến
        configuration.setAllowCredentials(true);//cho phép gửi cookie
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);//qui định cấu hình cors cho tất cả các link
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()//tắt hình thức cấu hình liên quan đến tấn công CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//khai báo không sử dụng session
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
//                .authorizeHttpRequests()//qui định lại các rule liên quan đến chứng thực cho link được gọi
//                .antMatchers("/signin"
//                        , "/signup"
//                        , "/demo/**"
//                        , "/product/**"
//                        , "/category"
//                        , "/blog"
//                        , "/comment").permitAll()//các link này không cần chứng thực
//                .antMatchers("/product/user").authenticated()
//                .anyRequest().authenticated()//tất cả các link còn lại phải được chứng thực
//                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
