package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.utils.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController

public class TestController {

    @RequestMapping("/get")
    public R get(){
        HashMap map = new HashMap();
        map.put("username","zhang.han@dxc.com");
        map.put("password","123456");

        return R.createBySuccess("访问成功",map);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/del")
    public String del(){
        return "删除成功";
    }

}
