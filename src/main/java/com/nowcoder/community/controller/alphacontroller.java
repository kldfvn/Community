package com.nowcoder.community.controller;

import com.nowcoder.community.service.alphaservice;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class alphacontroller {

    @Autowired
    private alphaservice alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayhello()
    {
        return "Hello spring boot";
    }
    @RequestMapping("/data")
    @ResponseBody
    public String getdata()
    {
        return alphaService.find();
    }
//------------------------------------------------------------------------------------------------------------
//    封装前
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response)
    {
//      获取请求数据
        System.out.println(request.getServletPath());
        System.out.println(request.getMethod());
    }
//------------------------------------------------------------------------------------------------------------
//    请求处理
//    GET请求
//    /students?current=1&limit=20
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1")int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10")int limit)
    {
        System.out.println(current);
        System.out.println(limit);
        return "students";
    }
//    /students/123
    @RequestMapping(path = "/students/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id)
    {
        System.out.println(id);
        return "a students";
    }
//    POST
    @RequestMapping(path = "/students",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age)
    {
        System.out.println(name);
        System.out.println(age);
        return "save success";
    }
//------------------------------------------------------------------------------------------------------------
//  响应html数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name","jack");
        modelAndView.addObject("age","30");
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model)
    {
        model.addAttribute("name","pku");
        model.addAttribute("age","80");
        return "/demo/view";
    }
//    相应json数据
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp()
    {
        Map<String,Object> emp=new HashMap<>();
        emp.put("name","joke");
        emp.put("age","23");
        emp.put("salary","8000");
        return emp;
    }
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps()
    {
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> emp1=new HashMap<>();
        emp1.put("name","joke");
        emp1.put("age","23");
        emp1.put("salary","8000");
        Map<String,Object> emp2=new HashMap<>();
        emp2.put("name","jack");
        emp2.put("age","100");
        emp2.put("salary","7000");
        list.add(emp1);
        list.add(emp2);
        return list;
    }
//    cookie
    @RequestMapping(path = "/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response)
    {
        //创建
        Cookie cookie=new Cookie("code", CommunityUtil.generateUUID());
        //设置生效范围
        cookie.setPath("community/alpha");
        //设置生存实践
        cookie.setMaxAge(60*10);
        response.addCookie(cookie);
        return "setCookie";
    }
    @RequestMapping(path = "/cookie/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code)
    {
        return code;
    }
//    session
    @RequestMapping(path = "/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session)
    {
        session.setAttribute("id",1);
        session.setAttribute("name","test");
        return "setSession";
    }
    @RequestMapping(path = "/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session)
    {
        return ""+session.getAttribute("id")+"\t"+session.getAttribute("name");
    }
}
