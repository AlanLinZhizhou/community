package com.linzz.community.controller;

import com.linzz.community.entity.DiscussPost;
import com.linzz.community.entity.Page;
import com.linzz.community.entity.User;
import com.linzz.community.service.DiscussPostService;
import com.linzz.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/index", method= RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        // 方法调用前，SpringMVC会自动实例化Model和Page,并将Page注入给Model
        // 所以在thymeleaf里可以直接放问Page中的对象
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.findDiscussPost(0,page.getOffset(),page.getLimit());
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(!list.isEmpty()){
            for(DiscussPost post: list){
                Map<String, Object> map = new HashMap<>();
                map.put("post",post);
                User user =userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);

        return "index";
    }
}
