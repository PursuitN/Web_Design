package com.example.springweb.service;

import com.example.springweb.dao.HelloApp;
import com.example.springweb.mapper.AppMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AppService {

    @Resource
    private AppMapper appMapper;

    public List<HelloApp> getAppList() {
        List<HelloApp> list = appMapper.findAll();
        return list;
    }

    public void InsertApp(Map<String, String> params){
        ObjectMapper objectMapper = new ObjectMapper();
        HelloApp helloApp = objectMapper.convertValue(params, HelloApp.class);
        appMapper.insert(helloApp);
    }

    public HelloApp getOne(String name){
        //HelloUser result = new HelloUser();
        HelloApp result = appMapper.getOne(name);
        System.out.println("getOne:"+result);
        if (result==null)
        {
            result=new HelloApp();//索引为空的时候，返回null，否则就会出错。
        }
        System.out.println(result.toString());
        return result;
    }

}
