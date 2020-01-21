package com.example.springweb.controller;

import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import com.example.springweb.dao.HelloUser;
import org.springframework.ui.Model;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    // 1：成功注册；  2：注册失败； 3：登录失败； 4：登录密码错误；  5：登录成功
    private int sendInfo;

    // 当前登录的用户信息
    private String myid;
    private String myname;
    private String mypassword;



    /* 默认界面，开始进入hello的界面 */
    @RequestMapping(value="/")
    public String hello() {
        //logger.info("hello logging" + helloService.getUserList());
        return "redirect:hello";
    }
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String helloset() {
        return "hello";
    }
    @RequestMapping(value="/hello",method = RequestMethod.POST)
    public String helloset(String action) {
        if(action.equals("loginGoto")){
            return "redirect:index";
        }
        else if(action.equals("registerGoto")){
            return "redirect:register";
        }
        return null;
    }



    /* 更改用户信息界面 */
    @RequestMapping(value="/changeuser",method = RequestMethod.GET)
    public String changeuser(Model model) {
        model.addAttribute("iid", myid);
        model.addAttribute("nname", myname);
        model.addAttribute("ppassword", mypassword);
        return "changeuser";
    }
    @RequestMapping(value="/changeuser",method = RequestMethod.POST)
    public String changeuser2(HelloUser user) {
        String n = user.getName();
        String p = user.getPassword();
        myname=n;
        mypassword=p;

        Map<String, String> newUsr = new HashMap<String, String>();
        newUsr.put("id", myid);
        newUsr.put("name", myname);
        newUsr.put("password", mypassword);
        helloService.UpdateByID(newUsr);
        return "redirect:formalhello";
    }



    /* 登录界面，获得来自页面的信息 */
    @RequestMapping(value="/index",method = RequestMethod.POST)
    public String indexGetinfo(HelloUser user, Model model,String action){

        if(action.equals("login")) {
            String i = user.getId();
            String p = user.getPassword();
            if (helloService.getOne(i).getId() == null) {
                // 该用户不存在
                model.addAttribute("sendInfo", 3);
                return "index";
            }
            else if (!helloService.getOne(i).getPassword().equals(p)) {
                // 密码错误
                //logger.info("ee" + helloService.getOne(i).getPassword());
                //logger.info("kk" + p);
                model.addAttribute("sendInfo", 4);
                return "index";
            }
            else {
                // 表示登录成功
                myid=helloService.getOne(i).getId();
                myname=helloService.getOne(i).getName();
                mypassword=helloService.getOne(i).getPassword();
                model.addAttribute("sendInfo", 5);
                return "redirect:formalhello";
            }
        }
        else if(action.equals("backhello")){
            return "redirect:hello";
        }

        return null;
    }
    // 登录界面
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String indexSendinfo(){
        return "index";
    }



    /* 注册界面，获得来自页面的信息 */
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String registerGetinfo(HelloUser user, Model model,String action){
        if(action.equals("register")) {
            Map<String, String> newUsr = new HashMap<String, String>();
            String i = user.getId();
            String n = user.getName();
            String p = user.getPassword();
            if (helloService.getOne(i).getId() == null) {
                // 表示里面没有相同的id，那么存储新的用户
                newUsr.put("id", i);
                newUsr.put("name", n);
                newUsr.put("password", p);
                logger.info("insert:success");
                helloService.InsertUser(newUsr);
                model.addAttribute("sendInfo", 1);
                return "register";
            } else {
                // 表示注册失败
                model.addAttribute("sendInfo", 2);
                logger.info("insert:exist");
                return "register";
            }
        }
        else if(action.equals("backhello")){
            return "redirect:hello";
        }
        return null;
    }
    // 注册界面
    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String registerSendinfo(){
        return "register";
    }

}
