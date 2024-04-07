package com.dxc.meetingroomreservationsystem.configuration.security;

import com.alibaba.fastjson.JSON;
import com.dxc.meetingroomreservationsystem.pojo.enums.ResponseCode;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        System.out.println("无凭证");
        R<Object> r = R.createByError("无凭证");
        // 使用fastjson
        String json =  JSON.toJSONString(r);
        // 指定响应格式是json
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}

