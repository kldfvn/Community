package com.nowcoder.community.service;

import com.nowcoder.community.dao.alphdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//业务注解
@Service
public class alphaservice {
    @Autowired
    private alphdao alphDao;

    public alphaservice(){
//        System.out.println("shilihua alphaservice ");
    }
    //构造器之后调用
    @PostConstruct
    public void init()
    {
//        System.out.println("init alphaservice");
    }
    //销毁前调用
    @PreDestroy
    public void destory(){
//        System.out.println("xiaohui alphaservice");
    }
    public String find(){
        return alphDao.select();
    }
}
