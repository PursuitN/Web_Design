package com.example.springweb.dao;

import java.io.Serializable;

public class HelloApp implements Serializable {

    private String appRange;            // APP的适用范围
    private String busLink;             // APP的业务环节
    private String knowledgeAspect;     // 知识类型

    private String appName;             // APP名称唯一

    private String seriousness;         // APP严重性
    private String classifyer;            // APP控制类别

    private String fourStandard;        // 19项中的4

    public HelloApp(){
        appRange=null;
        busLink=null;
        knowledgeAspect=null;
        appName=null;
        seriousness=null;
        classifyer=null;
        fourStandard=null;
    }

    public String getappRange() {
        return appRange;
    }

    public void setappRange(String appRange) {
        this.appRange = appRange;
    }

    public String getbusLink() {
        return busLink;
    }

    public void setbusLink(String busLink) {
        this.busLink = busLink;
    }

    public String getknowledgeAspect() {
        return knowledgeAspect;
    }

    public void setknowledgeAspect(String knowledgeAspect) {
        this.knowledgeAspect = knowledgeAspect;
    }

    public String getseriousness() {
        return seriousness;
    }

    public void setseriousness(String seriousness) {
        this.seriousness = seriousness;
    }

    public String getappName() {
        return appName;
    }

    public void setappName(String appName) {
        this.appName = appName;
    }

    public String getclassifyer() {
        return classifyer;
    }

    public void setclassifyer(String classifyer) {
        this.classifyer = classifyer;
    }

    public String getfourStandard() {
        return fourStandard;
    }

    public void setfourStandard(String fourStandard) {
        this.fourStandard = fourStandard;
    }

    @Override
    public String toString() {
        return  appRange+", "+ busLink+"," +knowledgeAspect+", "+appName+", "+ seriousness+", "+classifyer+", "+fourStandard;
    }
}
