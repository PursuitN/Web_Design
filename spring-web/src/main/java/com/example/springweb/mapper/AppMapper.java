package com.example.springweb.mapper;

import com.example.springweb.dao.HelloApp;
import com.example.springweb.dao.HelloUser;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AppMapper {

    @Select("select * from appinfo")
    @Results({
            @Result(property = "appRange", column = "range"),
            @Result(property = "busLink", column = "link"),
            @Result(property = "knowledgeAspect", column = "aspect"),
            @Result(property = "appName", column = "aname"),
            @Result(property = "seriousness", column = "serious"),
            @Result(property = "classifyer", column = "classify"),
            @Result(property = "fourStandard", column = "fours")
    })
    List<HelloApp> findAll();

    @Insert("insert into appinfo(`range`,`link`,`aspect`,`aname`,`serious`,`classify`,`fours`) " +
            "values (#{appRange},#{busLink},#{knowledgeAspect}," +
            " #{appName},#{seriousness},#{classifyer},#{fourStandard})")
    void insert(HelloApp helloApp);

    @Select("select * from appinfo where aname = #{appName}")
    @Results({
            @Result(property = "appRange", column = "range"),
            @Result(property = "busLink", column = "link"),
            @Result(property = "knowledgeAspect", column = "aspect"),
            @Result(property = "appName", column = "aname"),
            @Result(property = "seriousness", column = "serious"),
            @Result(property = "classifyer", column = "classify"),
            @Result(property = "fourStandard", column = "fours")
    })
    HelloApp getOne(String appName);
}
