package com.linzz.community.controller;

import com.linzz.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello Spring boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //        获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+": "+value);
        }
        System.out.println(request.getParameter("code"));

        // 返回相应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer=response.getWriter()){
//            打印网页
//            PrintWriter writer=response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Get请求，默认请求

//    分页显示，当前多少页，每页多少条数据 /students?current=1&limit=20
    @RequestMapping(path="/students",method= RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name="current",required=false, defaultValue="1")int current,
            @RequestParam(name="limit",required=false, defaultValue="10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "get students";
    }

//    查询一个学生 /students/123
    @RequestMapping(path="/student/{id}",method=RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

//    Post请求
    @RequestMapping(path = "/student",method=RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "successfully saved  "+name;
    }

//    响应html数据
    @RequestMapping(path="/teacher",method=RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name","小陈");
        modelAndView.addObject("age","24");
        modelAndView.setViewName("/demo/view"); //指的是/template/demo下的html文件
        return modelAndView;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",110);
         return "/demo/view";
    }

//    相应JSON数据（一般是在异步请求中）
//    Java对象 --> JSON字符串 --> JS对象
    @RequestMapping(path = "/emp", method =RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp=new HashMap<>();
        emp.put("name","小陈");
        emp.put("age",23);
        emp.put("salary","16k");
        return emp;
    }

    @RequestMapping(path = "/emps", method =RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list= new ArrayList<>();
        Map<String, Object> emp=new HashMap<>();
        emp.put("name","小陈");
        emp.put("age",23);
        emp.put("salary","16k");
        list.add(emp);
        emp.put("name","zhangsan");
        emp.put("age",24);
        emp.put("salary","8000");
        list.add(emp);
        emp.put("name","wangwu");
        emp.put("age",25);
        emp.put("salary","18000");
        list.add(emp);
        return list;
    }

}

