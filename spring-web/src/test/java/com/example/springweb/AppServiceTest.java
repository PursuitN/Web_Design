package com.example.springweb;

import com.example.springweb.dao.HelloApp;
import com.example.springweb.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTest {
    @Autowired
    AppService appService;

    @Test
    public void getApps1() {
        // 测试获取用户列表功能
        List<HelloApp> apps = appService.getAppList();
        assertFalse("User not null", apps == null);
        assertNotNull(apps);
        assertNotEquals(apps.size(), 0);

        assertEquals(apps.size(), 7);
        assertEquals(apps.get(0).getappRange(), "企业专用工业APP");
        assertEquals(apps.get(0).getbusLink(), "生产制造工业APP");
        assertEquals(apps.get(0).getknowledgeAspect(), "知识建模类");
        assertEquals(apps.get(0).getappName(), "myapp06");
        assertEquals(apps.get(0).getseriousness(), "灾难的");
        assertEquals(apps.get(0).getclassifyer(), "Ⅲ");
        assertEquals(apps.get(0).getfourStandard(), "三级要求");
    }

    @Test
    public void getApps2() {
        // 测试获取用户列表功能
        List<HelloApp> apps = appService.getAppList();
        assertFalse("User not null", apps == null);
        assertNotNull(apps);
        assertNotEquals(apps.size(), 0);

        assertEquals(apps.size(), 6);
        assertEquals(apps.get(1).getappRange(), "企业专用工业APP");
        assertEquals(apps.get(1).getbusLink(), "研发设计工业APP");
        assertEquals(apps.get(1).getknowledgeAspect(), "数据分析类");
        assertEquals(apps.get(1).getappName(), "myappya");
        assertEquals(apps.get(1).getseriousness(), "严重的");
        assertEquals(apps.get(1).getclassifyer(), "Ⅲ");
        assertEquals(apps.get(1).getfourStandard(), "三级要求");
    }

    @Test
    public void testInsert_getOne1() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("appRange", "企业专用工业APP");
        params.put("busLink", "研发设计工业APP");
        params.put("knowledgeAspect", "数据分析类");
        params.put("appName", "testapp1");
        params.put("seriousness", "轻度的");
        params.put("classifyer", "Ⅱ");
        params.put("fourStandard", "三级要求");

        appService.InsertApp(params);
        assertEquals(appService.getAppList().size(),7);
        // 获取刚刚插入的信息，进一步结合getOne进行验证
        HelloApp helloApp = appService.getOne("testapp1");

        assertEquals(helloApp.getappRange(),"企业专用工业APP");
        assertEquals(helloApp.getbusLink(),"研发设计工业APP");
        assertEquals(helloApp.getknowledgeAspect(),"数据分析类");
        assertEquals(helloApp.getappName(),"testapp1");
        assertEquals(helloApp.getseriousness(),"轻度的");
        assertEquals(helloApp.getclassifyer(),"Ⅱ");
        assertEquals(helloApp.getfourStandard(),"三级要求");

        // 获取不存在的信息
        HelloApp helloUser2 = appService.getOne("testappnotexist");
        assertEquals(helloUser2.getappName(),null);
    }

    @Test
    public void testInsert_getOne2() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("appRange", "行业通用工业APP");
        params.put("busLink", "运维服务工业APP");
        params.put("knowledgeAspect", "业务信息化类");
        params.put("appName", "testapp2");
        params.put("seriousness", "灾难的");
        params.put("classifyer", "Ⅱ");
        params.put("fourStandard", "一级要求");

        appService.InsertApp(params);
        assertEquals(appService.getAppList().size(),8);
        // 获取刚刚插入的信息，进一步结合getOne进行验证
        HelloApp helloApp = appService.getOne("testapp2");

        assertEquals(helloApp.getappRange(),"行业通用工业APP");
        assertEquals(helloApp.getbusLink(),"运维服务工业APP");
        assertEquals(helloApp.getknowledgeAspect(),"业务信息化类");
        assertEquals(helloApp.getappName(),"testapp2");
        assertEquals(helloApp.getseriousness(),"灾难的");
        assertEquals(helloApp.getclassifyer(),"Ⅱ");
        assertEquals(helloApp.getfourStandard(),"一级要求");

        // 获取不存在的信息
        HelloApp helloUser2 = appService.getOne("testappnotexist2");
        assertEquals(helloUser2.getappName(),null);
    }
}
