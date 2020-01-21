package com.example.springweb;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
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
public class HelloServiceTest {
    @Autowired
    HelloService helloService;

    @Test
    public void getUsers1() {
        // 测试获取用户列表功能
        List<HelloUser> users = helloService.getUserList();
        assertFalse("User not null", users == null);
        assertNotNull(users);
        assertNotEquals(users.size(), 0);

        assertEquals(users.size(), 7);
        assertEquals(users.get(0).getId(), "user1");
        assertEquals(users.get(0).getName(), "u1");
        assertEquals(users.get(0).getPassword(), "1234567");
        assertEquals(users.get(1).getId(), "user2");
        assertEquals(users.get(1).getName(), "u2");
        assertEquals(users.get(1).getPassword(), "77777");
    }

    @Test
    public void getUsers2() {
        // 测试获取用户列表功能
        List<HelloUser> users = helloService.getUserList();
        assertFalse("User not null", users == null);
        assertNotNull(users);
        assertNotEquals(users.size(), 0);
        assertEquals(users.size(), 6);
    }

    @Test
    public void testInsert_getOne1() throws Exception{

        Map<String,String> params=new HashMap<>();
        params.put("id","test1");
        params.put("name","t1");
        params.put("password","kuaile");
        helloService.InsertUser(params);
        assertEquals(helloService.getUserList().size(),7);
        // 获取刚刚插入的信息，进一步结合getOne进行验证
        HelloUser helloUser = helloService.getOne("test1");
        assertEquals(helloUser.getName(),"t1");
        assertEquals(helloUser.getPassword(),"kuaile");
        // 获取不存在的信息
        HelloUser helloUser2 = helloService.getOne("testnotexist");
        assertEquals(helloUser2.getName(),null);

    }

    @Test
    public void testInsert_getOne2() throws Exception{

        Map<String,String> params=new HashMap<>();
        params.put("id","test2");
        params.put("name","t2");
        params.put("password","kuaileya");
        helloService.InsertUser(params);
        assertEquals(helloService.getUserList().size(),8);
        // 获取刚刚插入的信息，进一步结合getOne进行验证
        HelloUser helloUser = helloService.getOne("test2");
        assertEquals(helloUser.getName(),"t2");
        assertEquals(helloUser.getPassword(),"kuaileya");
        // 获取不存在的信息
        HelloUser helloUser2 = helloService.getOne("testnull");
        assertEquals(helloUser2.getName(),null);

    }

//    @Test
//    public void testGetOne() throws Exception{
//        HelloUser helloUser = helloService.getOne("9");
//        assertEquals(helloUser.getName(),null);
//    }

    @Test
    public void testUpdate1() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("id","test1");
        params.put("name","ttt1");
        helloService.UpdateByID(params);
        assertEquals(helloService.getOne("test1").getName(),"ttt1");
    }

    @Test
    public void testUpdate2() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("id","test2");
        params.put("name","ttt2");
        helloService.UpdateByID(params);
        assertEquals(helloService.getOne("test2").getName(),"ttt2");
    }

    @Test
    public void testDelete1() throws Exception{
        helloService.DeleteByID("test1");
        assertEquals(helloService.getOne("test1").getName(),null);
    }

    @Test
    public void testDelete2() throws Exception{
        helloService.DeleteByID("test2");
        assertEquals(helloService.getOne("test2").getName(),null);
    }
}
