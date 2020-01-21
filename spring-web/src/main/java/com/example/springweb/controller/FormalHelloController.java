package com.example.springweb.controller;

import com.example.springweb.service.AppService;
import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import com.example.springweb.dao.HelloApp;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class FormalHelloController {

    @Autowired
    AppService appService;

    // 传递信�
    private String sendInfo2;

    // 当前APP的信�
    private String appr;
    private String appl;
    private String appk;
    private String appnn;
    private String appser;
    private String appcla;
    private String appf;

    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);



    /* 正式成功登录后的界面 */
    @RequestMapping(value="/formalhello",method = RequestMethod.GET)
    public String formalhello(Model model) {
        return "formalhello";
    }
    // 获取选项
    @RequestMapping(value="/formalhello",method = RequestMethod.POST)
    public String formalhelloget(Model model,String action) {
        if(action.equals("appGoto")){
            // 提交APP
            return "redirect:addApp";
        }
        else if (action.equals("appSearch")) {
            return "redirect:infoapp";
        }
        else if(action.equals("appShow")){
            return "redirect:showinfo";
        }
        else if(action.equals("changeUser")){
            return "redirect:changeuser";
        }
        else if(action.equals("exitUser")){
            return "redirect:hello";
        }
        return null;
    }


    /* 提交APP界面 */
    @RequestMapping(value="/addApp",method = RequestMethod.GET)
    public String submitui(Model model) {
        return "addapp";
    }
    // 提交：分类选择-适用范围
    @RequestMapping(value="/addApp",method = RequestMethod.POST)
    public String submit1(String apprange) {
        logger.info(apprange);
        appr=apprange;
        return "redirect:addApp02";
    }
    // 提交：分类选择-业务环节
    @RequestMapping(value="/addApp02",method = RequestMethod.GET)
    public String submitui2() {
        return "addapp02";
    }
    @RequestMapping(value="/addApp02",method = RequestMethod.POST)
    public String submit2(String busLink) {
        logger.info(busLink);
        appl=busLink;
        return "redirect:addApp03";
    }
    // 提交：分类选择-知识类型
    @RequestMapping(value="/addApp03",method = RequestMethod.GET)
    public String submitui3() {
        return "addapp03";
    }
    @RequestMapping(value="/addApp03",method = RequestMethod.POST)
    public String submit3(String knowledgeAspect) {
        appk=knowledgeAspect;
        logger.info(knowledgeAspect);
        return "redirect:addApp04";
    }
    // 提交：命�
    @RequestMapping(value="/addApp04",method = RequestMethod.GET)
    public String submitui4() {
        return "addapp04";
    }
    @RequestMapping(value="/addApp04",method = RequestMethod.POST)
    public String submit4(String iname) {
        appnn=iname;
        logger.info(iname);
        return "redirect:addApp05";
    }
    // 提交-安全1
    @RequestMapping(value="/addApp05",method = RequestMethod.GET)
    public String submitui5() {
        return "addapp05";
    }
    @RequestMapping(value="/addApp05",method = RequestMethod.POST)
    public String submit5(String seriousness) {
        appser=seriousness;
        logger.info(seriousness);
        return "redirect:addApp06";
    }
    // 提交-安全2
    @RequestMapping(value="/addApp06",method = RequestMethod.GET)
    public String submitui6() {
        return "addapp06";
    }
    @RequestMapping(value="/addApp06",method = RequestMethod.POST)
    public String submit6(String controla) {
        appcla=controla;
        logger.info(controla);
        return "redirect:addApp07";
    }
    // 提交-安全19�
    @RequestMapping(value="/addApp07",method = RequestMethod.GET)
    public String submitui7() {
        return "addapp07";
    }
    @RequestMapping(value="/addApp07",method = RequestMethod.POST)
    public String submit7(String innova) {
		logger.info(innova);
        appf=innova;
        Map<String, String> newApp = new HashMap<String, String>();
        if (appService.getOne(appnn).getappName() == null) {
            // 表示里面没有相同的id，那么存储新的用�
            newApp.put("appRange", appr);
            newApp.put("busLink", appl);
            newApp.put("knowledgeAspect", appk);
            newApp.put("appName", appnn);
            newApp.put("seriousness", appser);
            newApp.put("classifyer", appcla);
            newApp.put("fourStandard", appf);

            logger.info("insertApp:success");
            appService.InsertApp(newApp);
            //logger.info("insertApp:success");
            return "redirect:addappsucc";
        } else {
            // 表示注册失败

            logger.info("insertApp:exist");
            return "redirect:addappfail";
        } 
	}


    /* 提交成功与失�*/
    @RequestMapping(value="/addappsucc",method = RequestMethod.GET)
    public String submituisucc() {
        return "addappsucc";
    }
    @RequestMapping(value="/addappfail",method = RequestMethod.GET)
    public String submituifail() {
        return "addappfail";
    }
    // 跳转
    @RequestMapping(value="/addappsucc",method = RequestMethod.POST)
    public String submitsucc() {
        return "redirect:formalhello";
    }
    @RequestMapping(value="/addappfail",method = RequestMethod.POST)
    public String submitfail() {
        return "redirect:formalhello";
    }



    /* 关于APP*/
    @RequestMapping(value="/infoapp",method = RequestMethod.GET)
    public String getinfo() {
        return "infoapp";
    }
    @RequestMapping(value="/infoapp",method = RequestMethod.POST)
    public String getinfo2() {
        return "redirect:formalhello";
    }



    /* 展示APP内容 */
    @RequestMapping(value="/showinfo",method = RequestMethod.GET)
    public String showinfo( Model model) {
        String finals="";
        List <HelloApp> mylist=appService.getAppList();
        for(int i=0;i<mylist.size();i++){
            HelloApp oneapp=mylist.get(i);
            String total=oneapp.toString();
            //logger.info(total);
            finals=finals+"�+(1+i)+"�+total+"   ";
            logger.info(finals);
        }
        model.addAttribute("sinfo", finals);
        return "showinfo";
    }
    @RequestMapping(value="/showinfo",method = RequestMethod.POST)
    public String showinfo2() {
        return "redirect:formalhello";
    }

}
