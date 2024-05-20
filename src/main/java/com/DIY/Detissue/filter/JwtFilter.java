package com.DIY.Detissue.filter;


import com.DIY.Detissue.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
//Tất cả request đều phải chạy vào filter
public class JwtFilter extends OncePerRequestFilter {
    /**
     * Nhận được token truyền trên header
     * Giải mã token
     * Nếu giải mã thành công thì hợp lệ
     * Tạo chứng thực và cho phép đi vào link người dùng gọi
     */

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
//      Lấy header có key là authorization
            String header = request.getHeader("Authorization");
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                Claims claims = jwtHelper.decodeToken(token);
                if (claims != null) {
                    //Tạo chứng thực cho Spring Security
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                    securityContext.setAuthentication(user);
                }
            }
        }catch (Exception e) {

        }
        filterChain.doFilter(request,response);
    }
}
